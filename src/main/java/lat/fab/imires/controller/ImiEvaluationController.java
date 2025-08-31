package lat.fab.imires.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lat.fab.imires.util.OpenAiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;

@RestController
@RequestMapping("/api/imi")
@RequiredArgsConstructor
@Slf4j
public class ImiEvaluationController {

    private final OpenAiClient openAiClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final List<String> fileIds = List.of(
            "file-GWY6eyeK138BT2Mz6uC9aQ",
            "file-2z2bvEjKjspeeCvJpEcCpF",
            "file-7E9bArWsJ54JJw9kzXaVdP"
    );

    // =================== 1. Cargar archivos ===================
    @PostMapping("/cargar-archivos")
    public Mono<ResponseEntity<?>> cargarArchivosDesdeStorage() {
        List<String> archivos = List.of(
                "1_IMI_Detalle_del_Modelo_de_Madurez.pdf",
                "3_IMI_Brechas_identificadas_en_el_Plan_de_Establecimiento_y_Desarrollo_del_CSTD.pdf",
                "4_IMI_Descripción_de_la_cadena_productiva.pdf"
        );

        return Flux.fromIterable(archivos)
                .flatMap(nombre -> {
                    Path path = Paths.get("storage/app/public", nombre);
                    if (!Files.exists(path)) {
                        log.error("Archivo no encontrado: {}", path);
                        return Mono.empty();
                    }
                    try {
                        byte[] contenido = Files.readAllBytes(path);
                        return openAiClient.multipart("/files", contenido, nombre, Map.of("purpose", "assistants"))
                                .map(resp -> resp.path("id").asText());
                    } catch (IOException e) {
                        return Mono.error(e);
                    }
                })
                .collectList()
                .map(ids -> {
                    log.info("Archivos cargados en OpenAI: {}", ids);
                    return ResponseEntity.ok(Map.of("file_ids", ids));
                });
    }

    // =================== 2. Crear VectorStore ===================
    @PostMapping("/crear-vector-store")
    public Mono<ResponseEntity<?>> crearVectorStoreConArchivos() {
        return openAiClient.post("/vector_stores", Map.of("file_ids", fileIds))
                .map(resp -> resp.path("id").asText())
                .map(id -> {
                    log.info("Vector store creado: {}", id);
                    return ResponseEntity.ok(Map.of("vector_store_id", id));
                });
    }

    // =================== 3. Analizar Empresa ===================
    @PostMapping("/analizarEmpresa")
    public Mono<ResponseEntity<?>> analizarEmpresa(@RequestBody Map<String, String> body) {
        String descripcion = body.get("descripcion");

        String prompt = """
                Descripción de la empresa:
                """ + descripcion + """

                Con base en los documentos del IMI (modelo de madurez industrial, brechas, cadenas productivas), analiza esta empresa y responde estrictamente en el siguiente formato JSON:

                {
                  "resumen": "...",
                  "puntajes": {
                    "producto": { "puntaje": 1-4, "justificacion": "..." },
                    "recursos": { "puntaje": 1-4, "justificacion": "..." },
                    "informacion": { "puntaje": 1-4, "justificacion": "..." },
                    "organizacion": { "puntaje": 1-4, "justificacion": "..." },
                    "innovacion": { "puntaje": 1-4, "justificacion": "..." }
                  },
                  "preguntas": {
                    "recursos": [
                      {
                        "pregunta": "...",
                        "opciones": [
                          { "texto": "...", "puntaje": 1 },
                          { "texto": "...", "puntaje": 2 },
                          { "texto": "...", "puntaje": 3 },
                          { "texto": "...", "puntaje": 4 }
                        ]
                      }
                    ],
                    "organizacion": [...],
                    "innovacion": [...]
                  }
                }
                """;

        return crearAssistant()
                .flatMap(assistantId -> crearThread()
                        .flatMap(threadId -> enviarMensaje(threadId, prompt)
                                .then(iniciarRun(assistantId, threadId))
                                .flatMap(runId -> esperarRun(threadId, runId)
                                        .then(obtenerRespuesta(threadId))
                                        .map(output -> ResponseEntity.ok(
                                                Map.of("output", limpiarJson(output))
                                        ))
                                )
                        )
                );
    }

    // =================== 4. Generar Preguntas ===================
    @PostMapping("/generarPreguntas")
    public Mono<ResponseEntity<?>> generarPreguntas(@RequestBody Map<String, String> body) {
        String descripcion = body.get("descripcion");

        String prompt = """
                Eres un asistente que ayuda a evaluar el nivel de madurez de una empresa.
                        
                Responde SIEMPRE en **español**, usando un lenguaje sencillo y natural para personas sin conocimientos técnicos. No uses expresiones en inglés ni traducciones literales.
                        
                Descripción de la empresa:
                """ + descripcion + """

                Estas son las categorías y subcategorías a evaluar:
                                        
                1. Product Management: Product, Design, Fabrication
                2. Resources Management: Materials, Energy, Logistics
                3. Information Management: Sensing, Processing, Actuator
                4. Organization Management: Organization, Impact, Compliance
                5. Innovation Management: Innovation, Intellectual property, Training
        
                Instrucciones:
                - Para **cada subcategoría**, revisa la descripción y decide si es posible deducir el nivel de madurez (1 a 4).
                - Si la descripción **NO brinda suficiente información**, genera **1 o 2 preguntas** simples y concretas para esa subcategoría.
                - Cada pregunta debe tener **exactamente 4 opciones**, con un puntaje de 1 a 4, siendo 1 lo más básico y 4 lo más avanzado.
                - No uses palabras complejas como “sistemáticamente” o “implementación digital”.
                - Todas las preguntas y opciones deben estar en **español claro y fácil de entender**.
        
                Responde **EXCLUSIVAMENTE en español y SOLO en formato JSON** siguiendo este ejemplo:
        
                {
                "preguntas": {
                    "Product Management": [
                    {
                        "pregunta": "¿Cómo diseñan los modelos de sus chompas?",
                        "opciones": [
                        { "texto": "No hacemos diseño previo", "puntaje": 1 },
                        { "texto": "Los diseños se hacen de forma improvisada", "puntaje": 2 },
                        { "texto": "Tenemos algunos diseños estándar", "puntaje": 3 },
                        { "texto": "Trabajamos con diseños planificados y documentados", "puntaje": 4 }
                        ]
                    }
                    ],
                    "Resources Management": [],
                    "Information Management": [],
                    "Organization Management": [],
                    "Innovation Management": []
                }
                }
                """;

        return crearAssistant()
                .flatMap(assistantId -> crearThread()
                        .flatMap(threadId -> enviarMensaje(threadId, prompt)
                                .then(iniciarRun(assistantId, threadId))
                                .flatMap(runId -> esperarRun(threadId, runId)
                                        .then(obtenerRespuesta(threadId))
                                        .map(output -> ResponseEntity.ok(
                                                //Map.of("output", limpiarJson(output), "raw", output)
                                                Map.of("output", limpiarJson(output))
                                        ))
                                )
                        )
                );
    }

    // =================== 5. Analizar Respuestas ===================
    @PostMapping("/analizarRespuestas")
    public Mono<ResponseEntity<?>> analizarRespuestas(@RequestBody Map<String, Object> body) {
        String descripcion = (String) body.get("descripcion");
        Map<String, Object> respuestas = (Map<String, Object>) body.get("respuestas");

        String prompt = """
            Descripción de la empresa:
            """ + descripcion + """

            Respuestas del usuario por categoría:
            """ + formatearRespuestas(respuestas) + """

            Con base en esta información y los documentos del IMI (modelo, brechas, cadena productiva), genera el análisis completo de la madurez industrial de la empresa.

            Evalúa cada subcategoría de forma individual, asignando un puntaje (1 a 4) y una justificación breve. Luego, para cada categoría (por ejemplo, "Product Management", "Resources Management", etc.), genera una recomendación clara y práctica para mejorar esa dimensión.

            Para la **recomendacion_general**, genera un texto motivador y detallado que incluya:
            1. Un resumen inspirador sobre el potencial de la empresa.
            2. Ejemplos breves de empresas reales o agrupaciones en el Mundo.
            3. Algún dato real relevante sobre digitalización o productividad en pymes.
            4. Un mensaje final positivo que anime a la acción.

            El resultado debe respetar estrictamente este formato JSON:

            {
              "resumen": "Resumen general...",
              "puntajes": {
                "Product Management": {
                  "Product": { "puntaje": 1-4, "justificacion": "..." },
                  "Design": { "puntaje": 1-4, "justificacion": "..." },
                  "Fabrication": { "puntaje": 1-4, "justificacion": "..." }
                },
                "Resources Management": {
                  "Materials": { ... },
                  "Energy": { ... },
                  "Logistics": { ... }
                },
                "Information Management": {
                  "Sensing": { ... },
                  "Processing": { ... },
                  "Actuator": { ... }
                },
                "Organization Management": {
                  "Organization": { ... },
                  "Impact": { ... },
                  "Compliance": { ... }
                },
                "Innovation Management": {
                  "Innovation": { ... },
                  "Intellectual property": { ... },
                  "Training": { ... }
                }
              },
              "recomendaciones": {
                "Product Management": "...",
                "Resources Management": "...",
                "Information Management": "...",
                "Organization Management": "...",
                "Innovation Management": "..."
              },
              "recomendacion_general": "<inserta aquí un texto motivador y detallado según las instrucciones>"
            }

            Usa un lenguaje sencillo y sin tecnicismos complejos. No utilices palabras como “sistémico”, “framework” o “automatización avanzada”.
            Devuelve solo el JSON sin explicaciones ni marcas de Markdown como ```json.
            """;

        return crearAssistant()
                .flatMap(assistantId -> crearThread()
                        .flatMap(threadId -> enviarMensaje(threadId, prompt)
                                .then(iniciarRun(assistantId, threadId))
                                .flatMap(runId -> esperarRun(threadId, runId)
                                        .then(obtenerRespuesta(threadId))
                                        .map(output -> {
                                            String limpio = output.trim()
                                                    .replaceAll("^```json", "")
                                                    .replaceAll("```$", "")
                                                    .replaceAll("【[^】]+】", "");
                                            return ResponseEntity.ok(
                                                    //Map.of("output", limpiarJson(limpio), "raw", output)
                                                    Map.of("output", limpiarJson(limpio))
                                            );
                                        })
                                )
                        )
                );
    }


    // =================== Helpers ===================
    private Mono<String> crearAssistant() {
        return openAiClient.post("/assistants", Map.of(
                "model", "gpt-4-turbo",
                "name", "IMI Evaluador",
                "instructions", "Eres un experto en madurez industrial. Responde solo en JSON.",
                "tools", List.of(Map.of("type", "file_search"))
        )).map(resp -> resp.path("id").asText());
    }

    private Mono<String> crearThread() {
        return openAiClient.post("/threads", Map.of())
                .map(resp -> resp.path("id").asText());
    }

    private Mono<JsonNode> enviarMensaje(String threadId, String prompt) {
        return openAiClient.post("/threads/" + threadId + "/messages", Map.of(
                "role", "user", "content", prompt
        ));
    }

    private Mono<String> iniciarRun(String assistantId, String threadId) {
        return openAiClient.post("/threads/" + threadId + "/runs", Map.of(
                "assistant_id", assistantId,
                "tool_resources", Map.of("file_search",
                        Map.of("vector_store_ids", List.of("vs_686b20300f70819186e3ab012086c748")))
        )).map(resp -> resp.path("id").asText());
    }

    private Mono<JsonNode> esperarRun(String threadId, String runId) {
        return Flux.interval(Duration.ofSeconds(3))
                .flatMap(tick -> openAiClient.get("/threads/" + threadId + "/runs/" + runId))
                .filter(resp -> "completed".equals(resp.path("status").asText()))
                .next();
    }

    private Mono<String> obtenerRespuesta(String threadId) {
        return openAiClient.get("/threads/" + threadId + "/messages")
                .map(resp -> resp.path("data").get(0).path("content").get(0).path("text").path("value").asText());
    }

    private String formatearRespuestas(Map<String, Object> respuestas) {
        StringBuilder sb = new StringBuilder();
        respuestas.forEach((categoria, preguntas) -> {
            sb.append(categoria).append(":\n");
            if (preguntas instanceof Map<?, ?> map) {
                map.forEach((pregunta, respuesta) -> {
                    if (respuesta instanceof Map<?, ?> resMap &&
                            resMap.containsKey("texto") && resMap.containsKey("puntaje")) {
                        sb.append("- ").append(pregunta).append(": ")
                                .append(resMap.get("texto"))
                                .append(" (Puntaje: ").append(resMap.get("puntaje")).append(")\n");
                    } else if (respuesta instanceof Map<?, ?> resMap && resMap.containsKey("libre")) {
                        sb.append("- ").append(pregunta).append(" (respuesta libre): ")
                                .append(resMap.get("libre")).append("\n");
                    } else {
                        sb.append("- ").append(pregunta).append(": ").append(respuesta).append("\n");
                    }
                });
            }
            sb.append("\n");
        });
        return sb.toString();
    }

    private Map<String, Object> limpiarJson(String respuesta) {
        String limpio = respuesta.trim()
                .replaceAll("^```json", "")
                .replaceAll("```$", "")
                .replaceAll("【[^】]+】", "");
        try {
            return objectMapper.readValue(limpio, Map.class);
        } catch (Exception e) {
            return Map.of("raw", limpio);
        }
    }
}

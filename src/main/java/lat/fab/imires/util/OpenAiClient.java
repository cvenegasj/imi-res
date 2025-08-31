package lat.fab.imires.util;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class OpenAiClient {

    private final WebClient client;

    public OpenAiClient(@Value("${openai}") String apiKey) {
        this.client = WebClient.builder()
                .baseUrl("https://api.openai.com/v1")
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .defaultHeader("OpenAI-Beta", "assistants=v2")
                .build();
    }

    public Mono<JsonNode> post(String path, Object body) {
        return client.post()
                .uri(path)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    public Mono<JsonNode> get(String path) {
        return client.get()
                .uri(path)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }

    public Mono<JsonNode> multipart(String path, byte[] contenido, String nombreArchivo, Map<String, String> extraFields) {
        ByteArrayResource fileResource = new ByteArrayResource(contenido) {
            @Override
            public String getFilename() {
                return nombreArchivo;
            }
        };

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileResource);
        if (extraFields != null) {
            extraFields.forEach(body::add);
        }

        return client.post()
                .uri(path)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }
}


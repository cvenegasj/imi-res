package lat.fab.imires.repository;

import lat.fab.imires.model.Academic;
import lat.fab.imires.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AcademicRepository extends BaseRepository<Academic, String> {

    Mono<Academic> findByEmail(String email);

    Flux<Academic> findByCountriesContaining(String country);
}

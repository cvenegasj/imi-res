package lat.fab.imires.repository;

import lat.fab.imires.model.Academic;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DataAcademicRepository extends ReactiveMongoRepository<Academic, String> {

    Flux<Academic> findByEmail(String email);

    Flux<Academic> findByCountriesContaining(String country);
}

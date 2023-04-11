package lat.fab.imires.repository;

import lat.fab.imires.model.Provider;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DataProviderRepository extends ReactiveMongoRepository<Provider, String> {

    Flux<Provider> findByEmail(String email);

    Flux<Provider> findByCountriesContaining(String country);

    Flux<Provider> findByIndustriesContaining(String industry);
}

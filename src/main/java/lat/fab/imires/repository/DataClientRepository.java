package lat.fab.imires.repository;

import lat.fab.imires.model.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DataClientRepository extends ReactiveMongoRepository<Client, String> {

    Flux<Client> findByEmail(String email);

    Flux<Client> findByCountriesContaining(String country);

    Flux<Client> findByIndustriesContaining(String industry);
}

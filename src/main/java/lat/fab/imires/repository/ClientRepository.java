package lat.fab.imires.repository;

import lat.fab.imires.model.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientRepository extends BaseRepository<Client, String> {

    Mono<Client> findByEmail(String email);

    Flux<Client> findByCountriesContaining(String country);

    Flux<Client> findByIndustriesContaining(String industry);
}

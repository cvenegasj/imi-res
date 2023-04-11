package lat.fab.imires.repository;

import lat.fab.imires.model.Client;
import lat.fab.imires.model.Provider;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProviderRepository extends BaseRepository<Provider, String> {

    Mono<Provider> findByEmail(String email);

    Flux<Provider> findSuggestedProviders(Client c, int threshold);

    Flux<Provider> findByCountriesContaining(String country);

    Flux<Provider> findByIndustriesContaining(String industry);
}

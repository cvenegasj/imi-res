package lat.fab.imires.repository;

import lat.fab.imires.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MongoClientRepository implements ClientRepository {

    @Autowired
    private DataClientRepository clientRepository;

    @Override
    public Mono<Client> save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Mono<Void> remove(String id) {
        return clientRepository.deleteById(id);
    }

    @Override
    public Flux<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Mono<Client> findById(String id) {
        return clientRepository.findById(id);
    }

    @Override
    public Mono<Client> findByEmail(String email) {
        return clientRepository.findByEmail(email).next();
    }

    @Override
    public Flux<Client> findByCountriesContaining(String country) {
        return clientRepository.findByCountriesContaining(country);
    }

    @Override
    public Flux<Client> findByIndustriesContaining(String industry) {
        return clientRepository.findByIndustriesContaining(industry);
    }
}

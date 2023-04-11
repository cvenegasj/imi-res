package lat.fab.imires.repository;

import lat.fab.imires.model.Academic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MongoAcademicRepository implements AcademicRepository {

    @Autowired
    private DataAcademicRepository academicRepository;

    @Override
    public Mono<Academic> save(Academic academic) {
        return academicRepository.save(academic);
    }

    @Override
    public Mono<Void> remove(String id) {
        return academicRepository.deleteById(id);
    }

    @Override
    public Flux<Academic> findAll() {
        return academicRepository.findAll();
    }

    @Override
    public Mono<Academic> findById(String id) {
        return academicRepository.findById(id);
    }

    @Override
    public Mono<Academic> findByEmail(String email) {
        return academicRepository.findByEmail(email).next();
    }

    @Override
    public Flux<Academic> findByCountriesContaining(String country) {
        return academicRepository.findByCountriesContaining(country);
    }
}

package lat.fab.imires.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BaseRepository<T, U> {

    Mono<T> save(T entity);

    Mono<Void> remove(U id);

    Flux<T> findAll();

    Mono<T> findById(U id);
}

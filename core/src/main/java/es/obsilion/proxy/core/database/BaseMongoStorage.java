package es.obsilion.proxy.core.database;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface BaseMongoStorage<T> {

    Collection<T> loadAll();

    CompletableFuture<Collection<T>> loadAllAsync();

    T load(String name);

    T load(UUID uuid);

    CompletableFuture<T> loadAsync(String name);

    CompletableFuture<T> loadAsync(UUID uuid);

    void saveAll();

    void save(T t);

    void saveAsync(T t);

    void remove(T t);

    void removeAsync(T t);
}

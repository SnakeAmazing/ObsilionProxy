package es.obsilion.proxy.core.database;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public interface BaseMongoStorage<T> {

    Collection<T> loadAll();

    CompletableFuture<Collection<T>> loadAllAsync();

    T load();

    CompletableFuture<T> loadAsync();

    void save(T t);

    void saveAsync(T t);

    void remove(T t);

    void removeAsync(T t);
}

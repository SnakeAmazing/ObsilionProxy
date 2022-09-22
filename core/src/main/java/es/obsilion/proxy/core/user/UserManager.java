package es.obsilion.proxy.core.user;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface UserManager<T> {

    void load(UUID uuid);

    void load(String name);

    boolean isLoaded(String name);

    boolean isLoaded(UUID uuid);

    boolean isLoaded(T t);

    void unLoad(UUID uuid);

    void unLoad(String name);

    void unLoad(T t);

    Collection<T> getAll();

    Optional<T> get(UUID uuid);

    Optional<T> get(String name);

    Optional<T> get(T t);
}

package es.obsilion.proxy.bungee.user;

import com.google.inject.Inject;
import es.obsilion.proxy.bungee.storage.UserStorage;
import es.obsilion.proxy.core.user.UserManager;
import org.bson.types.ObjectId;

import java.util.*;

public class BungeeUserManager implements UserManager<BungeeUser> {

    @Inject
    private UserStorage userStorage;

    private final Map<UUID, BungeeUser> users = new HashMap<>();
    private final Map<String, UUID> namesToUuids = new HashMap<>();

    @Override
    public BungeeUser loadNewUser(String name, UUID uuid) {
        BungeeUser bungeeUser = new BungeeUser(new ObjectId().toString(), uuid, name);
        userStorage.saveAsync(bungeeUser);
        users.put(uuid, bungeeUser);
        namesToUuids.put(name, uuid);

        return bungeeUser;
    }

    @Override
    public void load(UUID uuid) {
        userStorage.loadAsync(uuid)
                .thenAcceptAsync(bungeeUser -> {
                            users.put(bungeeUser.getUniqueId(), bungeeUser);
                            namesToUuids.put(bungeeUser.getName(), bungeeUser.getUniqueId());
                        }
                );
    }

    @Override
    public void load(String name) {
        userStorage.loadAsync(name)
                .thenAcceptAsync(bungeeUser -> {
                            users.put(bungeeUser.getUniqueId(), bungeeUser);
                            namesToUuids.put(bungeeUser.getName(), bungeeUser.getUniqueId());
                        }
                );
    }

    @Override
    public boolean isLoaded(String name) {
        return namesToUuids.containsKey(name);
    }

    @Override
    public boolean isLoaded(UUID uuid) {
        return users.containsKey(uuid);
    }

    @Override
    public boolean isLoaded(BungeeUser bungeeUser) {
        return users.containsKey(bungeeUser.getUniqueId());
    }

    @Override
    public boolean isRegistered(UUID uuid) {
        return userStorage.exists(uuid);
    }

    @Override
    public void unLoad(UUID uuid) {
        BungeeUser bungeeUser = users.get(uuid);
        unLoad(bungeeUser);
    }

    @Override
    public void unLoad(String name) {
        BungeeUser bungeeUser = users.get(namesToUuids.get(name));
        unLoad(bungeeUser);
    }

    @Override
    public void unLoad(BungeeUser bungeeUser) {
        userStorage.saveAsync(bungeeUser);
        users.remove(bungeeUser.getUniqueId());
        namesToUuids.remove(bungeeUser.getName());
    }

    @Override
    public Collection<BungeeUser> getAll() {
        return users.values();
    }

    @Override
    public Optional<BungeeUser> get(UUID uuid) {
        return Optional.of(users.get(uuid));
    }

    @Override
    public Optional<BungeeUser> get(String name) {
        return get(namesToUuids.get(name));
    }

    @Override
    public Optional<BungeeUser> get(BungeeUser bungeeUser) {
        return get(bungeeUser.getUniqueId());
    }
}

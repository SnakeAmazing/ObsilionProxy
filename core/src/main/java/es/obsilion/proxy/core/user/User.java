package es.obsilion.proxy.core.user;

import java.util.UUID;

public class User {

    private final UUID uuid;

    private final String name;

    public User(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public UUID getUniqueId() {
        return uuid;
    }
}

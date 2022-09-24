package es.obsilion.proxy.core.user;

import java.util.UUID;

public class User {

    protected String documentId;

    protected UUID uuid;

    protected String name;

    public User(String documentId, UUID uuid, String name) {
        this.documentId = documentId;
        this.uuid = uuid;
        this.name = name;
    }

    public String getDocumentId() {
        return documentId;
    }

    public String getName() {
        return name;
    }

    public UUID getUniqueId() {
        return uuid;
    }
}

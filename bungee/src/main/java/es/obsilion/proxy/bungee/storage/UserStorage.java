package es.obsilion.proxy.bungee.storage;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import es.obsilion.proxy.bungee.serialize.BungeeUserSerializable;
import es.obsilion.proxy.bungee.user.BungeeUser;
import es.obsilion.proxy.core.database.BaseMongoStorage;
import es.obsilion.proxy.core.user.UserManager;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class UserStorage implements BaseMongoStorage<BungeeUser> {

    @Inject
    private UserManager<BungeeUser> userManager;

    private final static String collectionName = "users";
    private final Gson gson;
    private final MongoCollection<Document> mongoCollection;

    public UserStorage(MongoClient mongoClient, String database) {
        this.gson = new Gson();
        MongoDatabase mongoDatabase = mongoClient.getDatabase(database);
        mongoCollection = mongoDatabase.getCollection(collectionName);
    }

    @Override
    public Collection<BungeeUser> loadAll() {
        FindIterable<Document> documents = mongoCollection.find();
        Collection<BungeeUser> bungeeUsers = new ArrayList<>();

        for (Document document : documents) {
            bungeeUsers.add(load(document));
        }

        return bungeeUsers;
    }

    @Override
    public CompletableFuture<Collection<BungeeUser>> loadAllAsync() {
        return CompletableFuture.supplyAsync(this::loadAll);
    }

    @Override
    public BungeeUser load(String name) {
        Document document = mongoCollection.find(Filters.eq("name", name)).first();
        if (document == null) return null;

        return load(document);
    }

    @Override
    public BungeeUser load(UUID uuid) {
        Document document = mongoCollection.find(Filters.eq("uuid", uuid.toString())).first();
        if (document == null) return null;

        return load(document);
    }

    private BungeeUser load(Document document) {
        BungeeUserSerializable userSerializable = gson.fromJson(
                document.toJson(), BungeeUserSerializable.class
        );

        return new BungeeUser(
                userSerializable.getDocumentId(), UUID.fromString(userSerializable.getUuid()),
                userSerializable.getName(), userSerializable.getCoins(), userSerializable.getFriends(),
                userSerializable.getLastServer()
        );
    }

    @Override
    public CompletableFuture<BungeeUser> loadAsync(String name) {
        return CompletableFuture.supplyAsync(() -> load(name));
    }

    @Override
    public CompletableFuture<BungeeUser> loadAsync(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> load(uuid));
    }

    @Override
    public void saveAll() {
        for (BungeeUser bungeeUser : userManager.getAll()) {
            saveAsync(bungeeUser);
        }
    }

    @Override
    public void save(BungeeUser bungeeUser) {
        remove(bungeeUser);
        mongoCollection.insertOne(Document.parse(gson.toJson(bungeeUser.serialize())));
    }

    @Override
    public void saveAsync(BungeeUser bungeeUser) {
        CompletableFuture.runAsync(() -> save(bungeeUser));
    }

    @Override
    public void remove(BungeeUser bungeeUser) {
        mongoCollection.findOneAndDelete(Filters.eq("_id", bungeeUser.getDocumentId()));
    }

    @Override
    public void removeAsync(BungeeUser bungeeUser) {
        CompletableFuture.runAsync(() -> remove(bungeeUser));
    }
}

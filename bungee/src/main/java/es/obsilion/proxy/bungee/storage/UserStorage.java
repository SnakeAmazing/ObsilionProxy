package es.obsilion.proxy.bungee.storage;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import es.obsilion.proxy.bungee.user.BungeeUser;
import es.obsilion.proxy.core.database.BaseMongoStorage;
import org.bson.Document;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class UserStorage implements BaseMongoStorage<BungeeUser> {

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
        return null;
    }

    @Override
    public CompletableFuture<Collection<BungeeUser>> loadAllAsync() {
        return CompletableFuture.supplyAsync(this::loadAll);
    }

    @Override
    public BungeeUser load(String name) {
        return null;
    }

    @Override
    public BungeeUser load(UUID uuid) {
        return null;
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
    public void save(BungeeUser bungeeUser) {

    }

    @Override
    public void saveAsync(BungeeUser bungeeUser) {
        CompletableFuture.runAsync(() -> save(bungeeUser));
    }

    @Override
    public void remove(BungeeUser param) {

    }

    @Override
    public void removeAsync(BungeeUser bungeeUser) {
        CompletableFuture.runAsync(() -> remove(bungeeUser));
    }
}

package es.obsilion.proxy.core.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import es.obsilion.proxy.core.service.Service;

public class MongoDatabaseService implements Service {

    private final String uri;

    private MongoClient mongoClient;

    public MongoDatabaseService(String uri) {
        this.uri = uri;
    }

    @Override
    public void start() {
        mongoClient = new MongoClient(new MongoClientURI(uri));
    }

    @Override
    public void stop() {
        mongoClient.close();
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }
}

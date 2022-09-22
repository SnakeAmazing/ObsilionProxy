package es.obsilion.proxy.bungee.injector.module;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.name.Names;
import com.mongodb.MongoClient;
import es.obsilion.proxy.bungee.file.YAMLFile;
import es.obsilion.proxy.bungee.storage.UserStorage;
import es.obsilion.proxy.core.database.MongoDatabaseService;
import es.obsilion.proxy.core.service.Service;

import javax.inject.Named;

public class StorageModule implements Module {

    @Inject
    @Named("config")
    private YAMLFile config;

    @Override
    public void configure(Binder binder) {
        MongoDatabaseService mongoDatabaseService = new MongoDatabaseService(config.getString("database.uri"));
        binder.bind(MongoClient.class).toInstance(mongoDatabaseService.getMongoClient());
        binder.bind(Service.class).annotatedWith(Names.named("storage-service")).toInstance(mongoDatabaseService);

        UserStorage userStorage = new UserStorage(mongoDatabaseService.getMongoClient(), config.getString("database.database"));
        binder.bind(UserStorage.class).toInstance(userStorage);
    }
}

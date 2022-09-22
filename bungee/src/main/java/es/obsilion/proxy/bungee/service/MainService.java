package es.obsilion.proxy.bungee.service;

import com.google.inject.Inject;
import es.obsilion.proxy.core.service.Service;

import javax.inject.Named;

public class MainService implements Service {

    @Inject
    @Named("storage-service")
    private Service mongoDatabaseService;

    @Override
    public void start() {
        mongoDatabaseService.start();
    }

    @Override
    public void stop() {
        mongoDatabaseService.stop();
    }
}

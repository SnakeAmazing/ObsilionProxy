package es.obsilion.proxy.bungee;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import es.obsilion.proxy.bungee.injector.MainModule;
import es.obsilion.proxy.core.service.Service;
import net.md_5.bungee.api.plugin.Plugin;

import javax.inject.Named;

public class ObsilionBungee extends Plugin {

    private Injector injector;

    @Inject
    @Named("main-service")
    private Service mainService;

    @Override
    public void onLoad() {
        injector = Guice.createInjector(new MainModule(this));
    }

    @Override
    public void onEnable() {
        mainService.start();
    }

    @Override
    public void onDisable() {
        mainService.stop();
    }

    public Injector getInjector() {
        return injector;
    }
}

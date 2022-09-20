package es.obsilion.proxy.bungee;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import es.obsilion.proxy.bungee.injector.MainModule;
import es.obsilion.proxy.core.database.MongoDatabaseService;
import net.md_5.bungee.api.plugin.Plugin;

public class ObsilionBungee extends Plugin {

    private Injector injector;

    @Inject
    private MongoDatabaseService mongoDatabaseService;

    @Override
    public void onLoad() {
        injector = Guice.createInjector(new MainModule(this));
    }

    @Override
    public void onEnable() {
        mongoDatabaseService.start();
    }

    @Override
    public void onDisable() {
        mongoDatabaseService.stop();
    }

    public Injector getInjector() {
        return injector;
    }
}

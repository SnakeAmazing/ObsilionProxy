package es.obsilion.proxy.bungee.injector;


import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.name.Names;
import es.obsilion.proxy.bungee.file.YAMLFile;
import es.obsilion.proxy.bungee.file.YAMLFileRegistry;
import es.obsilion.proxy.bungee.injector.module.StorageModule;
import es.obsilion.proxy.bungee.service.MainService;
import es.obsilion.proxy.core.service.Service;
import net.md_5.bungee.api.plugin.Plugin;

public class MainModule extends AbstractModule {

    private final Plugin plugin;

    public MainModule(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(Plugin.class).toInstance(plugin);

        YAMLFileRegistry fileRegistry = new YAMLFileRegistry()
                .bind("config", new YAMLFile(plugin, "config"));

        bind(Service.class).annotatedWith(Names.named("main-service")).to(MainService.class).in(Scopes.SINGLETON);

        install(fileRegistry.build());
        install(new StorageModule());
    }
}

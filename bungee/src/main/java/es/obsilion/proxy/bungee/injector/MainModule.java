package es.obsilion.proxy.bungee.injector;


import com.google.inject.AbstractModule;
import es.obsilion.proxy.bungee.file.YAMLFile;
import es.obsilion.proxy.bungee.file.YAMLFileRegistry;
import es.obsilion.proxy.bungee.injector.module.StorageModule;
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

        install(fileRegistry.build());
        install(new StorageModule());
    }
}

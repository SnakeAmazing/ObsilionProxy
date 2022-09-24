package es.obsilion.proxy.bungee.loader;

import com.google.inject.Inject;
import es.obsilion.proxy.bungee.ObsilionBungee;
import es.obsilion.proxy.bungee.listener.ProxyJoinListener;
import es.obsilion.proxy.bungee.listener.ServerConnectListener;
import net.md_5.bungee.api.plugin.Listener;

public class ListenersLoader implements Loader {

    @Inject private ObsilionBungee obsilionBungee;
    @Inject private ProxyJoinListener proxyJoinListener;
    @Inject private ServerConnectListener serverConnectListener;

    @Override
    public void load() {
        registerListeners(
                proxyJoinListener,
                serverConnectListener
        );
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            obsilionBungee.getProxy().getPluginManager().registerListener(obsilionBungee, listener);
        }
    }
}

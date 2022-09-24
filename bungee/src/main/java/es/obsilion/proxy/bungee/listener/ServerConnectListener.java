package es.obsilion.proxy.bungee.listener;

import com.google.inject.Inject;
import es.obsilion.proxy.bungee.user.BungeeUser;
import es.obsilion.proxy.bungee.util.Balancer;
import es.obsilion.proxy.core.user.UserManager;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ServerConnectListener implements Listener {

    @Inject
    private UserManager<BungeeUser> userManager;

    @EventHandler
    public void onServerConnectEvent(ServerConnectEvent event) {
        userManager.get(event.getPlayer().getUniqueId())
                .ifPresent(bungeeUser -> {
                    ServerInfo lessPopulated = Balancer.getLessPopulated();
                    if (event.getTarget().getName().equalsIgnoreCase(lessPopulated.getName())) {
                        bungeeUser.setLastServer(lessPopulated.getName());
                        return;
                    }

                    ProxiedPlayer proxiedPlayer = event.getPlayer();
                    proxiedPlayer.connect(lessPopulated);
                    bungeeUser.setCurrentServer(lessPopulated.getName());
                });


    }
}

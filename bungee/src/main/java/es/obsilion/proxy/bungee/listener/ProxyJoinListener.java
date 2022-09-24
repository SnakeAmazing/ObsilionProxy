package es.obsilion.proxy.bungee.listener;

import com.google.inject.Inject;
import es.obsilion.proxy.bungee.user.BungeeUser;
import es.obsilion.proxy.core.user.UserManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ProxyJoinListener implements Listener {

    @Inject
    private UserManager<BungeeUser> userManager;

    @EventHandler
    public void onProxyJoinListener(PostLoginEvent event) {
        ProxiedPlayer proxiedPlayer = event.getPlayer();

        if (userManager.isRegistered(proxiedPlayer.getUniqueId())) {
            userManager.load(proxiedPlayer.getUniqueId());
        } else {
            userManager.loadNewUser(proxiedPlayer.getDisplayName(), proxiedPlayer.getUniqueId());

        }
    }
}

package es.obsilion.proxy.bungee.util;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Balancer {
    private static final List<String> lobbies = new ArrayList<>();

    static {

        ProxyServer.getInstance().getServersCopy()
                .forEach((s, serverInfo) -> {
                    if (s.toLowerCase(Locale.ROOT).contains("lobby")) lobbies.add(s);
                });
    }

    public static ServerInfo getLessPopulated() {
        if (lobbies.isEmpty()) return ProxyServer.getInstance().getServerInfo("Lobby");

        ServerInfo lessPopulated = ProxyServer.getInstance().getServerInfo(lobbies.get(0));

        for (int i = 1; i < lobbies.size(); ++i) {
            if (lessPopulated.getPlayers().size() > ProxyServer.getInstance().getServerInfo(lobbies.get(i)).getPlayers().size())
                lessPopulated = ProxyServer.getInstance().getServerInfo(lobbies.get(i));
        }

        return lessPopulated;
    }
}

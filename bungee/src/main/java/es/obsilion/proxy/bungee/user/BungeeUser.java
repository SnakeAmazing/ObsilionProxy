package es.obsilion.proxy.bungee.user;

import es.obsilion.proxy.core.user.OnlineUser;

import java.util.UUID;

public class BungeeUser extends OnlineUser {

    public String currentServer;
    public String lastServer;

    public String partyIdentifier = null;

    public BungeeUser(UUID uuid, String name) {
        super(uuid, name);
    }

    public String getCurrentServer() {
        return currentServer;
    }

    public String getLastServer() {
        return lastServer;
    }

    public String getPartyIdentifier() {
        return partyIdentifier;
    }

    public void setCurrentServer(String currentServer) {
        this.currentServer = currentServer;
    }

    public void setLastServer(String lastServer) {
        this.lastServer = lastServer;
    }

    public void setPartyIdentifier(String partyIdentifier) {
        this.partyIdentifier = partyIdentifier;
    }
}

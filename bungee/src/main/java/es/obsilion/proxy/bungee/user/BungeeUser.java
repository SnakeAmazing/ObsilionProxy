package es.obsilion.proxy.bungee.user;

import es.obsilion.proxy.bungee.serialize.BungeeUserSerializable;
import es.obsilion.proxy.core.serialize.Serializable;
import es.obsilion.proxy.core.user.OnlineUser;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BungeeUser extends OnlineUser implements Serializable<BungeeUserSerializable> {

    public String currentServer;
    public String lastServer;

    public String partyIdentifier = null;

    public BungeeUser(String documentId, UUID uuid, String name) {
        super(documentId, uuid, name, 0, new HashSet<>());
        this.lastServer = null;
    }

    public BungeeUser(String documentId, UUID uuid, String name, int coins, Set<String> friends,
                    String lastServer) {
        super(documentId, uuid, name, coins, friends);
        this.lastServer = lastServer;
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

    @Override
    public BungeeUserSerializable serialize() {
        return new BungeeUserSerializable(documentId, uuid.toString(), name,
                coins, friends, lastServer);
    }
}

package es.obsilion.proxy.bungee.serialize;

import java.util.Set;

public class BungeeUserSerializable {

    private final String _id;

    private final String uuid;
    private final String name;

    private final int coins;

    private final Set<String> friends;

    private final String lastServer;

    public BungeeUserSerializable(String _id, String uuid, String name, int coins, Set<String> friends, String lastServer) {
        this._id = _id;
        this.uuid = uuid;
        this.name = name;
        this.coins = coins;
        this.friends = friends;
        this.lastServer = lastServer;
    }

    public String getDocumentId() {
        return _id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public int getCoins() {
        return coins;
    }

    public Set<String> getFriends() {
        return friends;
    }

    public String getLastServer() {
        return lastServer;
    }
}

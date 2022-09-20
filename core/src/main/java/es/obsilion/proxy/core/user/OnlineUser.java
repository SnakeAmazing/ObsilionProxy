package es.obsilion.proxy.core.user;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class OnlineUser extends User {

    private int coins;
    private final Set<String> friends = new HashSet<>();

    public OnlineUser(UUID uuid, String name) {
        super(uuid, name);
    }

    /**
     * Coins Getter
     * @return actual player coins
     */
    public int getCoins() {
        return coins;
    }

    /**
     * Modify the player coins.
     * @param amount is the amount that will be modified.
     */
    public void modifyCoins(int amount) {
        this.coins += amount;
    }

    /**
     * Get a copy list with the UUIDs (represented by String) of the player friends.
     * @return a list with string UUIDs.
     */
    public Set<String> getFriends() {
        return Set.copyOf(friends);
    }

    /**
     * Add a new uuid to player's friend list
     * @param uuid is the UUID of the new friend.
     */
    public void addFriend(UUID uuid) {
        friends.add(uuid.toString());
    }

    /**
     * Remove an uuid from the player's friend list
     * @param uuid representing the friend
     */
    public void removeFriend(UUID uuid) {
        friends.remove(uuid.toString());
    }
}

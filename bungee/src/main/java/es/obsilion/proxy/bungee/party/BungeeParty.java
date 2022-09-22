package es.obsilion.proxy.bungee.party;

import es.obsilion.proxy.core.party.Party;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BungeeParty implements Party {

    private final String identifier;

    private UUID leader;
    private int capacity;

    private Set<String> members;

    public BungeeParty(UUID leader, int capacity) {
        this.leader = leader;
        this.capacity = capacity;

        this.identifier = UUID.randomUUID().toString();
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public void add(UUID uuid) {
        this.members.add(uuid.toString());
    }

    @Override
    public void remove(UUID uuid) {
        this.members.remove(uuid.toString());
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int size() {
        return members.size();
    }

    @Override
    public UUID getLeader() {
        return leader;
    }

    @Override
    public void setLeader(UUID uuid) {
        this.leader = uuid;
    }

    @Override
    public Set<String> getMembers() {
        return Set.copyOf(members);
    }

    @Override
    public Set<String> getAllMembers() {
        Set<String> all = new HashSet<>(members);
        all.add(leader.toString());

        return all;
    }
}

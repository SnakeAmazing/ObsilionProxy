package es.obsilion.proxy.core.party;

import java.util.Set;
import java.util.UUID;

public interface Party {

    String getIdentifier();

    void add(UUID uuid);

    void remove(UUID uuid);

    int capacity();

    void setCapacity(int capacity);

    int size();

    UUID getLeader();

    void setLeader(UUID uuid);

    Set<String> getMembers();
}

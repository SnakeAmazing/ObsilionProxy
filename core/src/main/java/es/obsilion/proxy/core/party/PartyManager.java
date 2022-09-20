package es.obsilion.proxy.core.party;

public interface PartyManager<T> {

    Party create(T t);

    void disband(T t);

    void disband(Party party);

    Party get(T t);

    void sendMessage(T t, String message);
}

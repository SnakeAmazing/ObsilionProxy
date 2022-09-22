package es.obsilion.proxy.core.party;

public interface PartyManager<T, U> {

    T create(U u);

    boolean has(U u);

    void disband(T t);

    T get(U u);

    void sendMessage(U u, String message);
}

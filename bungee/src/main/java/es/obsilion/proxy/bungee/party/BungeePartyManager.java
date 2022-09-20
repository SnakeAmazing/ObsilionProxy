package es.obsilion.proxy.bungee.party;

import es.obsilion.proxy.bungee.user.BungeeUser;
import es.obsilion.proxy.core.party.Party;
import es.obsilion.proxy.core.party.PartyManager;

import java.util.HashMap;
import java.util.Map;

public class BungeePartyManager implements PartyManager<BungeeUser> {

    private final Map<String, Party> parties = new HashMap<>();

    @Override
    public Party create(BungeeUser bungeeUser) {
        Party party = new BungeeParty(bungeeUser.getUniqueId(), 5);
        parties.put(party.getIdentifier(), party);

        bungeeUser.setPartyIdentifier(party.getIdentifier());

        return party;
    }

    @Override
    public void disband(BungeeUser bungeeUser) {
        Party party = parties.get(bungeeUser.getPartyIdentifier());

        for (String string : party.getMembers()) {
            // Remove Users Identifier
        }

        //Remove leader identifier

        parties.remove(party.getIdentifier());
    }

    @Override
    public void disband(Party party) {
        for (String string : party.getMembers()) {
            // Remove Users Identifier
        }

        //Remove leader identifier

        parties.remove(party.getIdentifier());
    }

    @Override
    public Party get(BungeeUser bungeeUser) {
        return parties.get(bungeeUser.getPartyIdentifier());
    }

    @Override
    public void sendMessage(BungeeUser bungeeUser, String message) {
        Party party = parties.get(bungeeUser.getPartyIdentifier());

        for (String s : party.getMembers()) {

        }
    }
}

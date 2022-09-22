package es.obsilion.proxy.bungee.party;

import com.google.inject.Inject;
import es.obsilion.proxy.bungee.user.BungeeUser;
import es.obsilion.proxy.core.party.Party;
import es.obsilion.proxy.core.party.PartyManager;
import es.obsilion.proxy.core.user.UserManager;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BungeePartyManager implements PartyManager<Party, BungeeUser> {

    @Inject
    private UserManager<BungeeUser> userManager;

    private final Map<String, Party> parties = new HashMap<>();

    @Override
    public Party create(BungeeUser bungeeUser) {
        Party party = new BungeeParty(bungeeUser.getUniqueId(), 5);
        parties.put(party.getIdentifier(), party);

        bungeeUser.setPartyIdentifier(party.getIdentifier());

        return party;
    }

    @Override
    public boolean has(BungeeUser bungeeUser) {
        return bungeeUser.getPartyIdentifier() != null;
    }

    @Override
    public void disband(Party party) {
        for (String string : party.getAllMembers()) {
            userManager.get(UUID.fromString(string))
                    .ifPresent(bungeeUser -> {
                        bungeeUser.setPartyIdentifier(null);
                    });
        }

        parties.remove(party.getIdentifier());
    }

    @Override
    public Party get(BungeeUser bungeeUser) {
        return parties.get(bungeeUser.getPartyIdentifier());
    }

    @Override
    public void sendMessage(BungeeUser bungeeUser, String message) {
        Party party = parties.get(bungeeUser.getPartyIdentifier());

        for (String s : party.getAllMembers()) {
            ProxiedPlayer proxiedPlayer = ProxyServer.getInstance().getPlayer(UUID.fromString(s));
            proxiedPlayer.sendMessage(message);
        }
    }
}

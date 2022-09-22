package es.obsilion.proxy.bungee.injector.module;

import com.google.inject.*;
import com.google.inject.Module;
import es.obsilion.proxy.bungee.party.BungeePartyManager;
import es.obsilion.proxy.bungee.user.BungeeUser;
import es.obsilion.proxy.bungee.user.BungeeUserManager;
import es.obsilion.proxy.core.party.Party;
import es.obsilion.proxy.core.party.PartyManager;
import es.obsilion.proxy.core.user.UserManager;

public class ManagerModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(Key.get(new TypeLiteral<UserManager<BungeeUser>>() {})).to(BungeeUserManager.class).in(Scopes.SINGLETON);
        binder.bind(Key.get(new TypeLiteral<PartyManager<Party, BungeeUser>>() {})).to(BungeePartyManager.class).in(Scopes.SINGLETON);

    }
}

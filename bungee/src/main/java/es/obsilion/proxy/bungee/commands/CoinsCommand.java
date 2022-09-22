package es.obsilion.proxy.bungee.commands;

import com.google.inject.Inject;
import es.obsilion.proxy.bungee.user.BungeeUser;
import es.obsilion.proxy.core.user.UserManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class CoinsCommand extends Command {

    @Inject
    private UserManager<BungeeUser> userManager;

    public CoinsCommand() {
        super("coins");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            return;
        }

        userManager.get(args[0])
                .ifPresent(bungeeUser -> {
                    sender.sendMessage(TextComponent.fromLegacyText("Tiene: " + bungeeUser.getCoins() + " monedas"));
                });
    }
}

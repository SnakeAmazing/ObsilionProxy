package es.obsilion.proxy.bungee.commands;

import com.google.inject.Inject;
import es.obsilion.proxy.bungee.file.YAMLFile;
import es.obsilion.proxy.bungee.util.Balancer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import javax.inject.Named;

public class LobbyCommand extends Command {

    @Inject
    @Named("config")
    private YAMLFile config;

    public LobbyCommand() {
        super("lobby", null, "hub");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer player)) {
            return;
        }

        player.connect(Balancer.getLessPopulated());
    }
}

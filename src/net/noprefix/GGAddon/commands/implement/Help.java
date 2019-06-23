package net.noprefix.GGAddon.commands.implement;

import net.labymod.main.LabyMod;
import net.noprefix.GGAddon.GGAddon;
import net.noprefix.GGAddon.addons.GAddon;
import net.noprefix.GGAddon.commands.GCommand;
import net.noprefix.GGAddon.commands.GCommandManager;

import java.util.Iterator;

public class Help extends GCommand {
    public Help(String name, String description, String germanDescription) {
        super(name, description, germanDescription);
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1)
            GGAddon.getInstance().getChat().sendHelp(GGAddon.getInstance().getLanguage());
        else {
            if (args.length == 2) {
                for (GCommand cmd : GGAddon.getInstance().getCommandManager().getCommands())
                    if (args[1].equalsIgnoreCase(cmd.getName())) {
                        GGAddon.getInstance().getChat().sendMessage("§aVerwendung von §e" + cmd.getName() + " §8: §7" + cmd.getDescription());
                        return;
                    }
            }
            GGAddon.getInstance().getChat().noCommand("de");
        }
    }
}

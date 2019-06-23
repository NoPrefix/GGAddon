package net.noprefix.GGAddon.commands.implement;

import net.noprefix.GGAddon.GGAddon;
import net.noprefix.GGAddon.addons.implement.GrieferWert;
import net.noprefix.GGAddon.commands.GCommand;

public class Price extends GCommand {
    public Price(String name, String description, String germanDescription) {
        super(name, description, germanDescription);
    }

    @Override
    public void execute(String[] args) {
        /*if(GGAddon.getInstance().getAddonManager().getAddon(GrieferWert.class).isEnabled())
            GGAddon.getInstance().getChat().sendMessage("Grieferwert addon ist aktiviert!");
        else
            GGAddon.getInstance().getChat().sendMessage("Grieferwert addon ist nicht aktiviert!");*/
        GGAddon.getInstance().getChat().sendMessage("§cDer Grieferwert Support folgt in der nächsten Version!");
    }
}

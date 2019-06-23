package net.noprefix.GGAddon.utils;

import net.labymod.main.LabyMod;
import net.noprefix.GGAddon.GGAddon;
import net.noprefix.GGAddon.addons.GAddon;
import net.noprefix.GGAddon.commands.GCommand;

import java.util.Iterator;

public class ChatUtils {

    public void connectedToGG(String locale) {
        headerAndFooter();
        space();
        locale = "de";
        if (locale.toLowerCase().equals("de")) {
            sendMessage("§aDu bist nun auf GrieferGames verbunden!");
            sendMessage("§aFolgende Addon-Features wurden aktivert: ");
        } else {
            sendMessage("§aYou are now connected to GrieferGames!");
            sendMessage("§aThe Following Addon-Features were activated: ");
        }
        space();
        sendActivatedAddons(locale);
        space();
        headerAndFooter();
        //VersionChecker.checkVersion();
    }

    public void sendMessage(String message) {
        LabyMod.getInstance().displayMessageInChat(GGAddon.PREFIX + message);
    }

    public void sendMagic(String message) {
        LabyMod.getInstance().displayMessageInChat("§7[§6MagicPrefix§7] §r" + message);
    }

    public void space() {
        LabyMod.getInstance().displayMessageInChat(" ");
    }

    public void sendActivatedAddons(String language) {
        Object next;
        Iterator iterator = GGAddon.getInstance().getAddonManager().getAddons().iterator();
        while (iterator.hasNext()) {
            next = iterator.next();
            if (language.toLowerCase().equals("de"))
                sendMessage("§e" +
                        GGAddon.getInstance().getAddonManager().getAddon(next.getClass()).getDisplayname() + ", §aAktiviert: " +
                        String.valueOf(GGAddon.getInstance().getAddonManager().getAddon(next.getClass()).isEnabled())
                                .replace("false", "§cNein")
                                .replace("true", "§aJa"));
            else
                sendMessage("§e" +
                        GGAddon.getInstance().getAddonManager().getAddon(next.getClass()).getDisplayname() + ", §aActivated: " +
                        String.valueOf(GGAddon.getInstance().getAddonManager().getAddon(next.getClass()).isEnabled())
                                .replace("false", "§cno")
                                .replace("true", "§ayes"));
        }
    }

    public void headerAndFooter() {
        LabyMod.getInstance().displayMessageInChat(GGAddon.headerAndFooter);
    }

    public void enableAddon(GAddon addon, String language) {
        if (language.toLowerCase().equals("de"))
            sendMessage("§a" + addon.getDisplayname() + " wurde aktiviert!");
        else
            sendMessage("§a" + addon.getDisplayname() + " was activated!");
    }

    public void disableAddon(GAddon addon, String language) {
        if (language.toLowerCase().equals("de"))
            sendMessage("§c" + addon.getDisplayname() + " wurde deaktiviert!");
        else
            sendMessage("§c" + addon.getDisplayname() + " was deactivated!");
    }

    public void sendHelp(String language) {
        GGAddon.getInstance().getChat().headerAndFooter();
        GGAddon.getInstance().getChat().space();
        for (GCommand ga : GGAddon.getInstance().getCommandManager().getCommands())
            GGAddon.getInstance().getChat().sendMessage(ga.getName() + " §8- §7" + ga.getGermanDescription());
        GGAddon.getInstance().getChat().space();
        GGAddon.getInstance().getChat().headerAndFooter();
    }

    public void noCommand(String language) {
        if (language.toLowerCase().equals("de")) {
            sendMessage("§cDas ist kein Command! §7(" + GGAddon.getInstance().commandPrefix + " help)");
        } else
            sendMessage("§cThis is not a command! §7(" + GGAddon.getInstance().commandPrefix + " help)");
    }

}

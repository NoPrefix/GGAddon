package net.noprefix.GGAddon;

import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.*;
import net.labymod.utils.Material;
import net.noprefix.GGAddon.addons.GAddonManager;
import net.noprefix.GGAddon.commands.GCommandManager;
import net.noprefix.GGAddon.events.EventHandler;
import net.noprefix.GGAddon.events.LabyEventHandler;
import net.noprefix.GGAddon.utils.ChatUtils;
import net.noprefix.GGAddon.utils.LocaleManager;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class GGAddon extends LabyModAddon {

    private static GGAddon addon;
    private ChatUtils cu;
    private GAddonManager addonManager;
    private GCommandManager commandManager;

    private ScheduledThreadPoolExecutor executor;

    private String language, serverIP;
    public String commandPrefix;
    private boolean enabled = true;
    public static String headerAndFooter, PREFIX;
    public static int version = 1;


    @Override
    public void onEnable() {
        init();
        new LabyEventHandler().registerEvents();
        this.getApi().registerForgeListener(new EventHandler());
        System.out.println("[GGAddon] Successfully enabled GrieferGames-Addon!");
        System.out.println("[GGAddon] Current language: " + language);
        System.out.println("--- ADDONLIST --");
        Iterator it = getAddonManager().getAddons().iterator();
        while (it.hasNext())
            System.out.println(getAddonManager().getAddon(it.next().getClass()).getDisplayname() + " §aDisplayname!");
        System.out.println("--- ADDONLIST --\n\n");
    }

    @Override
    public void loadConfig() {
        Object next;
        Iterator iterator = getAddonManager().getAddons().iterator();
        while (iterator.hasNext()) {
            next = iterator.next();
            boolean before = getAddonManager().getAddon(next.getClass()).enabled;
            getAddonManager().getAddon(next.getClass()).enabled =
                    getConfig().has("enabled" + getAddonManager().getAddon(next.getClass()).getName()) ?
                            getConfig().get("enabled" + getAddonManager().getAddon(next.getClass()).getName()).getAsBoolean()
                            : getAddonManager().getAddon(next.getClass()).default_enabled;
            if (before != getAddonManager().getAddon(next.getClass()).enabled)
                if (before == false)
                    getAddonManager().getAddon(next.getClass()).onEnable();
                else
                    getAddonManager().getAddon(next.getClass()).onDisable();
        }
        this.commandPrefix = getConfig().has("commandPrefix") ? getConfig().get("commandPrefix").getAsString() : "#gg";
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        Object next;
        Iterator iterator = getAddonManager().getAddons().iterator();
        list.add(new net.labymod.settings.elements.HeaderElement("Allgemeines"));
        while (iterator.hasNext()) {
            next = iterator.next();
            list.add(new BooleanElement(
                    getAddonManager().getAddon(next.getClass()).getDisplayname(), this,
                    new ControlElement.IconData(getAddonManager().getAddon(next.getClass()).getMaterial()),
                    "enabled" + getAddonManager().getAddon(next.getClass()).getName(),
                    getAddonManager().getAddon(next.getClass()).enabled));

        }
        list.add(new StringElement("Command Prefix", this, new ControlElement.IconData(Material.COMMAND), "commandPrefix", commandPrefix));
    }

    private void init() {
        addon = this;
        cu = new ChatUtils();
        addonManager = new GAddonManager();
        commandManager = new GCommandManager();

        executor = new ScheduledThreadPoolExecutor(2);

        language = new LocaleManager().getLanguage();
        serverIP = "0";
        headerAndFooter = "§8§m----------§r §7[§6§lGrieferGamesAddon§7] §8§m----------";
        PREFIX = "§7[§6GGAddon§7] §r";
    }

    public static GGAddon getInstance() {
        return addon;
    }

    public ChatUtils getChat() {
        return cu;
    }

    public GAddonManager getAddonManager() {
        return addonManager;
    }

    public GCommandManager getCommandManager() {
        return commandManager;
    }

    public ScheduledThreadPoolExecutor getExecutor() {
        return executor;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public String getLanguage() {
        return language;
    }

    public void connect() {
        if (serverIP.toLowerCase().endsWith("griefergames.net"))
            getChat().connectedToGG(language);
    }
}

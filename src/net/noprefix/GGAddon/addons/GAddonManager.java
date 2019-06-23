package net.noprefix.GGAddon.addons;

import net.labymod.utils.Material;
import net.noprefix.GGAddon.addons.implement.*;

import java.util.ArrayList;
import java.util.List;

public class GAddonManager {

    private ArrayList<GAddon> addons = new ArrayList();

    public GAddonManager() {
        addAddon(new GrieferWert("grieferwert", "GrieferWert Support", Material.BEACON, false));
        addAddon(new AntiFakeMoney("antiFakeMoney", "Anti Fake Money", Material.GOLD_NUGGET, true));
        addAddon(new ToggleChat("toggleChat", "Toggle Chat", Material.BOOK, false));
        addAddon(new AntiMagic("antiMagic", "NoMagicPrefix", Material.STAINED_GLASS_PANE, false));
    }

    public void addAddon(GAddon addon) {
        this.addons.add(addon);
    }

    public GAddon getAddon(Class<?> clazz) {
        for (GAddon addon : addons) {
            if (addon.getClass() == clazz)
                return addon;
        }
        return null;
    }

    public GAddon getAddonByName(String name) {
        for (GAddon addon : addons) {
            if ((addon.getName().trim().equalsIgnoreCase(name)) || (addon.getName().trim().equalsIgnoreCase(name.trim()))) {
                return addon;
            }
        }
        return null;
    }

    public List<GAddon> getAddons() {
        return addons;
    }
}

package net.noprefix.GGAddon.addons;

import net.labymod.main.LabyMod;
import net.labymod.utils.Material;
import net.minecraft.client.Minecraft;
import net.noprefix.GGAddon.GGAddon;

public abstract class GAddon {

    private String name, displayname;
    public boolean enabled;
    private Material material;
    public boolean default_enabled;
    public Minecraft mc = Minecraft.getMinecraft();

    public GAddon(String name, String displayname, Material material, boolean default_enabled) {
        this.name = name;
        this.displayname = displayname;
        this.material = material;
        this.default_enabled = default_enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void toggle() {
        enabled = !enabled;
        if (enabled)
            onEnable();
        else
            onDisable();
    }

    public void onEnable() {
        if (LabyMod.getInstance().isInGame())
            GGAddon.getInstance().getChat().sendMessage("§a" + displayname + " wurde aktiviert!");
    }

    public void onDisable() {
        GGAddon.getInstance().getChat().sendMessage("§a" + displayname + " wurde §cdeaktiviert!");
    }

    public abstract void onUpdate();

}

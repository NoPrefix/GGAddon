package net.noprefix.GGAddon.commands;

import net.minecraft.client.Minecraft;

public abstract class GCommand {

    private String name, description, germanDescription;
    public Minecraft mc = Minecraft.getMinecraft();

    public GCommand(String name, String usage, String germanDescription) {
        this.name = name;
        this.description = usage;
        this.germanDescription = germanDescription;
    }

    public abstract void execute(String[] args);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGermanDescription() {
        return germanDescription;
    }

    public void setGermanDescription(String germanDescription) {
        this.germanDescription = germanDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

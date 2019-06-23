package net.noprefix.GGAddon.commands.implement;

import net.minecraft.client.Minecraft;
import net.noprefix.GGAddon.addons.GAddon;
import net.noprefix.GGAddon.commands.GCommand;

public class ClearChat extends GCommand {

    public ClearChat(String name, String usage, String germanDescription) {
        super(name, usage, germanDescription);
    }

    @Override
    public void execute(String[] args) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().clearChatMessages();
    }
}

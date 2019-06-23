package net.noprefix.GGAddon.events;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.noprefix.GGAddon.GGAddon;
import net.noprefix.GGAddon.addons.GAddon;

import javax.swing.text.JTextComponent;

public class EventHandler {

    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        for(GAddon addon : GGAddon.getInstance().getAddonManager().getAddons())
            if(addon.isEnabled())
                addon.onUpdate();
    }

}

package net.noprefix.GGAddon.events;

import net.labymod.api.events.MessageReceiveEvent;
import net.labymod.api.events.MessageSendEvent;
import net.labymod.ingamechat.namehistory.NameHistory;
import net.labymod.utils.Consumer;
import net.labymod.utils.ServerData;
import net.noprefix.GGAddon.GGAddon;
import net.noprefix.GGAddon.addons.implement.AntiFakeMoney;
import net.noprefix.GGAddon.addons.implement.AntiMagic;
import net.noprefix.GGAddon.addons.implement.ToggleChat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LabyEventHandler {

    public List<String> renamed = new ArrayList();
    private NameHistory nameHistory;
    private boolean lastSearchNotFound;

    public void registerEvents() {
        rJoinEvent();
        rQuitEvent();
        rChatEvent();
        rIncomingMessageEvent();
    }

    public void rJoinEvent() {
        GGAddon.getInstance().getApi().getEventManager().registerOnJoin(new Consumer<ServerData>() {
            @Override
            public void accept(final ServerData serverData) {
                /*GGAddon.getInstance().getExecutor().schedule(() -> {
                    GGAddon.getInstance().setServerIP(serverData.getIp());
                    GGAddon.getInstance().connect();
                    GGAddon.getInstance().loadConfig();
                }, 2, TimeUnit.SECONDS);*/
                GGAddon.getInstance().getExecutor().schedule(new Runnable() {
                    @Override
                    public void run() {
                        GGAddon.getInstance().setServerIP(serverData.getIp());
                        GGAddon.getInstance().connect();
                        GGAddon.getInstance().loadConfig();
                    }
                }, 2, TimeUnit.SECONDS);
            }
        });
    }

    public void rQuitEvent() {
        GGAddon.getInstance().getApi().getEventManager().registerOnQuit(new Consumer<ServerData>() {
            @Override
            public void accept(final ServerData serverData) {
                //GGAddon.getInstance().getClient().disconnectFromChat();
            }
        });
    }

    public void rChatEvent() {
        GGAddon.getInstance().getApi().getEventManager().register(new MessageSendEvent() {
            @Override
            public boolean onSend(String s) {
                if (s.startsWith(GGAddon.getInstance().commandPrefix) && s.split(" ").length > 1) {
                    GGAddon.getInstance().getCommandManager().execute(s);
                    return true;
                } else if (s.startsWith(GGAddon.getInstance().commandPrefix)) {
                    GGAddon.getInstance().getChat().noCommand(GGAddon.getInstance().getLanguage());
                    return true;
                }
                return false;
            }
        });
    }

    public void rIncomingMessageEvent() {
        GGAddon.getInstance().getApi().getEventManager().register(new MessageReceiveEvent() {
            @Override
            public boolean onReceive(String s, String s1) {
                if (GGAddon.getInstance().getAddonManager().getAddon(AntiFakeMoney.class).isEnabled()) {
                    if(s.contains("hat dir") && s.contains("gegeben")) {
                        if(!s.contains(":") && s.endsWith("gegeben.§r") && s.contains("§r§ahat dir $")) {
                            GGAddon.getInstance().getChat().sendMessage("§aReal-Money von §e" + s.split(" ")[2]);
                        } else
                            GGAddon.getInstance().getChat().sendMessage("§cFake-Money von §e" + s.split(" ")[2]);
                    }
                }

                if(GGAddon.getInstance().getAddonManager().getAddon(AntiMagic.class).isEnabled()) {
                    if(s.contains("§k")) {
                        GGAddon.getInstance().getChat().sendMagic(s.replaceAll("§k", ""));
                        return true;
                    }
                }

                if(GGAddon.getInstance().getAddonManager().getAddon(ToggleChat.class).isEnabled()) {
                    return true;
                }
                return false;
            }
        });
    }
}

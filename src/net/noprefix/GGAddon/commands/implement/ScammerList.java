package net.noprefix.GGAddon.commands.implement;

import net.noprefix.GGAddon.GGAddon;
import net.noprefix.GGAddon.commands.GCommand;
import net.noprefix.GGAddon.commands.GCommandManager;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ScammerList extends GCommand {

    private List<String> scammer = new ArrayList();

    public ScammerList(String name, String description, String germanDescription) {
        super(name, description, germanDescription);
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 2 && args[1].equalsIgnoreCase("add"))
            addScammer(args[2]);
        else if (args.length > 2 && args[1].equalsIgnoreCase("remove"))
            removeScammer(args[2]);
        else if (args.length > 2 && args[1].equalsIgnoreCase("search"))
            searchScammer(args[2]);
        else if (args.length > 1 && args[1].equalsIgnoreCase("list"))
            showScammer();
        else
            GGAddon.getInstance().getChat().sendMessage("§cBitte verwende: §a" + this.getDescription());
    }

    private void addScammer(String name) {
        if (scammer.contains(name))
            GGAddon.getInstance().getChat().sendMessage("§aScammer §e" + name + " §aist §abereits §avorhanden!");
        else {
            scammer.add(name);
            GGAddon.getInstance().getChat().sendMessage("§aScammer §ahinzugefügt §e" + name + "§a!");
            try {
                FileWriter writer = new FileWriter("scammerList.gg");
                for (String str : scammer)
                    writer.write(str + "\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeScammer(String name) {
        if (scammer.contains(name)) {
            scammer.remove(scammer.indexOf((name)));
            GGAddon.getInstance().getChat().sendMessage("§aScammer §e" + name + " §aentfernt!");
            try {
                FileWriter writer = new FileWriter("scammerList.gg");
                for (String str : scammer)
                    writer.write(str + "\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            GGAddon.getInstance().getChat().sendMessage("§aScammer §e" + name + " §aexistiert §anicht!");
    }

    private void showScammer() {
        GGAddon.getInstance().getChat().sendMessage("§e§lScammer-Liste");
        GGAddon.getInstance().getChat().sendMessage("§8§m-------------------");
        try {
            scammer = Files.readAllLines(Paths.get("scammerList.gg"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (scammer.size() > 0)
            for (int i = 0; i < scammer.size(); i++)
                GGAddon.getInstance().getChat().sendMessage("§e" + (i + 1) + ". " + "§a" + scammer.get(i));
        else
            GGAddon.getInstance().getChat().sendMessage("§aFüge §aScammer §amit §e#scammer add <name> §ahinzu!");
        GGAddon.getInstance().getChat().sendMessage("§8§m-------------------");
    }

    private void searchScammer(String name) {
        if (scammer.contains(name))
            GGAddon.getInstance().getChat().sendMessage("§aScammer §e" + name + " §aist §avorhanden!");
        else
            GGAddon.getInstance().getChat().sendMessage("§aScammer §e" + name + " §aexistiert §anicht!");
    }
}

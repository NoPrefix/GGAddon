package net.noprefix.GGAddon.commands.implement;

import net.noprefix.GGAddon.GGAddon;
import net.noprefix.GGAddon.commands.GCommand;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TrustList extends GCommand {

    private List<String> trust = new ArrayList();

    public TrustList(String name, String description, String germanDescription) {
        super(name, description, germanDescription);
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 2 && args[1].equalsIgnoreCase("add"))
            addTrust(args[2]);
        else if (args.length > 2 && args[1].equalsIgnoreCase("remove"))
            removeTrust(args[2]);
        else if (args.length > 2 && args[1].equalsIgnoreCase("search"))
            searchTrust(args[2]);
        else if (args.length > 1 && args[1].equalsIgnoreCase("list"))
            showTrust();
        else
            GGAddon.getInstance().getChat().sendMessage("§cBitte verwende: §a" + this.getDescription());
    }

    private void addTrust(String name) {
        if (trust.contains(name))
            GGAddon.getInstance().getChat().sendMessage("§aVertrauter §e" + name + " §aist §abereits §avorhanden!");
        else {
            trust.add(name);
            GGAddon.getInstance().getChat().sendMessage("§aVertrauter §ahinzugefügt §e" + name + "§a!");
            try {
                FileWriter writer = new FileWriter("trustList.gg");
                for (String str : trust)
                    writer.write(str + "\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeTrust(String name) {
        if (trust.contains(name)) {
            trust.remove(trust.indexOf((name)));
            GGAddon.getInstance().getChat().sendMessage("§aVertrauter §e" + name + " §aentfernt!");
            try {
                FileWriter writer = new FileWriter("trustList.gg");
                for (String str : trust)
                    writer.write(str + "\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            GGAddon.getInstance().getChat().sendMessage("§aVertrauter §e" + name + " §aexistiert §anicht!");
    }

    private void showTrust() {
        GGAddon.getInstance().getChat().sendMessage("§e§lTrust-Liste");
        GGAddon.getInstance().getChat().sendMessage("§8§m-------------------");
        try {
            trust = Files.readAllLines(Paths.get("trustList.gg"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (trust.size() > 0)
            for (int i = 0; i < trust.size(); i++)
                GGAddon.getInstance().getChat().sendMessage("§e" + (i + 1) + ". " + "§a" + trust.get(i));
        else
            GGAddon.getInstance().getChat().sendMessage("§aFüge §aVertraute §amit §e#trust add <name> §ahinzu!");
        GGAddon.getInstance().getChat().sendMessage("§8§m-------------------");
    }

    private void searchTrust(String name) {
        if (trust.contains(name))
            GGAddon.getInstance().getChat().sendMessage("§aVertrauter §e" + name + " §aist §avorhanden!");
        else
            GGAddon.getInstance().getChat().sendMessage("§aVertrauter §e" + name + " §aexistiert §anicht!");
    }
}

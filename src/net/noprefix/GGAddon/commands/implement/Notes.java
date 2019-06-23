package net.noprefix.GGAddon.commands.implement;

import net.labymod.main.LabyMod;
import net.noprefix.GGAddon.GGAddon;
import net.noprefix.GGAddon.commands.GCommand;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Notes extends GCommand {

    private List<String> notes = new ArrayList();

    public Notes(String name, String description, String germanDescription) {
        super(name, description, germanDescription);
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 2 && args[1].equalsIgnoreCase("add"))
            addNote(args);
        else if (args.length > 2 && args[1].equalsIgnoreCase("remove"))
            removeNote(args[2]);
        else if (args.length > 2 && args[1].equalsIgnoreCase("show"))
            showNote(args[2]);
        else if (args.length > 1 && args[1].equalsIgnoreCase("list"))
            listNotes();
        else
            GGAddon.getInstance().getChat().sendMessage("§cBitte verwende: §a" + this.getDescription());
    }

    private void addNote(String[] note) {
        if (notes.contains(note[2])) {
            GGAddon.getInstance().getChat().sendMessage("§aNotiz §e" + note[2] + " §aist §abereits §avorhanden!");
        } else {
            notes.add(note[2]);
            try {
                FileWriter writer = new FileWriter("noteList_save.txt");
                for (String str : notes) {
                    writer.write(str + "\n");
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                FileWriter writer = new FileWriter(note[2] + ".txt");
                for (int i = 2; i < note.length; i++) {
                    writer.write(note[i] + " ");
                }
                writer.close();
                GGAddon.getInstance().getChat().sendMessage("§aNotiz §e" + note[2] + " §ahinzugefügt!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeNote(String note) {
        if (notes.contains(note)) {
            notes.remove(notes.indexOf((note)));
            GGAddon.getInstance().getChat().sendMessage("§aNotiz §e" + note + " §aentfernt!");
            try {
                Files.delete(Paths.get(note + ".txt"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FileWriter writer = new FileWriter("noteList_save.txt");
                for (String str : notes) {
                    writer.write(str + "\n");
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            GGAddon.getInstance().getChat().sendMessage("§aNotiz §e" + note + " §aexistiert §anicht!");
        }
    }

    private void showNote(String note) {
        try {
            List<String> notes = new ArrayList();
            notes = Files.readAllLines(Paths.get(note + ".txt"));
            GGAddon.getInstance().getChat().sendMessage("§e§lNotiz: " + note);
            GGAddon.getInstance().getChat().sendMessage("§8§m-------------------");

            if (notes.size() > 0) {
                String names = "";
                for (int i = 0; i < notes.size(); i++)
                    names = names.concat("§a" + notes.get(i) + " ");
                LabyMod.getInstance().displayMessageInChat(names.substring(0, names.length() - 1));
            }
            GGAddon.getInstance().getChat().sendMessage("§8§m-------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listNotes() {
        GGAddon.getInstance().getChat().sendMessage("§e§lNotiz-Liste");
        GGAddon.getInstance().getChat().sendMessage("§8§m-------------------");
        try {
            notes = Files.readAllLines(Paths.get("noteList_save.txt"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if (notes.size() > 0) {
            String names = "";
            for (int i = 0; i < notes.size(); i++) {
                names = names.concat("§a" + notes.get(i) + "§e, ");

            }
            LabyMod.getInstance().displayMessageInChat(names.substring(0, names.length() - 4));
        } else {
            GGAddon.getInstance().getChat().sendMessage("§aFüge §aNotizen §amit \"§e#gg note add §7<§edeine §eNotiz §ehier§7>§a\" §ahinzu!");
        }
        GGAddon.getInstance().getChat().sendMessage("§8§m-------------------");
    }

}

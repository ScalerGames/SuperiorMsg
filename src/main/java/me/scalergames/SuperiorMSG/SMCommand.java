package me.scalergames.SuperiorMSG;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SMCommand implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
        if (label.equalsIgnoreCase("sm")) {
            if (s.hasPermission("sm.command")) {

                //If command has no argument
                if (args.length == 0) {
                    s.sendMessage(Color.format("&c/sm [reload, version]"));
                }

                //If command has an argument
                if (args.length >= 1) {

                    if (args[0].equalsIgnoreCase("reload")) {
                        if (s.hasPermission("sm.command.reload")) {
                            Main.getInstance().reloadConfig();;
                            s.sendMessage(Color.format("&3You reloaded SuperiorMSG"));
                        } else {
                            s.sendMessage(Color.format("&cYou do not have permission to reload SuperiorMSG"));
                        }
                    }

                    if (args[0].equalsIgnoreCase("version")) {
                        if (s.hasPermission("sm.command.version")) {
                            s.sendMessage(Color.format("&3You are running version "
                                    + Main.getInstance().getDescription().getVersion() + " of SuperiorMSG"));
                        } else {
                            s.sendMessage(Color.format("&cYou do not have permission to check the version of SuperiorMSG"));
                        }
                    }

                    if (args[0].equalsIgnoreCase("help")) {
                        s.sendMessage(Color.format("&8&l&m======&3SuperiorMSG&8&l&m======" + "\n&9SuperiorMSG is a plugin"
                                + " which replaces some of the default minecraft messages."
                                + "\nThe Commands are /sm, /msg, /broadcast, /localbroadcast\n/broadcastworld"
                                + " if you need help with the plugin please join the discord and check\n"
                                + " the wiki for more info.").replace("\\n", "\n"));
                    }

                    if (args[0].equalsIgnoreCase("wiki")) {
                        s.sendMessage(Color.format("&3Wiki: &9https://github.com/ScalerGames/SuperiorMSG"));
                    }

                    if (args[0].equalsIgnoreCase("discord")) {
                        s.sendMessage(Color.format("&3Discord: &9https://discord.gg/Ztm5WuBMpq"));
                    }
                }
            }
        }
        return false;
    }
}

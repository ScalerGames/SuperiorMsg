package me.scalergames.SuperiorMSG;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PM implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
        if (label.equalsIgnoreCase("msg")) {
            if (s instanceof Player) {
                Player p = (Player) s;
                if (p.hasPermission("sm.msg")) {
                    if (args.length == 0) {
                        p.sendMessage(Color.format("&cUsage: /msg [player] [message]"));
                    } else {
                        Player reciever = Bukkit.getPlayer(args[0]);
                        if (reciever != null) {
                            String message = StringUtils.join(Arrays.copyOfRange(args, 1, args.length), " ");
                            reciever.sendMessage(Color.format(Main.getInstance().getConfig().getString("PMRecieve"))
                                    .replace("{sender}", p.getDisplayName()).replace("{reciever}", reciever.getDisplayName())
                                    .replace("{message}", message));
                            p.sendMessage(Color.format(Main.getInstance().getConfig().getString("PMSend"))
                                    .replace("{reciever}", reciever.getDisplayName()).replace("{sender}", p.getDisplayName())
                                    .replace("{message}", message));
                        }

                        else if(!(reciever != null)) {
                            s.sendMessage(Color.format("&cThat player is not online!"));
                        }
                    }
                    return true;
                }
            } else {
                if (args.length == 0) {
                    s.sendMessage(Color.format("&cUsage: /msg [player] [message]"));
                } else {
                    Player reciever = Bukkit.getPlayer(args[0]);
                    if (reciever != null) {
                        String message = StringUtils.join(Arrays.copyOfRange(args, 1, args.length), " ");
                        reciever.sendMessage(Color.format(Main.getInstance().getConfig().getString("PMSend"))
                                .replace("{reciever}", reciever.getDisplayName()).replace("{sender}", Color.format("&cConsole&r"))
                                .replace("{message}", message));
                        s.sendMessage(Color.format(Main.getInstance().getConfig().getString("PMRecieve")
                                .replace("{reciever}", reciever.getDisplayName()).replace("{sender}", Color.format("&cConsole&r"))
                                .replace("{message}", message)));

                    }
                    else if(!(reciever != null)) {
                        s.sendMessage(Color.format("&cThat player is not online!"));
                    }
                }
            }
        }
        return false;
    }
}

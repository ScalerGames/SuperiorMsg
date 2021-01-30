package me.scalergames.SuperiorMSG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class BroadcastLocal implements CommandExecutor {

    private final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    private String format(String msg) {
        if (Bukkit.getVersion().contains("1.16")) {
            Matcher match = pattern.matcher(msg);
            while (match.find()) {
                String color = msg.substring(match.start(), match.end());
                msg = msg.replace(color, ChatColor.of(color) + "");
                match = pattern.matcher(msg);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("localbroadcast")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("bp.localbroadcast")) {
                    if (args.length == 0) {
                        player.sendMessage(format("&cUsage: /localbroadcast <methord> <range> <message>"));
                        return true;
                    }
                    String bprefix = Main.getInstance().getConfig().getString("BroadcastPrefix");
                    String message = StringUtils.join(Arrays.copyOfRange(args, 1, args.length), " ");
                    if (args.length >= 1) {
                        if (args[0].equalsIgnoreCase("chat")) {
                            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                                List<Player> list = new ArrayList<Player>();
                                Location source = player.getLocation();

                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.getLocation().distance(source) < Main.getInstance().getConfig().getInt("LocalBroadcastRange")) {
                                        list.add(p);
                                    }
                                }

                                for (Player p : list) {
                                    if (p != null && p.isOnline()) {
                                        String withplace = PlaceholderAPI.setPlaceholders(p, message);
                                        p.sendMessage(format(bprefix) + " " + ChatColor.RESET + format(withplace).replace("\\n", "\n"));
                                    }
                                }
                                list.clear();
                                list = null;
                            } else {
                                List<Player> list = new ArrayList<Player>();
                                Location source = player.getLocation();

                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.getLocation().distance(source) < Main.getInstance().getConfig().getInt("LocalBroadcastRange")) {
                                        list.add(p);
                                    }
                                }

                                for (Player p : list) {
                                    if (p != null && p.isOnline()) {
                                        p.sendMessage(format(bprefix) + " " + ChatColor.RESET + format(message).replace("\\n", "\n"));
                                    }
                                }
                                list.clear();
                                list = null;
                            }

                        }
                        if (args[0].equalsIgnoreCase("bar")) {
                            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                                List<Player> list = new ArrayList<Player>();
                                Location source = player.getLocation();

                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.getLocation().distance(source) < Main.getInstance().getConfig().getInt("LocalBroadcastRange")) {
                                        list.add(p);
                                    }
                                }

                                for (Player p : list) {
                                    if (p != null && p.isOnline()) {
                                        String withplace = PlaceholderAPI.setPlaceholders(p, message);
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(format(withplace)));
                                    }
                                }
                                list.clear();
                                list = null;
                            } else {
                                List<Player> list = new ArrayList<Player>();
                                Location source = player.getLocation();

                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.getLocation().distance(source) < Main.getInstance().getConfig().getInt("LocalBroadcastRange")) {
                                        list.add(p);
                                    }
                                }

                                for (Player p : list) {
                                    if (p != null && p.isOnline()) {
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(format(message)));
                                    }
                                }
                                list.clear();
                                list = null;
                            }
                        }
                        if (args[0].equalsIgnoreCase("title")) {
                            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                                List<Player> list = new ArrayList<Player>();
                                Location source = player.getLocation();

                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.getLocation().distance(source) < Main.getInstance().getConfig().getInt("LocalBroadcastRange")) {
                                        list.add(p);
                                    }
                                }

                                for (Player p : list) {
                                    if (p != null && p.isOnline()) {
                                        String withplace = PlaceholderAPI.setPlaceholders(p, message);
                                        p.sendTitle(format(withplace), null, Main.getInstance().getConfig().getInt("TitleFadeIn"), Main.getInstance().getConfig().getInt("TitleDisplayTime"), Main.getInstance().getConfig().getInt("TitleFadeOut"));
                                    }
                                }
                                list.clear();
                                list = null;
                            } else {
                                List<Player> list = new ArrayList<Player>();
                                Location source = player.getLocation();

                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.getLocation().distance(source) < Main.getInstance().getConfig().getInt("LocalBroadcastRange")) {
                                        list.add(p);
                                    }
                                }

                                for (Player p : list) {
                                    if (p != null && p.isOnline()) {
                                        p.sendTitle(format(message), null, Main.getInstance().getConfig().getInt("TitleFadeIn"), Main.getInstance().getConfig().getInt("TitleDisplayTime"), Main.getInstance().getConfig().getInt("TitleFadeOut"));
                                    }
                                }
                                list.clear();
                                list = null;
                            }
                        }
                        if (args[0].equalsIgnoreCase("boss")) {
                            player.sendMessage(format("&cThe boss bar is not ready yet."));
                        }
                        if (args[0].equalsIgnoreCase("board")) {
                            player.sendMessage(format("&cThe scoreboard broadcast is not ready yet."));
                        }
                    }

                }
            }
        }
        return false;
    }
}

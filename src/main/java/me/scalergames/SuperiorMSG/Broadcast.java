package me.scalergames.SuperiorMSG;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class Broadcast implements CommandExecutor {

    FileConfiguration config;

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
        if (label.equalsIgnoreCase("broadcast")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("bp.broadcast")) {
                    if (args.length == 0) {
                        player.sendMessage(format("&cUsage: /broadcast <method> <message>"));
                        return true;
                    }
                    if (args.length >= 1) {
                        if (args[0].equalsIgnoreCase("chat")) {
                            String bprefix = Main.getInstance().getConfig().getString("BroadcastPrefix");
                            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    String args1 = StringUtils.join(Arrays.copyOfRange(args, 1, args.length), " ");
                                    String withplace = PlaceholderAPI.setPlaceholders(p, args1);
                                    p.sendMessage(format(bprefix) + " " + ChatColor.RESET + format(withplace).replace("\\n", "\n"));
                                }
                            } else {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    String args1 = StringUtils.join(Arrays.copyOfRange(args, 1, args.length), " ");
                                    p.sendMessage(format(bprefix) + " " + ChatColor.RESET + format(args1).replace("\\n", "\n"));
                                }
                            }
                        }
                        if (args[0].equalsIgnoreCase("bar")) {
                            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    String args1 = StringUtils.join(Arrays.copyOfRange(args, 1, args.length), " ");
                                    String withplace = PlaceholderAPI.setPlaceholders(p, args1);
                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(format(withplace).replace("\\n", "\n")));
                                }
                            } else {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    String args1 = StringUtils.join(Arrays.copyOfRange(args, 1, args.length), " ");
                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(format(args1).replace("\\n", "\n")));
                                }
                            }
                        }
                        if (args[0].equalsIgnoreCase("title")) {
                            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    String args1 = StringUtils.join(Arrays.copyOfRange(args, 1, args.length), " ");
                                    String withplace = PlaceholderAPI.setPlaceholders(p, args1);
                                    p.sendTitle(format(withplace), null, Main.getInstance().getConfig().getInt("TitleFadeIn"), Main.getInstance().getConfig().getInt("TitleDisplayTime"), Main.getInstance().getConfig().getInt("TitleFadeOut"));
                                }
                            } else {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    String args1 = StringUtils.join(Arrays.copyOfRange(args, 1, args.length), " ");
                                    p.sendTitle(format(args1), null, Main.getInstance().getConfig().getInt("TitleFadeIn"), Main.getInstance().getConfig().getInt("TitleDisplayTime"), Main.getInstance().getConfig().getInt("TitleFadeOut"));
                                }
                            }
                        }
                        if (args[0].equalsIgnoreCase("boss")) {
                            player.sendMessage(format("&cThis feature is not ready yet."));
                        }
                        if (args[0].equalsIgnoreCase("board")) {
                            player.sendMessage(format("&cThis feature is not ready yet."));
                        }
                    }
                } else {
                    player.sendMessage(format("&cYou do not have permission to do /broadcast!"));
                }
            } else {
                if (args.length == 0) {
                    sender.sendMessage(format("&cUsage: /broadcast <method> <message>"));
                    return true;
                }
                if (args.length >= 1) {
                    if (args[0].equalsIgnoreCase("chat")) {
                        String bprefix = Main.getInstance().getConfig().getString("BroadcastPrefix");
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                String args1 = StringUtils.join(Arrays.copyOfRange(args, 1, args.length), " ");
                                String withplace = PlaceholderAPI.setPlaceholders(p, args1);
                                p.sendMessage(format(bprefix) + " " + ChatColor.RESET + format(withplace).replace("\\n", "\n"));
                            }
                        } else {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                String args1 = StringUtils.join(Arrays.copyOfRange(args, 1, args.length), " ");
                                p.sendMessage(format(bprefix) + " " + ChatColor.RESET + format(args1).replace("\\n", "\n"));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("bar")) {
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                String args1 = StringUtils.join(Arrays.copyOfRange(args, 1, args.length), " ");
                                String withplace = PlaceholderAPI.setPlaceholders(p, args1);
                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(format(withplace).replace("\\n", "\n")));
                            }
                        } else {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                String args1 = StringUtils.join(Arrays.copyOfRange(args, 1, args.length), " ");
                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(format(args1).replace("\\n", "\n")));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("title")) {
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                String args1 = StringUtils.join(Arrays.copyOfRange(args, 1, args.length), " ");
                                String withplace = PlaceholderAPI.setPlaceholders(p, args1);
                                p.sendTitle(format(withplace), null, Main.getInstance().getConfig().getInt("TitleFadeIn"), Main.getInstance().getConfig().getInt("TitleDisplayTime"), Main.getInstance().getConfig().getInt("TitleFadeOut"));
                            }
                        } else {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                String args1 = StringUtils.join(Arrays.copyOfRange(args, 1, args.length), " ");
                                p.sendTitle(format(args1), null, Main.getInstance().getConfig().getInt("TitleFadeIn"), Main.getInstance().getConfig().getInt("TitleDisplayTime"), Main.getInstance().getConfig().getInt("TitleFadeOut"));
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("boss")) {
                        sender.sendMessage(format("&cThis feature is not ready yet."));
                    }
                    if (args[0].equalsIgnoreCase("board")) {
                        sender.sendMessage(format("&cThis feature is not ready yet."));
                    }
                }
            }
        }
        return false;
    }
}

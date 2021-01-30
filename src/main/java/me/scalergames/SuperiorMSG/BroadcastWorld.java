package me.scalergames.SuperiorMSG;

import java.util.Arrays;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class BroadcastWorld implements CommandExecutor {

    FileConfiguration config;

    private final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    private BossBar bar;

    public void addPlayer(Player player) {
        bar.addPlayer(player);
    }

    public BossBar getBar() {
        return bar;
    }

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
        if (label.equalsIgnoreCase("broadcastworld")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("bp.broadcastworld")) {
                    String bprefix = Main.getInstance().getConfig().getString("BroadcastPrefix");
                    if (args.length >= 2) {
                        String message = StringUtils.join(Arrays.copyOfRange(args, 2, args.length), " ");
                        if (args[0].equalsIgnoreCase("chat")) {
                            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                                        String withplace = PlaceholderAPI.setPlaceholders(p, message);
                                        p.sendMessage(format(bprefix) + " " + ChatColor.RESET + format(withplace).replace("\\n", "\n"));
                                    }
                                }
                            } else {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                                        p.sendMessage(format(bprefix) + " " + ChatColor.RESET + format(message).replace("\\n", "\n"));
                                    }
                                }
                            }
                        }
                        if (args[0].equalsIgnoreCase("bar")) {
                            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                                        String withplace = PlaceholderAPI.setPlaceholders(p, message);
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(format(withplace)));
                                    }
                                }
                            } else {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(format(message)));
                                    }
                                }
                            }
                        }
                        if (args[0].equalsIgnoreCase("title")) {
                            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                                        String withplace = PlaceholderAPI.setPlaceholders(p, message);
                                        p.sendTitle(format(withplace), null, Main.getInstance().getConfig().getInt("TitleFadeIn"), Main.getInstance().getConfig().getInt("TitleDisplayTime"), Main.getInstance().getConfig().getInt("TitleFadeOut"));
                                    }
                                }
                            } else {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                                        p.sendTitle(format(message), null, Main.getInstance().getConfig().getInt("TitleFadeIn"), Main.getInstance().getConfig().getInt("TitleDisplayTime"), Main.getInstance().getConfig().getInt("TitleFadeOut"));
                                    }
                                }
                            }
                        }
                        if (args[0].equalsIgnoreCase("boss")) {
                            player.sendMessage("&cThe BossBar is not ready yet and will be in the next update!");
                            //for (Player p : Bukkit.getOnlinePlayers()) {
                            //String withplace = PlaceholderAPI.setPlaceholders(p, message);
                            //bar = Bukkit.createBossBar(format(withplace), BarColor.RED, BarStyle.SOLID);
                            //bar.setVisible(true);
                            //if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                            //bar.addPlayer(p);
                            //int seconds = 1000000;
                            //while (seconds > 1) {
                            //bar.setProgress(seconds / 1000000D);
                            //seconds = seconds - 1;
                            //}
                            //bar.removeAll();
                            //}
                            //}
                        }
                        if (args[0].equalsIgnoreCase("board")) {
                            player.sendMessage(format("&cThe scoreboard broadcast is not ready yet."));
                        }
                    }
                } else {
                    player.sendMessage(format("&cYou do not have permission to broadcast to a world."));
                }
            } else {
                String bprefix = Main.getInstance().getConfig().getString("BroadcastPrefix");
                if (args.length >= 2) {
                    String message = StringUtils.join(Arrays.copyOfRange(args, 2, args.length), " ");
                    if (args[0].equalsIgnoreCase("chat")) {
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                                    String withplace = PlaceholderAPI.setPlaceholders(p, message);
                                    p.sendMessage(format(bprefix) + " " + ChatColor.RESET + format(withplace).replace("\\n", "\n"));
                                }
                            }
                        } else {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                                    p.sendMessage(format(bprefix) + " " + ChatColor.RESET + format(message).replace("\\n", "\n"));
                                }
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("bar")) {
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                                    String withplace = PlaceholderAPI.setPlaceholders(p, message);
                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(format(withplace)));
                                }
                            }
                        } else {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(format(message)));
                                }
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("title")) {
                        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                                    String withplace = PlaceholderAPI.setPlaceholders(p, message);
                                    p.sendTitle(format(withplace), null, Main.getInstance().getConfig().getInt("TitleFadeIn"), Main.getInstance().getConfig().getInt("TitleDisplayTime"), Main.getInstance().getConfig().getInt("TitleFadeOut"));
                                }
                            }
                        } else {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                                    p.sendTitle(format(message), null, Main.getInstance().getConfig().getInt("TitleFadeIn"), Main.getInstance().getConfig().getInt("TitleDisplayTime"), Main.getInstance().getConfig().getInt("TitleFadeOut"));
                                }
                            }
                        }
                    }
                    if (args[0].equalsIgnoreCase("boss")) {
                        sender.sendMessage("&cThe BossBar is not ready yet and will be in the next update!");
                        //for (Player p : Bukkit.getOnlinePlayers()) {
                        //String withplace = PlaceholderAPI.setPlaceholders(p, message);
                        //bar = Bukkit.createBossBar(format(withplace), BarColor.RED, BarStyle.SOLID);
                        //bar.setVisible(true);
                        //if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                        //bar.addPlayer(p);
                        //int seconds = 1000000;
                        //while (seconds > 1) {
                        //bar.setProgress(seconds / 1000000D);
                        //seconds = seconds - 1;
                        //}
                        //bar.removeAll();
                        //}
                        //}
                    }
                    if (args[0].equalsIgnoreCase("board")) {
                        sender.sendMessage(format("&cThe scoreboard broadcast is not ready yet."));
                    }
                }
            }
        }
        return false;
    }
}

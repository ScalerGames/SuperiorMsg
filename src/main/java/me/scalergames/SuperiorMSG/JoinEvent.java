package me.scalergames.SuperiorMSG;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class JoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        if (Main.getInstance().getConfig().getBoolean("JoinMSG")) {
            if (!Main.getInstance().getConfig().getString("JoinMessage").equalsIgnoreCase("false")) {
                if (pm.getPlugin("PlaceholderAPI") != null) {
                    String noplace = Main.getInstance().getConfig().getString("JoinMessage");
                    String message = PlaceholderAPI.setPlaceholders(event.getPlayer(), noplace);
                    event.setJoinMessage(Color.format(message).replace("{p}", event.getPlayer().getDisplayName()));
                } else {
                    event.setJoinMessage(Main.getInstance().getConfig().getString(Color.format("JoinMessage").replace("{p}", event.getPlayer().getDisplayName())));
                }
            } else {
                event.setJoinMessage(null);
            }
            if (!Main.getInstance().getConfig().getString("JoinActionBar").equalsIgnoreCase("false")) {
                if (pm.getPlugin("PlaceholderAPI") != null) {
                    String noplace = Main.getInstance().getConfig().getString("JoinActionBar");
                    String bar = PlaceholderAPI.setPlaceholders(event.getPlayer(), noplace);
                    event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Color.format(bar).replace("{p}", event.getPlayer().getDisplayName())));
                } else {
                    String noplace = Main.getInstance().getConfig().getString("JoinActionBar");
                    event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Color.format(noplace).replace("{p}", event.getPlayer().getDisplayName())));
                }
            }
            if (!Main.getInstance().getConfig().getString("JoinTitle").equalsIgnoreCase("false")) {
                if (pm.getPlugin("PlaceholderAPI") != null) {
                    String subtitle = PlaceholderAPI.setPlaceholders(event.getPlayer(), Main.getInstance().getConfig().getString("JoinSubtitle"));
                    String noplace = Main.getInstance().getConfig().getString("JoinTitle");
                    String title = PlaceholderAPI.setPlaceholders(event.getPlayer(), noplace);
                    event.getPlayer().sendTitle(Color.format(title).replace("{p}", event.getPlayer().getDisplayName()), Color.format(subtitle).replace("{p}", event.getPlayer().getDisplayName()), 1, 60, 1);
                } else {
                    event.getPlayer().sendTitle(Color.format(Main.getInstance().getConfig().getString("JoinTitle").replace("{p}", event.getPlayer().getDisplayName())), Color.format(Main.getInstance().getConfig().getString("JoinSubtitle").replace("{p}", event.getPlayer().getDisplayName())), 1, 60, 1);
                }
            }
        }
        if (Main.getInstance().getConfig().getBoolean("FirstJoinMSG")) {
            if (!event.getPlayer().hasPlayedBefore()) {
                if (!Main.getInstance().getConfig().getString("FirstJoinMessage").equalsIgnoreCase("false")) {
                    if (pm.getPlugin("PlaceholderAPI") != null) {
                        String noplace = Main.getInstance().getConfig().getString("FirstJoinMessage");
                        String message = PlaceholderAPI.setPlaceholders(event.getPlayer(), noplace);
                        event.setJoinMessage(Color.format(message).replace("{p}", event.getPlayer().getDisplayName()));
                    } else {
                        event.setJoinMessage(Main.getInstance().getConfig().getString(Color.format("FirstJoinMessage").replace("{p}", event.getPlayer().getDisplayName())));
                    }
                } else {
                    event.setJoinMessage(null);
                }
                if (!Main.getInstance().getConfig().getString("FirstJoinActionBar").equalsIgnoreCase("false")) {
                    if (pm.getPlugin("PlaceholderAPI") != null) {
                        String noplace = Main.getInstance().getConfig().getString("FirstJoinActionBar");
                        String bar = PlaceholderAPI.setPlaceholders(event.getPlayer(), noplace);
                        event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Color.format(bar).replace("{p}", event.getPlayer().getDisplayName())));
                    } else {
                        String noplace = Main.getInstance().getConfig().getString("FirstJoinActionBar");
                        event.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Color.format(noplace).replace("{p}", event.getPlayer().getDisplayName())));
                    }
                }
                if (!Main.getInstance().getConfig().getString("JoinTitle").equalsIgnoreCase("false")) {
                    if (pm.getPlugin("PlaceholderAPI") != null) {
                        String subtitle = PlaceholderAPI.setPlaceholders(event.getPlayer(), Main.getInstance().getConfig().getString("FirstJoinSubtitle"));
                        String noplace = Main.getInstance().getConfig().getString("FirstJoinTitle");
                        String title = PlaceholderAPI.setPlaceholders(event.getPlayer(), noplace);
                        event.getPlayer().sendTitle(Color.format(title), Color.format(subtitle), 1, 60, 1);
                    } else {
                        event.getPlayer().sendTitle(Color.format(Main.getInstance().getConfig().getString("FirstJoinTitle").replace("{p}", event.getPlayer().getDisplayName())), Color.format(Main.getInstance().getConfig().getString("FirstJoinSubtitle").replace("{p}", event.getPlayer().getDisplayName())), 1, 60, 1);
                    }
                }
            }
        }
    }
}

package me.scalergames.SuperiorMSG;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;

import me.clip.placeholderapi.PlaceholderAPI;

public class QuitEvent implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        if (Main.getInstance().getConfig().getBoolean("QuitMSG")) {
            if (!Main.getInstance().getConfig().getString("QuitMessage").equalsIgnoreCase("false")) {
                if (pm.getPlugin("PlaceholderAPI") != null) {
                    String noplace = Main.getInstance().getConfig().getString("QuitMessage");
                    String withplace = PlaceholderAPI.setPlaceholders(event.getPlayer(), noplace);
                    event.setQuitMessage(Color.format(withplace).replace("{p}", event.getPlayer().getDisplayName()));

                } else {
                    String noplace = Main.getInstance().getConfig().getString("QuitMessage");
                    event.setQuitMessage(Color.format(noplace).replace("{p}", event.getPlayer().getDisplayName()));
                }
            } else {
                event.setQuitMessage(null);
            }
        }
    }
}

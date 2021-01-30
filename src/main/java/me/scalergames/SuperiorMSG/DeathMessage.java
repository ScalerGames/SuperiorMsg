package me.scalergames.SuperiorMSG;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.PluginManager;

import me.clip.placeholderapi.PlaceholderAPI;

public class DeathMessage implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        if (Main.getInstance().getConfig().getBoolean("DeathMSG")) {
            if (!Main.getInstance().getConfig().getString("DeathMessage").equalsIgnoreCase("false")) {
                if (pm.getPlugin("PlaceholderAPI") != null) {
                    String noplace = Main.getInstance().getConfig().getString("DeathMessage");
                    String withplace = PlaceholderAPI.setPlaceholders(event.getEntity(), noplace);
                    event.setDeathMessage(withplace.replace("{player}", event.getEntity().getDisplayName())
                            .replace("{reason}", event.getDeathMessage())
                            .replace("{world}", event.getEntity().getWorld().getName()));

                } else {
                    String noplace = Main.getInstance().getConfig().getString("DeathMessage");
                    event.setDeathMessage(Color.format(noplace).replace("{player}", event.getEntity().getDisplayName())
                            .replace("{reason}", event.getDeathMessage())
                            .replace("{world}", event.getEntity().getWorld().getName()));
                }
            }
        }
    }
}

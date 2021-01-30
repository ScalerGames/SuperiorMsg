package me.scalergames.SuperiorMSG;

import org.apache.commons.lang.WordUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.clip.placeholderapi.PlaceholderAPI;

public class Format implements Listener {

    @EventHandler
    public void chatFormat(AsyncPlayerChatEvent event) {
        String noplace = Main.getInstance().getConfig().getString("Format");
        String withplace = PlaceholderAPI.setPlaceholders(event.getPlayer(), noplace);

        //Chat Color/Emoji Format
        if(event.getPlayer().hasPermission("sm.chatcolor")) {
            event.setMessage(Color.format(event.getMessage().replace(":heart:", "♥").replace(":tick:", "✔").replace(":cross:", "✖")
                    .replace(":warn:", "⚠").replace(":smile:", "☺").replace(":face:", "☻").replace(":badface:", "☹").replace(":tickbox:", "☑")
                    .replace(":crossbox:", "☒").replace(":star:", "⭐").replace(":sword:", "⚔").replace(":pickaxe:", "⛏").replace(":axe:", "🪓")
                    .replace(":bow:", "🏹")));

        }

        //World Rename
        if (event.getPlayer().getWorld().getName().equalsIgnoreCase("world")) {
            String world = "Overworld";
            event.setFormat(Color.format(withplace).replace("{message}", event.getMessage()
            ).replace("{world}", world)
                    .replace("{player}", event.getPlayer().getDisplayName())
                    .replaceAll("%", "%%"));
        }
        if (event.getPlayer().getWorld().getName().equalsIgnoreCase("world_nether")) {
            String world = "Nether";
            event.setFormat(Color.format(withplace).replace("{message}", event.getMessage()
            ).replace("{world}", world)
                    .replace("{player}", event.getPlayer().getDisplayName())
                    .replaceAll("%", "%%"));
        }
        if (event.getPlayer().getWorld().getName().equalsIgnoreCase("world_the_end")) {
            String world = "End";
            event.setFormat(Color.format(withplace).replace("{message}", event.getMessage()
            ).replace("{world}", world)
                    .replace("{player}", event.getPlayer().getDisplayName())
                    .replaceAll("%", "%%"));
        }

        else {
            String world = event.getPlayer().getWorld().getName();
            world = WordUtils.capitalizeFully(world);
            event.setFormat(Color.format(withplace).replace("{message}", event.getMessage()
            ).replace("{world}", world)
                    .replace("{player}", event.getPlayer().getDisplayName())
                    .replaceAll("%", "%%"));
        }

        //Chat Format
        //String world = event.getPlayer().getWorld().getName();
        //world = WordUtils.capitalizeFully(world);
        //event.setFormat(Color.format(withplace).replace("{message}", event.getMessage()
        //).replace("{world}", world)
        //.replace("{player}", event.getPlayer().getDisplayName())
        //.replaceAll("%", "%%"));
    }
}

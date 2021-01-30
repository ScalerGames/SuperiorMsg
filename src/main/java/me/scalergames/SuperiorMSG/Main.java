package me.scalergames.SuperiorMSG;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.FormattedCommandAlias;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {

    private static Main plugin;

    FileConfiguration config;
    File cFile;
    File worldFile;

    //On Server Start
    @Override
    public void onEnable() {
        enableCommands();
        enableConfig();
        getServer().getPluginManager().registerEvents(this, this);
        //JOIN EVENT
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        //QUIT EVENT
        getServer().getPluginManager().registerEvents(new QuitEvent(), this);
        //DeathMessage
        getServer().getPluginManager().registerEvents(new DeathMessage(), this);
        //CHAT FORMAT
        if (getConfig().getBoolean("ChatFormat")) {
            getServer().getPluginManager().registerEvents(new Format(), this);
        }
        //PLACEHOLDERAPI ENABLE
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(this, this);
        } else {
            getLogger().info(ChatColor.GOLD + "Could not find PlaceholderAPI. Placeholders will be disabled.");
        }
        plugin = this;
    }


    //On Server Close
    public void onDisable() {

    }

    //
    public void enableCommands() {
        getCommand("sm").setExecutor((CommandExecutor)new SMCommand());
        getCommand("sm").setTabCompleter((TabCompleter)new SMTab());
        getCommand("msg").setExecutor((CommandExecutor)new PM());
        getCommand("broadcast").setExecutor((CommandExecutor)new Broadcast());
        getCommand("broadcast").setTabCompleter((TabCompleter)new BroadcastTab());
        getCommand("broadcastworld").setExecutor((CommandExecutor)new BroadcastWorld());
        getCommand("broadcastworld").setTabCompleter((TabCompleter)new BroadcastTab());
        getCommand("localbroadcast").setExecutor((CommandExecutor)new BroadcastLocal());
        getCommand("localbroadcast").setTabCompleter((TabCompleter)new BroadcastTab());
    }

    public void enableConfig() {
        config = getConfig();
        //Chat Format
        config.addDefault("ChatFormat", true);
        config.addDefault("Format", "&8&l[&2&lMember&8&l] &a{player} &8&l» &r{message}");
        config.addDefault("Color", true);
        //Join
        config.addDefault("JoinMSG", true);
        config.addDefault("JoinMessage", "&e{p} joined the server");
        config.addDefault("JoinActionBar", "&eWelcome {p}");
        config.addDefault("JoinTittle", "false");
        config.addDefault("JoinSubtitle", "false");
        //First Join
        config.addDefault("FirstJoinMSG", true);
        config.addDefault("FirstJoinMessage", "&8&l[&e&lWelcome&8&l] {p} joined the server for the first time!");
        config.addDefault("FirstJoinActionBar", "false");
        config.addDefault("FirstJoinTitle", "false");
        config.addDefault("FirstJoinSubtitle", "false");
        //Quit
        config.addDefault("QuitMSG", true);
        config.addDefault("QuitMessage", "&e{p} left the server");
        //Death
        config.addDefault("DeathMSG", true);
        config.addDefault("DeathMessage", "&c{reason}");
        //Private MSG
        config.addDefault("PrivateMSG", true);
        config.addDefault("PMSend", "{sender} -> {reciever} >> {message}");
        config.addDefault("PMRecieve", "{reciever} -> {sender} >> {message}");
        //Broadcast
        config.addDefault("Broadcast", true);
        config.addDefault("BroadcastPrefix", "&8&l[&c&lBroadcast&8&l]");
        config.addDefault("TitleFadeIn", 1);
        config.addDefault("TitleDisplayTime", 20);
        config.addDefault("LocalBroadcastRange", 100);
        //--END--
        config.options().copyDefaults(true);
        saveDefaultConfig();
        cFile = new File(getDataFolder(), "config.yml");
    }

    public static Main getInstance() {
        return plugin;
    }
}

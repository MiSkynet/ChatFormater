package de.miskynet.chatformater;

import de.miskynet.chatformater.listener.chatListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Setup config
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        
        // Register the listener
        getServer().getPluginManager().registerEvents(new chatListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
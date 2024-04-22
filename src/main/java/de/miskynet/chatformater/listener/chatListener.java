package de.miskynet.chatformater.listener;

import de.miskynet.chatformater.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class chatListener implements Listener {

    private static Main plugin;
    public chatListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void playerChatEvent(AsyncPlayerChatEvent event) {

        // Register all strings
        String playername = event.getPlayer().getName();
        String configChatFormat = plugin.getConfig().getString("chat-format");
        String message = event.getMessage();
        String finalFormat = "";

        // If the string in the config.yml is 'default'
        // If it isn't, use the string from the config.yml
        if (configChatFormat.equals("default")) {
            finalFormat = "<%playername%>";
        }
        else {
            finalFormat = configChatFormat;
        }

        // Default replacement for the string
        // (player name, message)
        finalFormat
                .replace("%playername%", playername)
                .replace("%message%", message);
        event.setFormat(finalFormat);

    }
}

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

        String playername = event.getPlayer().getName();
        String configChatFormat = plugin.getConfig().getString("chat-format");
        String finalFormat = "";

        if (configChatFormat.equals("default")) {
            finalFormat = "<%playername%>";
        }

        finalFormat.replace("%playername%", playername);
        event.setFormat(finalFormat);

    }
}

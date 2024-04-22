package de.miskynet.chatformater.listener;

import de.miskynet.chatformater.Main;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;
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
        String player = event.getPlayer().getName();
        String message = event.getMessage();
        String finalFormat;
        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(event.getPlayer());

        // If the string in the config.yml is 'default' or is null
        // Otherwise use the string from the config.yml
        if (plugin.getConfig().getString("chat-format").equals("default") || plugin.getConfig().getString("chat-format") == null) {
            finalFormat = "<%player%> %message%";
        }
        else {
            finalFormat = plugin.getConfig().getString("chat-format");
        }

        // Default replacement for the string
        // (player name, message)
        finalFormat = finalFormat
                .replaceAll("%player%", player)
                .replaceAll("%message%", message);

        // Set a players prefix
        if (user.getCachedData().getMetaData().getPrefix() != null) {
            finalFormat = finalFormat.replace("%prefix%", user.getCachedData().getMetaData().getPrefix());
        }

        event.setFormat(finalFormat.replaceAll("%", "%%"));

    }
}

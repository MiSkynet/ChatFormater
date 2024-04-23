package de.miskynet.chatformater.listener;

import de.miskynet.chatformater.Main;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.ChatColor;
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
        Boolean chatColorEnabled = plugin.getConfig().getBoolean("allow-chat-color");
        String chatFormat = plugin.getConfig().getString("chat-format");
        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(event.getPlayer());

        // If the 'allow-chat-color' is activated
        if (chatColorEnabled) {
            message = ChatColor.translateAlternateColorCodes('&', message);
        }

        // If the string in the config.yml is 'default' or is null
        // Otherwise use the string from the config.yml
        if (chatFormat.equals("default") || chatFormat  == null) {
            finalFormat = "<%player%> %message%";
        }
        else {
            finalFormat = chatFormat;
        }

        // Default replacement for the string
        // (player name, message)
        finalFormat = finalFormat
                .replaceAll("%player%", player)
                .replaceAll("%message%", message);

        // Set a players prefix
        if (user.getCachedData().getMetaData().getPrefix() != null) {
            if (!(user.getCachedData().getMetaData().getPrefix().equals(""))) {
                finalFormat = finalFormat.replace("%prefix%", user.getCachedData().getMetaData().getPrefix());
            }
        }

        event.setFormat(finalFormat.replaceAll("%", "%%"));

    }
}

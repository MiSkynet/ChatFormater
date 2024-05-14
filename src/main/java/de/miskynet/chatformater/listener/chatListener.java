package de.miskynet.chatformater.listener;

import de.miskynet.chatformater.Main;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class chatListener implements Listener {

    private static Main plugin;
    public chatListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void playerChatEvent(AsyncPlayerChatEvent event) {

        // Register
        String player = event.getPlayer().getName();
        String message = event.getMessage();
        String finalFormat;
        String playerMessageLowerCased = message.toLowerCase();
        String chatFormat = plugin.getConfig().getString("chat-format");
        Boolean chatColorEnabled = plugin.getConfig().getBoolean("allow-chat-color");

        User user = LuckPermsProvider.get().getPlayerAdapter(Player.class).getUser(event.getPlayer());

        // Censor blacklisted words
        ArrayList<String> blacklistedwords = (ArrayList<String>) plugin.getConfig().getStringList("blacklisted-words");
        for (String blacklistedword : blacklistedwords) {
            blacklistedword = blacklistedword.toLowerCase();
            if (playerMessageLowerCased.contains(blacklistedword)) {
                StringBuilder cleanedWord = new StringBuilder();
                for (int i = 0; i < blacklistedword.length(); i++) {
                    cleanedWord.append("*");
                    System.out.println(cleanedWord);
                }
                message = message.substring(0, message.toLowerCase().indexOf(blacklistedword)) + cleanedWord + message.substring(message.toLowerCase().indexOf(blacklistedword) + blacklistedword.length());
            }
        }

        // If the 'allow-chat-color' is activated and the player has the Permission
        if (chatColorEnabled) {
            if (event.getPlayer().hasPermission(configString("chat-color-permission")) || configString("chat-color-permission").equals("-all")  || configString("chat-color-permission").equals("-a")) {
                message = ChatColor.translateAlternateColorCodes('&', message);
            }
        }

        // If the string in the config.yml is 'default' or is null
        // Otherwise use the string from the config.yml
        if (chatFormat.equals("-default") || chatFormat.equals("-d") || chatFormat  == null) {
            finalFormat = "<%player%> %message%";
        }
        else {
            finalFormat = chatFormat;
        }

        // Default replacement for the string
        // (player name, message)
        finalFormat = finalFormat
                .replace("%player%", player)
                .replace("%message%", message)
                .replace("%time%", timeFormater());

        // Set a players prefix
        if (user.getCachedData().getMetaData().getPrefix() != null) {
            if (!(user.getCachedData().getMetaData().getPrefix().equals(""))) {
                finalFormat = finalFormat.replace("%prefix%", user.getCachedData().getMetaData().getPrefix());
            }
        }

        event.setFormat(finalFormat.replaceAll("%", "%%"));
    }

    private static String timeFormater() {
        String formattedTime = "";
        LocalDateTime now = LocalDateTime.now();

        String configtimeformat = plugin.getConfig().getString("time-format");

        configtimeformat = configtimeformat
                .replace("%HH%", "HH")
                .replace("%MM%", "mm")
                .replace("%SS%", "ss");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(configtimeformat);
        formattedTime = now.format(formatter);

        return formattedTime;
    }

    private static String configString(String string) {
        return plugin.getConfig().getString(string);
    }

}

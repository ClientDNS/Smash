package de.clientdns.smash.util;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

public class PlayerUtil {

    /**
     * Sends an actionbar message to all online players.
     *
     * @param message The message to send as a {@link Component}.
     */
    public static void actionbar(Component message) {
        Bukkit.getOnlinePlayers().forEach(player -> player.sendActionBar(message));
    }

    /**
     * Checks if a player is online.
     *
     * @param name The name of the player.
     * @return True if the player is online, false if not.
     */
    public static boolean online(String name) {
        return Bukkit.getPlayer(name) != null;
    }

    /**
     * Sends a message to every player on the server.
     *
     * @param message The message to send as a {@link Component}.
     */
    public static void broadcast(Component message) {
        Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(message));
    }
}

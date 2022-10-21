package de.clientdns.smash.util;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class PlayerUtil {

    /**
     * Sends an actionbar message to all online players.
     *
     * @param message The message to send as a {@link Component}.
     */
    public static void actionbar(Component message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendActionBar(message);
        }
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
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
        }
    }

    /**
     * Sends a message to every player on the server.
     *
     * @param message The message to send as a {@link Component}.
     */
    public static void broadcastTo(Component message, GameMode requiredGameMode) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getGameMode().equals(requiredGameMode)) {
                player.sendMessage(message);
            }
        }
    }
}

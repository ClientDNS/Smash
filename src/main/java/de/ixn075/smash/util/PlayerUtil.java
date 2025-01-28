package de.ixn075.smash.util;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerUtil {

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
}

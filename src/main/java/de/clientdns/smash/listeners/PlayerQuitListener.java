package de.clientdns.smash.listeners;

import de.clientdns.smash.config.Constants;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerQuitListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (Constants.disableJoinMessage()) event.quitMessage(Component.empty());
        else
            event.quitMessage(Constants.prefix().append(Component.text("§e" + player.getName() + " §7hat den Server verlassen§8.")));
    }
}

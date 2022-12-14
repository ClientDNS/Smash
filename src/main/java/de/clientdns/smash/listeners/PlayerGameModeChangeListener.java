package de.clientdns.smash.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.jetbrains.annotations.NotNull;

import static de.clientdns.smash.config.Value.get;

public class PlayerGameModeChangeListener implements Listener {

    @EventHandler
    void on(@NotNull PlayerGameModeChangeEvent event) {
        if (event.getCause().equals(PlayerGameModeChangeEvent.Cause.UNKNOWN) || event.getCause().equals(PlayerGameModeChangeEvent.Cause.COMMAND)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(get("prefix").append(get("cannot-switch-gamemode")));
        }
    }
}

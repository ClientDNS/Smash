package de.clientdns.smash.listeners;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerGameModeChangeListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull PlayerGameModeChangeEvent event) {
        if (event.getNewGameMode().equals(GameMode.ADVENTURE) || event.getNewGameMode().equals(GameMode.SURVIVAL)) {
            event.getPlayer().setAllowFlight(true);
        }
    }
}

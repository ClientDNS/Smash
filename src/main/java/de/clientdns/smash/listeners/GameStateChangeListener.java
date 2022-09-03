package de.clientdns.smash.listeners;

import de.clientdns.smash.countdown.EndCountdown;
import de.clientdns.smash.events.GameStateChangeEvent;
import de.clientdns.smash.gamestate.GameState;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class GameStateChangeListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull GameStateChangeEvent event) {
        if (event.getGameState().equals(GameState.INGAME)) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.setGameMode(GameMode.ADVENTURE);
                player.setAllowFlight(false);
                player.setFlying(false);
                player.getInventory().clear();
            });
        } else if (event.getGameState().equals(GameState.END)) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.setGameMode(GameMode.SURVIVAL);
                player.setAllowFlight(false);
                player.setFlying(false);
                player.getInventory().clear();
            });
            EndCountdown.start();
        }
    }
}

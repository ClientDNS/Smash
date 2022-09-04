package de.clientdns.smash.listeners.custom;

import de.clientdns.smash.countdown.EndCountdown;
import de.clientdns.smash.events.GameStateChangeEvent;
import de.clientdns.smash.gamestate.GameState;
import net.kyori.adventure.text.Component;
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
                // TODO: Give items and teleport to voted map locations
            });
        } else if (event.getGameState().equals(GameState.END)) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.setGameMode(GameMode.SURVIVAL);
                player.setAllowFlight(false);
                player.setFlying(false);
                player.getInventory().clear();
                // TODO: Teleport to spawn
            });
            EndCountdown.start();
        }
        Bukkit.broadcast(Component.text("§7Der Spielzustand wurde auf §e" + event.getGameState() + " §7gesetzt§8."));
    }
}

package de.clientdns.smash.listeners.custom;

import de.clientdns.smash.countdown.EndCountdown;
import de.clientdns.smash.countdown.LobbyCountdown;
import de.clientdns.smash.events.GameStateChangeEvent;
import de.clientdns.smash.gamestate.GameState;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public class GameStateChangeListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull GameStateChangeEvent event) {
        if (event.getGameState().equals(GameState.INGAME)) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.setGameMode(GameMode.ADVENTURE);
                player.setAllowFlight(false);
                player.setFlying(false);
                player.getInventory().clear();
                // TODO: Give items and teleport to voted map locations
            }
            LobbyCountdown.forceStop();
        } else if (event.getGameState().equals(GameState.END)) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.setGameMode(GameMode.SPECTATOR);
                player.setAllowFlight(true);
                player.setFlying(true);
                player.getInventory().clear();
                player.sendTitlePart(TitlePart.TITLE, text("Das Spiel ist vorbei!", RED));
                player.sendTitlePart(TitlePart.SUBTITLE, text("Du bist nun im Spectator-Modus.", GRAY));
                player.sendTitlePart(TitlePart.TIMES, Title.Times.times(Duration.ZERO, Duration.ofMillis(2500), Duration.ZERO));
            }
            LobbyCountdown.forceStop();
            EndCountdown.start();
        }
    }
}

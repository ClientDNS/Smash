package de.clientdns.smash.listeners.custom;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.Constants;
import de.clientdns.smash.countdown.EndCountdown;
import de.clientdns.smash.countdown.LobbyCountdown;
import de.clientdns.smash.events.GameStateChangeEvent;
import de.clientdns.smash.gamestate.GameState;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import static net.kyori.adventure.text.Component.text;

public class GameStateChangeListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull GameStateChangeEvent event) {
        SmashPlugin.getPlugin().getLogger().info("Calling GameStateChangeEvent (" + event.getGameState().name() + ")");
        if (event.getGameState().equals(GameState.INGAME)) {
            CompletableFuture.supplyAsync(Bukkit::getOnlinePlayers).thenAccept(collection -> collection.forEach(player -> {
                player.setGameMode(GameMode.ADVENTURE);
                player.setAllowFlight(false);
                player.setFlying(false);
                player.getInventory().clear();
                LobbyCountdown.forceStop();
                // TODO => Give items and teleport to voted map locations
            }));
        } else if (event.getGameState().equals(GameState.END)) {
            CompletableFuture.supplyAsync(Bukkit::getOnlinePlayers).thenAccept(collection -> collection.forEach(player -> {
                player.setGameMode(GameMode.SPECTATOR);
                player.setAllowFlight(true);
                player.setFlying(true);
                player.getInventory().clear();
                LobbyCountdown.forceStop();
                player.sendTitlePart(TitlePart.TITLE, text("Das Spiel ist vorbei!").color(NamedTextColor.RED));
                player.sendTitlePart(TitlePart.TIMES, Title.Times.times(Duration.ZERO, Duration.ofMillis(2500), Duration.ZERO));
                EndCountdown.start();
            }));
        }
        Bukkit.broadcast(Constants.prefix().append(text("Der Spielzustand wurde auf " + event.getGameState() + " gesetzt.", NamedTextColor.GRAY)));
    }
}

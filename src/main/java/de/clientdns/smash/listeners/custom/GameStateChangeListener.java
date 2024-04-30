package de.clientdns.smash.listeners.custom;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.countdown.EndCountdown;
import de.clientdns.smash.events.GameStateChangeEvent;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

import static net.kyori.adventure.text.format.NamedTextColor.*;

public class GameStateChangeListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull GameStateChangeEvent event) {
        switch (event.getGameState()) {
            case INGAME -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.setGameMode(GameMode.ADVENTURE);
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    player.getInventory().clear();
                    player.sendTitlePart(TitlePart.TITLE, MiniMsg.plain("Hooray!", GREEN));
                    player.sendTitlePart(TitlePart.SUBTITLE, MiniMsg.plain("The game begins!", GRAY));
                    player.sendTitlePart(TitlePart.TIMES, Title.Times.times(Duration.ZERO, Duration.ofMillis(2500), Duration.ZERO));
                    // TODO: Teleport players to random locations of voted map
                }
                SmashPlugin.getPlugin().getGameTimer().start();
            }
            case END -> {
                SmashPlugin.getPlugin().getGameTimer().stop();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.setAllowFlight(false);
                    player.setFlying(false);
                    player.getInventory().clear();
                    player.sendTitlePart(TitlePart.TITLE, MiniMsg.plain("End of the game!", RED));
                    player.sendTitlePart(TitlePart.TIMES, Title.Times.times(Duration.ZERO, Duration.ofMillis(1500), Duration.ZERO));
                }
                EndCountdown.start();
            }
        }
    }
}

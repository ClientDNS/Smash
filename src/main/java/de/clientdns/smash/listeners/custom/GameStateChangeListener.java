package de.clientdns.smash.listeners.custom;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.countdown.EndCountdown;
import de.clientdns.smash.countdown.LobbyCountdown;
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

import static net.kyori.adventure.text.format.NamedTextColor.GRAY;
import static net.kyori.adventure.text.format.NamedTextColor.RED;

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
                    // TODO: Give items and teleport to voted map locations
                }
                LobbyCountdown.forceStopScheduler();
            }
            case END -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.setGameMode(GameMode.SPECTATOR);
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    player.getInventory().clear();
                    player.sendTitlePart(TitlePart.TITLE, MiniMsg.plain("Das Spiel ist vorbei!", RED));
                    player.sendTitlePart(TitlePart.SUBTITLE, MiniMsg.plain("Du schaust nun zu.", GRAY));
                    player.sendTitlePart(TitlePart.TIMES, Title.Times.times(Duration.ZERO, Duration.ofMillis(2500), Duration.ZERO));
                }
                LobbyCountdown.forceStopScheduler();
                EndCountdown.start();
            }
        }
        SmashPlugin.getPlugin().getLogger().info("Detected game state change to " + event.getGameState());
    }
}

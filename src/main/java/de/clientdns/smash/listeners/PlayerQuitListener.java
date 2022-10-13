package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.Constants;
import de.clientdns.smash.countdown.LobbyCountdown;
import de.clientdns.smash.util.PlayerUtil;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import static net.kyori.adventure.text.Component.empty;
import static net.kyori.adventure.text.Component.text;

public class PlayerQuitListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull PlayerQuitEvent event) {
        Player player = event.getPlayer();

        int online = Bukkit.getOnlinePlayers().size() - 1;
        int minPlayers = Constants.minPlayers();
        int maxPlayers = Bukkit.getMaxPlayers();

        if (SmashPlugin.getPlugin().getGameStateManager().isLobbyState()) {
            // GameState is in LOBBY state
            PlayerUtil.broadcast(text(player.getName() + " hat den Server verlassen.", NamedTextColor.RED));
            if (online < minPlayers) {
                LobbyCountdown.forceStop();
                Bukkit.broadcast(Constants.prefix().append(text("Der Countdown wurde gestoppt, da nicht genügend Spieler online sind.", NamedTextColor.RED)));
            }
        } else if (SmashPlugin.getPlugin().getGameStateManager().isIngameState()) {
            // GameState is in INGAME state
            if (online == 0) {
                stopServer();
            }
        } else {
            // GameState is in END state
            if (online == 0) {
                stopServer();
            }
        }
        event.quitMessage(empty());
    }

    private void stopServer() {
        Bukkit.shutdown();
    }
}

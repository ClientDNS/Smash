package de.clientdns.smash.listeners;

import de.clientdns.smash.api.SmashApi;
import de.clientdns.smash.countdown.LobbyCountdown;
import de.clientdns.smash.api.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

import static de.clientdns.smash.config.Value.get;
import static de.clientdns.smash.config.Value.plain;
import static net.kyori.adventure.text.Component.empty;
import static net.kyori.adventure.text.format.NamedTextColor.RED;

public class PlayerQuitListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull PlayerQuitEvent event) {
        Player player = event.getPlayer();
        int online = Bukkit.getOnlinePlayers().size() - 1;
        int minPlayers = get("min-players", 2);
        if (SmashApi.gameStateManager().lobbyState()) {
            // GameState is in LOBBY state
            PlayerUtil.broadcast(plain(player.getName() + " hat den Server verlassen.", RED));
            if (online < minPlayers) {
                LobbyCountdown.forceStop();
                Bukkit.broadcast(get("prefix").append(plain("Der Countdown wurde gestoppt, da nicht genügend Spieler online sind.", RED)));
            }
        } else if (SmashApi.gameStateManager().ingameState()) {
            // GameState is in INGAME state
            if (online == 0)
                stopServer();
        } else {
            // GameState is in END state
            if (online == 0)
                stopServer();
        }
        event.quitMessage(empty());
    }

    private void stopServer() {
        // Stop server if no players are online
        Bukkit.shutdown();
    }
}

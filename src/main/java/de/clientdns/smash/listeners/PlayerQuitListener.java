package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.api.SmashApi;
import de.clientdns.smash.api.config.MiniMsg;
import de.clientdns.smash.api.gamestate.GameState;
import de.clientdns.smash.api.util.PlayerUtil;
import de.clientdns.smash.countdown.LobbyCountdown;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import static net.kyori.adventure.text.Component.empty;
import static net.kyori.adventure.text.format.NamedTextColor.RED;

public class PlayerQuitListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull PlayerQuitEvent event) {
        Player player = event.getPlayer();
        int online = Bukkit.getOnlinePlayers().size() - 1;
        int minPlayers = SmashPlugin.getPlugin().getSmashConfig().getInt("min-players");
        if (SmashApi.getGameStateManager().getGameState().equals(GameState.LOBBY)) {
            // lobby state
            PlayerUtil.broadcast(MiniMsg.mini("prefix").append(MiniMsg.mini("quit-message").replaceText(builder -> builder.matchLiteral("$name").replacement(player.getName()))));
            if (online < minPlayers) {
                LobbyCountdown.forceStop();
                Bukkit.broadcast(MiniMsg.mini("prefix").append(MiniMsg.plain("Der Countdown wurde gestoppt, da nicht genügend Spieler online sind.", RED)));
            }
        } else if (SmashApi.getGameStateManager().getGameState().equals(GameState.INGAME)) {
            // in-game state
            if (online == 0) {
                stopServer(player);
            }
        } else {
            // end state
            if (online == 0) {
                stopServer(player);
            }
        }
        event.quitMessage(empty());
    }

    private void stopServer(@NotNull Player player) {
        // Stop server if no players are online
        Bukkit.shutdown();

        // Delete damage count from player
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(SmashPlugin.getPlugin(), "damageCount");
        if (pdc.has(key)) {
            pdc.remove(key);
        }
    }
}

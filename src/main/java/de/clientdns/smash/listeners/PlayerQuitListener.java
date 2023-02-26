package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.countdown.LobbyCountdown;
import de.clientdns.smash.gamestate.GameState;
import de.clientdns.smash.map.setup.MapSetup;
import de.clientdns.smash.util.PlayerUtil;
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

        // Delete damage count from player
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        NamespacedKey key = new NamespacedKey(SmashPlugin.getPlugin(), "damageCount");
        if (pdc.has(key)) {
            pdc.remove(key); // Delete key from data container
        }

        // Remove player's setup when running.
        if (SmashPlugin.getPlugin().getSetups().get(player) != null) {
            MapSetup mapSetup = SmashPlugin.getPlugin().getSetups().get(player);
            mapSetup.delete();
        }

        int online = Bukkit.getOnlinePlayers().size() - 1;
        int minPlayers = SmashPlugin.getPlugin().getSmashConfig().getInt("min-players");
        if (SmashPlugin.getPlugin().getGameStateManager().getGameState().equals(GameState.LOBBY)) {
            // lobby state
            PlayerUtil.broadcast(MiniMsg.mini("prefix").append(MiniMsg.mini("quit-message").replaceText(builder -> builder.matchLiteral("$name").replacement(player.getName()))));
            if (online < minPlayers) {
                LobbyCountdown.forceStopScheduler();
                Bukkit.broadcast(MiniMsg.mini("prefix").append(MiniMsg.plain("Der Countdown wurde gestoppt, da nicht genügend Spieler online sind.", RED)));
            }
        } else if (SmashPlugin.getPlugin().getGameStateManager().getGameState().equals(GameState.INGAME)) {
            // in-game state
            stopServer(online);
        } else {
            // end state
            stopServer(online);
        }
        event.quitMessage(empty());
    }

    private void stopServer(int count) {
        // Stop server if no players are online
        if (count == 0) {
            Bukkit.shutdown();
        }
    }
}

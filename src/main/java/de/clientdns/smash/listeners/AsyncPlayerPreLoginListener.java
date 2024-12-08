package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.gamestate.GameState;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import static net.kyori.adventure.text.format.NamedTextColor.RED;

public class AsyncPlayerPreLoginListener implements Listener {

    @EventHandler
    void on(AsyncPlayerPreLoginEvent e) {
        int online = Bukkit.getOnlinePlayers().size();
        int max = Bukkit.getMaxPlayers();
        if (online >= max) {
            if (SmashPlugin.getPlugin().getGameStateManager().is(GameState.LOBBY)) {
                e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, MiniMsg.plain("The server is full!", RED));
            } else if (SmashPlugin.getPlugin().getGameStateManager().is(GameState.INGAME)) {
                e.allow();
                Player player = Bukkit.getPlayer(e.getUniqueId());
                if (player != null) {
                    Bukkit.getOnlinePlayers().remove(player); // Remove player from online players list
                    player.setGameMode(GameMode.SPECTATOR);
                }
            } else {
                e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, MiniMsg.plain("The game already ended.", RED));
            }
        }
    }
}

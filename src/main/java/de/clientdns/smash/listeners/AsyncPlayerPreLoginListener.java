package de.clientdns.smash.listeners;

import de.clientdns.smash.api.SmashApi;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.jetbrains.annotations.NotNull;

import static de.clientdns.smash.config.Value.plain;
import static net.kyori.adventure.text.format.NamedTextColor.RED;

public class AsyncPlayerPreLoginListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull org.bukkit.event.player.AsyncPlayerPreLoginEvent event) {
        int online = Bukkit.getOnlinePlayers().size();
        int max = Bukkit.getMaxPlayers();
        if (online >= max) {
            if (SmashApi.gameStateManager().lobbyState())
                event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, plain("Der Server ist schon voll!", RED));
            else if (SmashApi.gameStateManager().ingameState()) {
                event.allow();
                Player player = Bukkit.getPlayer(event.getUniqueId());
                if (player != null) {
                    Bukkit.getOnlinePlayers().remove(player); // Remove player from online players list
                    player.setGameMode(GameMode.SPECTATOR);
                }
            } else
                event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, plain("Der Server ist gerade nicht verfügbar!", RED));
        }
    }
}

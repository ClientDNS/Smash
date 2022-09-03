package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.Constants;
import de.clientdns.smash.countdown.EndCountdown;
import de.clientdns.smash.countdown.LobbyCountdown;
import de.clientdns.smash.gamestate.GameState;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerQuitListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull PlayerQuitEvent event) {
        Player player = event.getPlayer();

        int online = Bukkit.getOnlinePlayers().size() - 1;
        int minPlayers = Constants.minPlayers();
        int maxPlayers = Bukkit.getMaxPlayers();

        if (SmashPlugin.getPlugin().getGameStateManager().getGameState().equals(GameState.LOBBY)) {
            if (online < minPlayers) {
                LobbyCountdown.stop(player);
            }
            Component quitMessage = Component.text("${name} §7hat den Server verlassen§8. [§a" + online + "§8/§a" + maxPlayers + "§8]")
                    .replaceText(b -> b.matchLiteral("${name}").replacement(Component.text(player.getName(), NamedTextColor.YELLOW)));
            event.quitMessage(Constants.prefix().append(quitMessage));
        } else if (SmashPlugin.getPlugin().getGameStateManager().getGameState().equals(GameState.INGAME)) {
            if (online == 0) {
                EndCountdown.start();
            }
        } else {
            if (online < 1) {
                Bukkit.broadcast(Component.text("Server ist leer, stoppe Server...", NamedTextColor.YELLOW));
                Bukkit.shutdown();
            }
            EndCountdown.start();
        }
    }
}

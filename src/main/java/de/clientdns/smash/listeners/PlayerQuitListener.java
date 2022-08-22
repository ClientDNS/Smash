package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.Constants;
import de.clientdns.smash.gamestate.GameState;
import net.kyori.adventure.text.Component;
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
        int maxPlayers = Bukkit.getMaxPlayers();
        if (SmashPlugin.getGameStateManager().getCurrentState().equals(GameState.LOBBY))
            event.quitMessage(Constants.prefix().append(Component.text("§e" + player.getName() + " §7hat den Server verlassen§8. [§a" + online + "§8/§a" + maxPlayers + "§8]")));
    }
}

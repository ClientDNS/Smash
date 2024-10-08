package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.gamestate.GameState;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerItemHeldListener implements Listener {

    @EventHandler
    void on(@NotNull PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        if (SmashPlugin.getPlugin().getGameStateManager().is(GameState.INGAME)) {
            if (player.getGameMode().equals(GameMode.SPECTATOR)) {
                event.setCancelled(false);
                return;
            }
            // Prevent player from moving to other slots than 0
            if (event.getPreviousSlot() == 0) {
                event.setCancelled(true);
            } else {
                player.getInventory().setHeldItemSlot(0);
            }
        }
    }
}

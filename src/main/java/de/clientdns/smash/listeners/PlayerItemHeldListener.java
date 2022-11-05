package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerItemHeldListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        if (SmashPlugin.getPlugin().getGameStateManager().isIngameState()) {
            if (player.getGameMode().equals(GameMode.SPECTATOR)) {

            }
            // Prevent player from moving to other slots than
            if (event.getPreviousSlot() != 0) {
                player.getInventory().setHeldItemSlot(0);
            } else if (event.getNewSlot() != 0) {
                player.getInventory().setHeldItemSlot(0);
            } else {
                player.getInventory().setHeldItemSlot(0);
            }
        }
    }
}

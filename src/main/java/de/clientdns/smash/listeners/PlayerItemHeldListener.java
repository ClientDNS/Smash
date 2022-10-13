package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class PlayerItemHeldListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(PlayerItemHeldEvent event) {
        if (SmashPlugin.getPlugin().getGameStateManager().isIngameState()) {
            // Prevent player from moving to other slots than 0
            if (event.getNewSlot() != 0) {
                event.setCancelled(true);
            }
        }
    }
}

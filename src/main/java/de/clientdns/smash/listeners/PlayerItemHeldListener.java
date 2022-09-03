package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.gamestate.GameState;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class PlayerItemHeldListener implements Listener {

    @SuppressWarnings("unused")
    void on(PlayerItemHeldEvent event) {
        if (SmashPlugin.getPlugin().getGameStateManager().getGameState().equals(GameState.INGAME)) {
            if (event.getNewSlot() != 0) {
                event.setCancelled(true);
            }
        }
    }
}

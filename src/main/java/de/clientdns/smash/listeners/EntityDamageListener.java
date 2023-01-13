package de.clientdns.smash.listeners;

import de.clientdns.smash.api.SmashApi;
import de.clientdns.smash.api.gamestate.GameState;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

public class EntityDamageListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler()
    void on(@NotNull EntityDamageEvent event) {
        if (SmashApi.getGameStateManager().getGameState().equals(GameState.LOBBY)) {
            if (!event.getEntityType().equals(EntityType.DROPPED_ITEM)) {
                event.setCancelled(true);
            }
        } else if (SmashApi.getGameStateManager().getGameState().equals(GameState.INGAME)) {
            event.setDamage(0D);
        } else {
            event.setCancelled(true);
        }
    }
}

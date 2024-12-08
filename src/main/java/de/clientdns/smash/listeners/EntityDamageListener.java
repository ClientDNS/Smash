package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.gamestate.GameState;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

public class EntityDamageListener implements Listener {

    @EventHandler
    void on(@NotNull EntityDamageEvent e) {
        if (!e.getEntityType().equals(EntityType.ITEM)) {
            e.setCancelled(true);
        }
        if (SmashPlugin.getPlugin().getGameStateManager().is(GameState.INGAME)) {
            if (!e.getEntityType().equals(EntityType.PLAYER)) {
                e.setDamage(0D);
            }
        } else {
            e.setCancelled(true);
        }
    }
}

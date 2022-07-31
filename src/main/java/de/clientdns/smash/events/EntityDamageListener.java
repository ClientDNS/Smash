package de.clientdns.smash.events;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

public class EntityDamageListener implements Listener {

    @EventHandler
    void on(@NotNull EntityDamageEvent event) {
        if (event.getEntity().getType() == EntityType.PLAYER) {
            event.setDamage(0);
        } else {
            event.setCancelled(true);
        }
    }
}

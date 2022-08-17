package de.clientdns.smash.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

public class EntityDamageListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull EntityDamageEvent event) {
        event.setDamage(0);
    }
}

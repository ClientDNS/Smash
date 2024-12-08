package de.clientdns.smash.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.jetbrains.annotations.NotNull;

public class EntityPickupItemListener implements Listener {

    @EventHandler
    void on(@NotNull EntityPickupItemEvent e) {
        e.setCancelled(true);
    }
}

package de.clientdns.smash.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerPickupItemListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull EntityPickupItemEvent event) {
        event.setCancelled(true);
    }
}

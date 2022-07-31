package de.clientdns.smash.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.jetbrains.annotations.NotNull;

public class FoodLevelChangeListener implements Listener {

    @EventHandler
    void on(@NotNull FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}

package de.clientdns.smash.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.jetbrains.annotations.NotNull;

public class FoodLevelChangeListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }
}

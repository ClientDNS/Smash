package de.clientdns.smash.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;

public class BlockPlaceListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull BlockPlaceEvent event) {
        event.setCancelled(true);
    }
}

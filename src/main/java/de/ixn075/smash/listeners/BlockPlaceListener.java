package de.ixn075.smash.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.jetbrains.annotations.NotNull;

public class BlockPlaceListener implements Listener {

    @EventHandler
    void on(@NotNull BlockPlaceEvent e) {
        e.setCancelled(true);
        e.setBuild(false);
    }
}

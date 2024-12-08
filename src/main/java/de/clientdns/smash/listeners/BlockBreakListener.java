package de.clientdns.smash.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class BlockBreakListener implements Listener {

    @EventHandler
    void on(@NotNull BlockBreakEvent e) {
        e.setCancelled(true);
        e.setDropItems(false);
    }
}

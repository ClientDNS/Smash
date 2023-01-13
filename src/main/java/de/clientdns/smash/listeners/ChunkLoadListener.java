package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.jetbrains.annotations.NotNull;

public class ChunkLoadListener implements Listener {

    @EventHandler
    void on(@NotNull ChunkLoadEvent event) {
        int count = 0;
        for (Entity entity : event.getWorld().getEntities()) {
            if (entity.getType().equals(EntityType.DROPPED_ITEM)) {
                entity.remove();
                count++;
            }
        }
        if (count > 1) {
            SmashPlugin.getPlugin().getLogger().info("Killed '" + count + "' entities in " + event.getChunk());
        }
    }
}

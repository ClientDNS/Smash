package de.clientdns.smash.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.jetbrains.annotations.NotNull;

public class ChunkLoadListener implements Listener {

    @EventHandler
    void on(@NotNull ChunkLoadEvent event) {
        for (LivingEntity entity : event.getWorld().getLivingEntities()) {
            if (entity.getType().equals(EntityType.DROPPED_ITEM)) entity.remove();
        }
    }
}

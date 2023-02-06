package de.clientdns.smash.listeners;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerMoveListener implements Listener {

    @EventHandler
    void on(@NotNull PlayerMoveEvent event) {
        if (!event.hasChangedBlock()) {
            return;
        }
        Player player = event.getPlayer();
        Location loc = player.getLocation().clone().subtract(0, 1, 0);
        player.getWorld().spawnParticle(Particle.FLAME, loc, 1, 0, 0, 0, 0);
    }
}

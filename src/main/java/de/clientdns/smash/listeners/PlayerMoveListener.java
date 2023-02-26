package de.clientdns.smash.listeners;

import org.bukkit.Color;
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
        Location loc = player.getLocation();
        Particle.DustTransition dustTransition = new Particle.DustTransition(Color.BLUE, Color.PURPLE, 1.0F);
        player.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, loc.x(), loc.y() + 0.5, loc.z(), 25, dustTransition);
    }
}

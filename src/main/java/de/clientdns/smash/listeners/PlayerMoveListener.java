package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.gamestate.GameState;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

import java.security.SecureRandom;

public class PlayerMoveListener implements Listener {

    private final Color[] colors = new Color[]{Color.RED, Color.ORANGE, Color.YELLOW, Color.LIME, Color.GREEN, Color.AQUA, Color.BLUE, Color.PURPLE};

    @EventHandler
    void on(@NotNull PlayerMoveEvent event) {
        if (!event.hasChangedBlock()) {
            return;
        }
        if (!SmashPlugin.getPlugin().getGameStateManager().getCurrentState().equals(GameState.LOBBY)) {
            return;
        }
        Player player = event.getPlayer();
        Location loc = player.getLocation();
        Particle.DustTransition dustTransition = new Particle.DustTransition(random(colors), random(colors), 1.0F);
        loc.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, loc.x(), loc.y() + 0.5, loc.z(), 50, dustTransition);
    }

    public Color random(Color @NotNull [] colors) {
        return colors[new SecureRandom().nextInt(colors.length)];
    }
}

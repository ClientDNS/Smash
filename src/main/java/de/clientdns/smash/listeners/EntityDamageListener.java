package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

public class EntityDamageListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler()
    void on(@NotNull EntityDamageEvent event) {
        if (SmashPlugin.getPlugin().getGameStateManager().isLobbyState()) {
            event.setCancelled(true);
        } else if (SmashPlugin.getPlugin().getGameStateManager().isIngameState()) {
            event.setDamage(0D);
        } else {
            event.setCancelled(true);
        }
    }
}

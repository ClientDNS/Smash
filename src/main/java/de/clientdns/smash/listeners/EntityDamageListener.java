package de.clientdns.smash.listeners;

import de.clientdns.smash.api.SmashApi;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

public class EntityDamageListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler()
    void on(@NotNull EntityDamageEvent event) {
        if (SmashApi.gameStateManager().lobbyState()) {
            event.setCancelled(true);
        } else if (SmashApi.gameStateManager().ingameState()) {
            event.setDamage(0D);
        } else {
            event.setCancelled(true);
        }
    }
}

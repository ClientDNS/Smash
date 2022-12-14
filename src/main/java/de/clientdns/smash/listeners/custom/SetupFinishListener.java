package de.clientdns.smash.listeners.custom;

import de.clientdns.smash.api.SmashApi;
import de.clientdns.smash.api.events.SetupFinishEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class SetupFinishListener implements Listener {

    @EventHandler
    void on(@NotNull SetupFinishEvent event) {
        SmashApi.setupManager().remove(event.getPlayer());
    }
}

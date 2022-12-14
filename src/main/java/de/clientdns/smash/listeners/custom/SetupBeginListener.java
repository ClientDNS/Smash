package de.clientdns.smash.listeners.custom;

import de.clientdns.smash.api.SmashApi;
import de.clientdns.smash.api.events.SetupBeginEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class SetupBeginListener implements Listener {

    @EventHandler
    void on(@NotNull SetupBeginEvent event) {
        SmashApi.setupManager().add(event.getPlayer(), event.getSetup());
    }
}

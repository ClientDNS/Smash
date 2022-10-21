package de.clientdns.smash.listeners.custom;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.events.SetupBeginEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class SetupBeginListener implements Listener {

    @EventHandler
    void on(@NotNull SetupBeginEvent event) {
        SmashPlugin.getPlugin().getSetupManager().add(event.getPlayer(), event.getSetup());
    }
}

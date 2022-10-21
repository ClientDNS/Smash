package de.clientdns.smash.listeners.custom;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.events.SetupDeleteEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class SetupDeleteListener implements Listener {

    @EventHandler
    void on(@NotNull SetupDeleteEvent event) {
        event.getSetup().save();
        SmashPlugin.getPlugin().getSetupManager().remove(event.getPlayer());
    }
}

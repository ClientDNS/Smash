package de.clientdns.smash.listeners.custom;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.events.SetupFinishEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class SetupFinishListener implements Listener {

    @EventHandler
    void on(@NotNull SetupFinishEvent event) {
        SmashPlugin.getPlugin().getSetupManager().remove(event.getPlayer());
    }
}

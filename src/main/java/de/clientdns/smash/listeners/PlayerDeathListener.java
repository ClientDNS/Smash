package de.clientdns.smash.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

import static net.kyori.adventure.text.Component.empty;

public class PlayerDeathListener implements Listener {

    @EventHandler
    void on(@NotNull PlayerDeathEvent event) {
        event.setKeepInventory(false);
        event.setKeepLevel(false);
        event.setShouldPlayDeathSound(false);
        event.setShouldDropExperience(false);
        event.deathMessage(empty());
    }
}

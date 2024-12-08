package de.clientdns.smash.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

import static net.kyori.adventure.text.Component.empty;

public class PlayerDeathListener implements Listener {

    @EventHandler
    void on(@NotNull PlayerDeathEvent e) {
        e.setKeepInventory(false);
        e.setKeepLevel(false);
        e.setShouldPlayDeathSound(false);
        e.setShouldDropExperience(false);
        e.deathMessage(empty());
    }
}

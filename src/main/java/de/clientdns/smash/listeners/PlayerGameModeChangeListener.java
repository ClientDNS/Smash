package de.clientdns.smash.listeners;

import de.clientdns.smash.config.Constants;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerGameModeChangeListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull PlayerGameModeChangeEvent event) {
        if (event.getCause() == PlayerGameModeChangeEvent.Cause.COMMAND) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(Constants.prefix().append(Component.text("Du kannst nicht in den Spielmodus wechseln.", NamedTextColor.RED)));
        }
    }
}

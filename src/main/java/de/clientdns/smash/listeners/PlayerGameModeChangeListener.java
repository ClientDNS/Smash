package de.clientdns.smash.listeners;

import de.clientdns.smash.config.MiniMsg;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerGameModeChangeListener implements Listener {

    @EventHandler
    void on(@NotNull PlayerGameModeChangeEvent event) {
        if (event.getCause().equals(PlayerGameModeChangeEvent.Cause.UNKNOWN) || event.getCause().equals(PlayerGameModeChangeEvent.Cause.COMMAND)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("You cannot change your gamemode while playing.", NamedTextColor.RED)));
        }
    }
}

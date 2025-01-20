package de.clientdns.smash.listeners;

import de.clientdns.smash.config.MiniMsg;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerGameModeChangeListener implements Listener {

    @EventHandler
    void on(@NotNull PlayerGameModeChangeEvent e) {
        if (e.getCause().equals(PlayerGameModeChangeEvent.Cause.COMMAND)) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Changing gamemode while ingame is not possible.", NamedTextColor.RED)));
        }
    }
}

package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.api.config.MiniMsg;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerGameModeChangeListener implements Listener {

    @EventHandler
    void on(@NotNull PlayerGameModeChangeEvent event) {
        if (event.getCause().equals(PlayerGameModeChangeEvent.Cause.UNKNOWN) || event.getCause().equals(PlayerGameModeChangeEvent.Cause.COMMAND)) {
            if (SmashPlugin.getPlugin().getSmashConfig().getBoolean("deny-gamemode-switch")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("switch-gamemode")));
            }
        }
    }
}

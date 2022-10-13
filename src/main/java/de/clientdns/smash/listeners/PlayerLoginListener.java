package de.clientdns.smash.listeners;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.jetbrains.annotations.NotNull;

import static net.kyori.adventure.text.Component.text;

public class PlayerLoginListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull PlayerLoginEvent event) {
        int online = event.getPlayer().getServer().getOnlinePlayers().size();
        int maxPlayers = event.getPlayer().getServer().getMaxPlayers();
        if (online >= maxPlayers) {
            event.setResult(PlayerLoginEvent.Result.KICK_FULL);
            event.kickMessage(text("Der Server ist voll.", NamedTextColor.RED));
        }
    }
}

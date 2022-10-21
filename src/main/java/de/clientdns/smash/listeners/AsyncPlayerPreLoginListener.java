package de.clientdns.smash.listeners;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import static net.kyori.adventure.text.Component.text;

public class AsyncPlayerPreLoginListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull org.bukkit.event.player.AsyncPlayerPreLoginEvent event) {
        int online = Bukkit.getOnlinePlayers().size();
        int maxPlayers = Bukkit.getMaxPlayers();
        if (online >= maxPlayers) {
            event.disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result.KICK_FULL, text("Der Server ist voll!", NamedTextColor.RED));
        }
    }
}

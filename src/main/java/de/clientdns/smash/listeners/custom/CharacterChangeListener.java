package de.clientdns.smash.listeners.custom;

import de.clientdns.smash.events.CharacterChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public class CharacterChangeListener implements Listener {

    @EventHandler
    void on(@NotNull CharacterChangeEvent event) {
        Player player = event.getPlayer();
        if (event.replaced()) {
            player.sendMessage(text("Du hast deinen Charakter zu", GREEN).append(event.getCharacter().getName()).append(text(" geändert!", GREEN)));
        } else {
            player.sendMessage(text("Du hast ", GREEN).append(event.getCharacter().getName()).append(text(" ausgewählt!", GREEN)));
        }
    }
}

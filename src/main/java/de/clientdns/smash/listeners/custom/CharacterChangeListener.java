package de.clientdns.smash.listeners.custom;

import de.clientdns.smash.api.character.Character;
import de.clientdns.smash.api.config.MiniMsg;
import de.clientdns.smash.api.events.CharacterChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import static net.kyori.adventure.text.format.NamedTextColor.GREEN;

public class CharacterChangeListener implements Listener {

    @EventHandler
    void on(@NotNull CharacterChangeEvent event) {
        Player player = event.getPlayer();
        Character before = event.getBefore();
        Character character = event.getCharacter();
        boolean characterReplaced = event.isReplaced();
        if (characterReplaced) {
            if (!character.equals(before)) { // Not same character as before
                player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Dein Charakter wurde auf ", GREEN).append(character.getName()).append(MiniMsg.plain(" gesetzt!", GREEN))));
            }
        } else {
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Du hast den Charakter ", GREEN).append(character.getName()).append(MiniMsg.plain(" ausgewählt!", GREEN))));
        }
    }
}

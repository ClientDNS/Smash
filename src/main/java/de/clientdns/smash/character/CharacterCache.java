package de.clientdns.smash.character;

import de.clientdns.smash.character.enums.Character;
import de.clientdns.smash.config.ConfigValues;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static net.kyori.adventure.text.Component.text;

public class CharacterCache {

    private final Map<Player, Character> playerCharacters;

    public CharacterCache() {
        playerCharacters = new ConcurrentHashMap<>();
    }

    public void put(Player player, Character character) {
        if (!playerCharacters.containsKey(player)) {
            playerCharacters.put(player, character);
        } else {
            playerCharacters.replace(player, character);
        }
        player.sendMessage(ConfigValues.prefix().append(text("Du hast den Charakter ", NamedTextColor.GRAY).append(character.getName()).append(text(" ausgewählt!", NamedTextColor.GRAY))));
    }

    public void clear() {
        playerCharacters.clear();
    }
}

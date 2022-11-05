package de.clientdns.smash.character;

import de.clientdns.smash.character.enums.Character;
import de.clientdns.smash.config.values.ConfigValues;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public class CharacterCache {

    private final Map<Player, Character> playerCharacters;

    public CharacterCache() {
        this.playerCharacters = new HashMap<>();
    }

    public void put(Player player, Character character) {
        if (!this.playerCharacters.containsKey(player)) {
            this.playerCharacters.put(player, character);
        } else {
            this.playerCharacters.replace(player, character);
        }
        player.sendMessage(ConfigValues.prefix().append(text("Du hast den Charakter ", GRAY).append(character.getName()).append(text(" ausgewählt!", GRAY))));
    }

    public void clear() {
        this.playerCharacters.clear();
    }
}

package de.clientdns.smash.character;

import de.clientdns.smash.character.enums.Character;
import de.clientdns.smash.config.Constants;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import static net.kyori.adventure.text.Component.text;

public class CharacterCache {

    private final Map<Player, Character> playerCharacters;

    public CharacterCache() {
        playerCharacters = new HashMap<>();
    }

    public void putOrReplace(Player player, Character character) {
        if (!playerCharacters.containsKey(player)) {
            playerCharacters.put(player, character);
        } else {
            playerCharacters.replace(player, character);
        }
        player.sendMessage(Constants.prefix().append(text("Du hast den Charakter ", NamedTextColor.GRAY).append(character.getName()).append(text(" ausgewählt!", NamedTextColor.GRAY))));
    }

    public void clear() {
        playerCharacters.clear();
    }

    public Character chooseRandom() {
        SecureRandom random = new SecureRandom();
        return Character.values()[random.nextInt(Character.values().length)];
    }
}

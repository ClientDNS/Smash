package de.clientdns.smash.character;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CharacterCache {

    private static Map<Player, Character> playerCharacters;

    public CharacterCache() {
        playerCharacters = new HashMap<>();
    }

    public void add(Player player, Character character) {
        playerCharacters.put(player, character);
    }

    public void replace(Player player, Character character) {
        playerCharacters.replace(player, character);
    }

    public Optional<Character> get(Player player) {
        return Optional.ofNullable(playerCharacters.get(player));
    }

    public void remove(Player player) {
        playerCharacters.remove(player);
    }

    public void clear() {
        playerCharacters.clear();
    }
}

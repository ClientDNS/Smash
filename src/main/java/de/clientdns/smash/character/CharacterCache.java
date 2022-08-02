package de.clientdns.smash.character;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CharacterCache {

    private static Map<Player, Character> playerCharacters;

    public CharacterCache() {
        playerCharacters = new HashMap<>();
    }

    public void add(Player player, Character character) {
        playerCharacters.put(player, character);
    }

    public Character get(Player player) {
        return playerCharacters.get(player);
    }

    public void remove(Player player) {
        playerCharacters.remove(player);
    }

    public boolean has(Player player) {
        return playerCharacters.containsKey(player);
    }
}

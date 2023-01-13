package de.clientdns.smash.api.player;

import de.clientdns.smash.api.character.Character;
import de.clientdns.smash.api.events.CharacterChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {

    private static final Map<Player, Character> map = new HashMap<>();

    public static void set(Player player, Character character) {
        if (!map.containsKey(player)) {
            map.put(player, character);
            Bukkit.getPluginManager().callEvent(new CharacterChangeEvent(player, null, character, false));
        } else {
            Character before = map.get(player); // Previous set character
            map.replace(player, character);
            Bukkit.getPluginManager().callEvent(new CharacterChangeEvent(player, before, character, true));
        }
    }

    public static boolean reset() {
        if (map.isEmpty()) {
            return false;
        } else {
            map.clear();
            return true;
        }
    }
}

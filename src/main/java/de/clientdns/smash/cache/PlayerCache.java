package de.clientdns.smash.cache;

import de.clientdns.smash.character.Character;
import de.clientdns.smash.events.CharacterChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerCache {

    private static final Map<Player, Character> map = new HashMap<>();

    public static void set(Player player, Character character) {
        if (map.containsKey(player)) {
            map.replace(player, character);
            Bukkit.getPluginManager().callEvent(new CharacterChangeEvent(player, character, true));
        } else {
            map.put(player, character);
            Bukkit.getPluginManager().callEvent(new CharacterChangeEvent(player, character, false));
        }
    }
}

package de.clientdns.smash.api.manager;

import de.clientdns.smash.api.character.Character;
import de.clientdns.smash.events.CharacterChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {

    private static final Map<Player, Character> map = new HashMap<>();

    public static @Nullable Character get(Player player) {
        if (map.containsKey(player))
            return map.get(player);
        return null;
    }

    public static void set(Player player, Character character) {
        if (get(player) != null) {
            Character before = get(player);
            map.replace(player, character);
            Bukkit.getPluginManager().callEvent(new CharacterChangeEvent(player, character, true, before == character));
        } else {
            map.put(player, character);
            Bukkit.getPluginManager().callEvent(new CharacterChangeEvent(player, character, false, false));
        }
    }
}

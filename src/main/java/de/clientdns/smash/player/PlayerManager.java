package de.clientdns.smash.player;

import de.clientdns.smash.character.Character;
import de.clientdns.smash.config.MiniMsg;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {

    private static final Map<Player, Character> characters = new HashMap<>();

    public static Character get(Player player) {
        return characters.get(player);
    }

    public static void set(Player player, Character character) {
        Character before = null;
        if (characters.containsKey(player)) {
            before = characters.get(player);
            characters.replace(player, character);
        } else {
            characters.put(player, character);
        }
        if (before == null) {
            player.sendActionBar(MiniMsg.mini("prefix").append(MiniMsg.plain("Dein Charakter wurde auf ", NamedTextColor.GREEN).append(PlayerManager.get(player).getData().getName()).append(MiniMsg.plain(" gesetzt.", NamedTextColor.GREEN))));
            return;
        }
        if (before != character) {
            player.sendActionBar(MiniMsg.mini("prefix").append(MiniMsg.plain("Dein Charakter wurde zu ", NamedTextColor.GREEN).append(PlayerManager.get(player).getData().getName()).append(MiniMsg.plain(" geändert.", NamedTextColor.GREEN))));
        }
    }

    public static void clearCharacters() {
        if (!characters.isEmpty()) {
            characters.clear();
        }
    }
}

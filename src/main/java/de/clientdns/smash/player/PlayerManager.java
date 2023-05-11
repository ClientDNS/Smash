package de.clientdns.smash.player;

import de.clientdns.smash.character.Character;
import de.clientdns.smash.config.MiniMsg;
import net.kyori.adventure.text.Component;
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
            Component finalMessage = MiniMsg.mini("character-select").replaceText(builder -> builder.matchLiteral("$name").replacement(character.data().name()));
            player.sendActionBar(MiniMsg.mini("prefix").append(finalMessage));
            return;
        }
        if (before != character) {
            Component finalMessage = MiniMsg.mini("character-switch").replaceText(builder -> builder.matchLiteral("$name").replacement(character.data().name()));
            player.sendActionBar(MiniMsg.mini("prefix").append(finalMessage));
        }
    }

    public static void clearCharacters() {
        if (!characters.isEmpty()) {
            characters.clear();
        }
    }
}

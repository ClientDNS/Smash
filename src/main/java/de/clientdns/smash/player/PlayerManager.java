package de.clientdns.smash.player;

import de.clientdns.smash.character.Character;
import de.clientdns.smash.config.MiniMsg;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerManager {

    private final Map<Player, Character> characters;

    public PlayerManager() {
        this.characters = new HashMap<>();
    }

    public void set(Player player, Character character) {
        Character before;
        if (!characters.containsKey(player)) {
            characters.put(player, character);
            Component finalMessage = MiniMsg.mini("Your character has been set to $name.", NamedTextColor.GREEN).replaceText(builder -> builder.matchLiteral("$name").replacement(character.data().name()));
            player.sendActionBar(MiniMsg.mini("prefix").append(finalMessage));
        } else {
            before = characters.get(player);
            characters.replace(player, before, character);
            if (before == character) return;
            Component finalMessage = MiniMsg.plain("Your character has been changed to $name.", NamedTextColor.GREEN).replaceText(builder -> builder.matchLiteral("$name").replacement(character.data().name()));
            player.sendActionBar(MiniMsg.mini("prefix").append(finalMessage));
        }
    }
}

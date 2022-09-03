package de.clientdns.smash.character;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.character.enums.Character;
import de.clientdns.smash.config.Constants;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public class PlayerManager {

    private final Player player;

    public PlayerManager(Player player) {
        this.player = player;
    }

    public void setCharacter(Character character) {
        if (SmashPlugin.getPlugin().getCharacterCache().get(player).isPresent()) {
            SmashPlugin.getPlugin().getCharacterCache().replace(player, character);
        } else {
            SmashPlugin.getPlugin().getCharacterCache().put(player, character);
        }
        player.sendMessage(Constants.prefix()
                .append(Component.text("Du hast den Charakter", NamedTextColor.GRAY)
                        .append(character.getName()
                                .append(Component.text("ausgewählt.", NamedTextColor.GRAY)))));
    }
}

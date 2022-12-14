package de.clientdns.smash.listeners.custom;

import com.destroystokyo.paper.profile.PlayerProfile;
import de.clientdns.smash.api.character.Character;
import de.clientdns.smash.api.events.CharacterChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.profile.PlayerTextures;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;

import static de.clientdns.smash.config.Value.get;
import static de.clientdns.smash.config.Value.plain;
import static net.kyori.adventure.text.format.NamedTextColor.GREEN;

public class CharacterChangeListener implements Listener {

    public static void setSkin(@NotNull Player player, Character character) {
        if (character != null) {
            PlayerProfile profile = player.getPlayerProfile();
            PlayerTextures textures = profile.getTextures();
            PlayerTextures.SkinModel currentSkinModel = textures.getSkinModel();
            try {
                textures.setSkin(new URL(""), currentSkinModel);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @EventHandler
    void on(@NotNull CharacterChangeEvent event) {
        Player player = event.getPlayer();
        if (event.characterReplaced()) {
            if (!event.sameCharacter())
                player.sendMessage(get("prefix").append(plain("Du hast deinen Charakter zu ", GREEN).append(event.getCharacter().getName()).append(plain(" geändert!", GREEN))));
        } else
            player.sendMessage(get("prefix").append(plain("Du hast den Charakter ", GREEN).append(event.getCharacter().getName()).append(plain(" ausgewählt!", GREEN))));
    }
}

package de.clientdns.smash.api.config;

import de.clientdns.smash.SmashPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

public class MiniMsg {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
    private static final SmashConfig SMASH_CONFIG = SmashPlugin.getPlugin().getSmashConfig();

    public static Component mini(String path) {
        return MINI_MESSAGE.deserializeOrNull(String.valueOf(SMASH_CONFIG.getString(path)));
    }

    public static <R> @NotNull Component plain(R message) {
        return plain(message, null);
    }

    public static <R> @NotNull Component plain(R message, TextColor color) {
        return MINI_MESSAGE.deserialize(String.valueOf(message)).color(color);
    }
}
package de.ixn075.smash.config;

import de.ixn075.smash.SmashPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

public class MiniMsg {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
    private static final PluginConfig SMASH_CONFIG = SmashPlugin.getPlugin().getSmashConfig();

    public static @NotNull Component mini(String path) {
        return plain(SMASH_CONFIG.getStr(path));
    }

    public static @NotNull Component mini(String path, NamedTextColor color) {
        return plain(SMASH_CONFIG.getStr(path), color);
    }

    public static <R extends String> @NotNull Component plain(R message) {
        return MINI_MESSAGE.deserialize(message);
    }

    public static <R extends String> @NotNull Component plain(R message, NamedTextColor color) {
        return MINI_MESSAGE.deserialize(message).color(color);
    }
}

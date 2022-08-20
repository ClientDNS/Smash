package de.clientdns.smash.config;

import de.clientdns.smash.SmashPlugin;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Constants {

    public static boolean setPlayerInAdventure() {
        return SmashPlugin.getSmashConfig().getBoolean("config.set-player-in-adventure").orElse(false);
    }

    public static boolean disableJoinMessage() {
        return SmashPlugin.getSmashConfig().getBoolean("config.disable-join-message").orElse(true);
    }

    public static boolean disableQuitMessage() {
        return SmashPlugin.getSmashConfig().getBoolean("config.disable-quit-message").orElse(true);
    }

    @Contract(" -> new")
    public static @NotNull Component prefix() {
        return Component.text(SmashPlugin.getSmashConfig().getString("config.messages.prefix").orElse("§8[§6Smash§8] §r"));
    }
}

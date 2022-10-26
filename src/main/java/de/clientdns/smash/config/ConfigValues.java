package de.clientdns.smash.config;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

public class ConfigValues {

    public static @NotNull Component prefix() {
        return MiniMessage.miniMessage().deserialize(new Value<String>("messages.prefix").get());
    }

    public static @NotNull Component permissionRequired() {
        return MiniMessage.miniMessage().deserialize(new Value<String>("messages.permission-required").get());
    }

    public static @NotNull Component playerRequired() {
        return MiniMessage.miniMessage().deserialize(new Value<String>("messages.player-required").get());
    }

    public static @NotNull Component playerNotFound() {
        return MiniMessage.miniMessage().deserialize(new Value<String>("messages.player-not-found").get());
    }

    public static int minPlayers() {
        return new Value<Integer>("min-players").get();
    }
}

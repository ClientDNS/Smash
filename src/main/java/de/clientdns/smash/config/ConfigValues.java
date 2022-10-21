package de.clientdns.smash.config;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ConfigValues {

    private static final Component prefix = MiniMessage.miniMessage().deserialize(new Value<String>("messages.prefix").get());
    private static final Component permissionRequired = MiniMessage.miniMessage().deserialize(new Value<String>("messages.permission-required").get());
    private static final Component playerRequired = MiniMessage.miniMessage().deserialize(new Value<String>("messages.player-required").get());
    private static final Component playerNotFound = MiniMessage.miniMessage().deserialize(new Value<String>("messages.player-not-found").get());
    private static final int minPlayers = new Value<Integer>("min-players").get();

    public static Component prefix() {
        return prefix;
    }

    public static Component permissionRequired() {
        return permissionRequired;
    }

    public static Component playerRequired() {
        return playerRequired;
    }

    public static Component playerNotFound() {
        return playerNotFound;
    }

    public static int minPlayers() {
        return minPlayers;
    }
}

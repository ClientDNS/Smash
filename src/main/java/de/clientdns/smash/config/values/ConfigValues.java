package de.clientdns.smash.config.values;

import de.clientdns.smash.config.Value;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

public class ConfigValues {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
    private static Component PREFIX, PERMISSION_REQUIRED, PLAYER_REQUIRED, PLAYER_NOT_FOUND, CANNOT_SWITCH_GAMEMODE;
    private static Integer MIN_PLAYERS;

    public static @NotNull Component prefix() {
        if (PREFIX == null) {
            PREFIX = MINI_MESSAGE.deserialize(new Value<String>("messages.prefix").get());
        }
        return PREFIX;
    }

    public static @NotNull Component permissionRequired() {
        if (PERMISSION_REQUIRED == null) {
            PERMISSION_REQUIRED = MINI_MESSAGE.deserialize(new Value<String>("messages.permission-required").get());
        }
        return PERMISSION_REQUIRED;
    }

    public static @NotNull Component playerRequired() {
        if (PLAYER_REQUIRED == null) {
            PLAYER_REQUIRED = MINI_MESSAGE.deserialize(new Value<String>("messages.player-required").get());
        }
        return PLAYER_REQUIRED;
    }

    public static @NotNull Component playerNotFound() {
        if (PLAYER_NOT_FOUND == null) {
            PLAYER_NOT_FOUND = MINI_MESSAGE.deserialize(new Value<String>("messages.player-not-found").get());
        }
        return PLAYER_NOT_FOUND;
    }

    public static @NotNull Component cannotSwitchGameMode() {
        if (CANNOT_SWITCH_GAMEMODE == null) {
            CANNOT_SWITCH_GAMEMODE = MINI_MESSAGE.deserialize(new Value<String>("messages.cannot-switch-gamemode").get());
        }
        return CANNOT_SWITCH_GAMEMODE;
    }

    public static int minPlayers() {
        if (MIN_PLAYERS == null) {
            MIN_PLAYERS = new Value<Integer>("min-players").get();
        }
        return MIN_PLAYERS;
    }

    public static void reset() {
        PREFIX = null;
        PERMISSION_REQUIRED = null;
        PLAYER_REQUIRED = null;
        PLAYER_NOT_FOUND = null;
        CANNOT_SWITCH_GAMEMODE = null;
        MIN_PLAYERS = null;
    }
}

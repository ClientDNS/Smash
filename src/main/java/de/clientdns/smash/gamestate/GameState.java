package de.clientdns.smash.gamestate;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public enum GameState {

    LOBBY,
    INGAME,
    END;

    public static List<String> asList() {
        return Arrays.stream(GameState.values()).map(GameState::name).toList();
    }

    public static @Nullable GameState fromString(@NotNull String string) {
        for (GameState gameState : GameState.values()) {
            if (gameState.name().equalsIgnoreCase(string.toUpperCase())) {
                return gameState;
            }
        }
        return null;
    }
}

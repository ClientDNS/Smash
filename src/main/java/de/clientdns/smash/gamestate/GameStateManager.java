package de.clientdns.smash.gamestate;

import de.clientdns.smash.events.GameStateChangeEvent;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public class GameStateManager {

    private GameState currentState;

    public GameStateManager() {
        setGameState(GameState.LOBBY);
    }

    public GameState getGameState() {
        return currentState;
    }

    public void setGameState(@NotNull GameState state) {
        currentState = state;
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(currentState));
    }
}

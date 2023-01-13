package de.clientdns.smash.api.gamestate;

import de.clientdns.smash.api.events.GameStateChangeEvent;
import org.bukkit.Bukkit;

public class GameStateManager {

    private GameState currentState;

    public GameStateManager() {
        currentState = GameState.LOBBY;
    }

    public GameState getGameState() {
        return currentState;
    }

    public void setGameState(GameState state) {
        currentState = state;
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(currentState));
    }
}

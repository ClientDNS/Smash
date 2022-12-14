package de.clientdns.smash.api.gamestate;

import de.clientdns.smash.api.events.GameStateChangeEvent;
import org.bukkit.Bukkit;

public class GameStateManager {

    private GameState currentState;

    public GameStateManager() {
        currentState = GameState.LOBBY;
    }

    public GameState gameState() {
        return currentState;
    }

    public void gameState(GameState state) {
        currentState = state;
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(currentState));
    }

    public boolean lobbyState() {
        return currentState.equals(GameState.LOBBY);
    }

    public boolean ingameState() {
        return currentState.equals(GameState.INGAME);
    }

    public boolean endState() {
        return currentState.equals(GameState.END);
    }
}

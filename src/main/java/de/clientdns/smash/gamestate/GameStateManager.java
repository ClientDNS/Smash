package de.clientdns.smash.gamestate;

import de.clientdns.smash.events.GameStateChangeEvent;
import org.bukkit.Bukkit;

public class GameStateManager {

    private GameState currentState;

    public GameStateManager() {
        this.currentState = GameState.LOBBY;
    }

    public GameState getGameState() {
        return this.currentState;
    }

    public void setCurrentState(GameState state) {
        this.currentState = state;
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(this.currentState, state));
    }

    public boolean isLobbyState() {
        return this.currentState.equals(GameState.LOBBY);
    }

    public boolean isIngameState() {
        return this.currentState.equals(GameState.INGAME);
    }

    public boolean isEndState() {
        return this.currentState.equals(GameState.END);
    }
}

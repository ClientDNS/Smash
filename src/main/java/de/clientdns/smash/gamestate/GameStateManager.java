package de.clientdns.smash.gamestate;

public class GameStateManager {

    private GameState currentState;

    public GameStateManager() {
        setCurrentState(GameState.LOBBY);
    }

    public GameState getCurrentState() {
        return this.currentState;
    }

    public void setCurrentState(GameState newState) {
        this.currentState = newState;
    }
}

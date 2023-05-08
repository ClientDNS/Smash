package de.clientdns.smash.gamestate;

import de.clientdns.smash.events.GameStateChangeEvent;
import org.bukkit.Bukkit;

public class GameStateManager {

    private GameState currentState;

    public GameStateManager() {
        this.currentState = GameState.LOBBY;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(GameState gamestate) {
        this.currentState = gamestate;
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(gamestate));
    }
}

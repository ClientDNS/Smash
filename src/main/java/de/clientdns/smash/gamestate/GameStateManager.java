package de.clientdns.smash.gamestate;

import de.clientdns.smash.events.GameStateChangeEvent;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

@Getter
public class GameStateManager {

    private GameState currentState;

    public GameStateManager() {
        this.currentState = GameState.LOBBY;
    }

    public void setGameState(@NotNull GameState state) {
        this.currentState = state;
        Bukkit.getPluginManager().callEvent(new GameStateChangeEvent(currentState));
    }
}

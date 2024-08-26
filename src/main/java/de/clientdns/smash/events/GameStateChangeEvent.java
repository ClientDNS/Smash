package de.clientdns.smash.events;

import de.clientdns.smash.gamestate.GameState;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * This event is called when the game state changes.
 */
public class GameStateChangeEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final GameState gameState;

    public GameStateChangeEvent(GameState state) {
        this.gameState = state;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public GameState getGameState() {
        return gameState;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}

package de.clientdns.smash.api.events;

import de.clientdns.smash.api.gamestate.GameState;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * This event is called when the game state changes.
 *
 * @author ClientDNS
 * @version 1.0
 * @since 1.0
 */
public class GameStateChangeEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final GameState gameState;
    private boolean cancelled;

    /**
     * Creates a new game state change event.
     *
     * @param state The new game state
     */
    public GameStateChangeEvent(GameState state) {
        this.gameState = state;
        this.cancelled = false;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    /**
     * Returns the new game state.
     *
     * @return The new game state
     */
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}

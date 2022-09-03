package de.clientdns.smash.events;

import de.clientdns.smash.gamestate.GameState;
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
public class GameStateChangeEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final GameState state;

    /**
     * Creates a new game state change event.
     *
     * @param state The new game state
     */
    public GameStateChangeEvent(GameState state) {
        this.state = state;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public GameState getGameState() {
        return state;
    }
}

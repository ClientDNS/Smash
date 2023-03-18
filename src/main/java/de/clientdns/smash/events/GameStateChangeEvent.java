package de.clientdns.smash.events;

import de.clientdns.smash.gamestate.GameState;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * This event is called when the game state changes.
 */
@Getter
public class GameStateChangeEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final GameState gameState;

    /**
     * Creates a new game state change event.
     *
     * @param state The new game state
     */
    public GameStateChangeEvent(GameState state) {
        this.gameState = state;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}

package de.clientdns.smash.events;

import de.clientdns.smash.character.Character;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CharacterChangeEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final Character character;
    private final boolean replaced;
    private boolean cancelled;

    public CharacterChangeEvent(Player player, Character character, boolean replaced) {
        this.player = player;
        this.character = character;
        this.replaced = replaced;
        this.cancelled = false;
    }

    public boolean replaced() {
        return this.replaced;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Character getCharacter() {
        return this.character;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}

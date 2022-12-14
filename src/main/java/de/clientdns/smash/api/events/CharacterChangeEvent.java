package de.clientdns.smash.api.events;

import de.clientdns.smash.api.character.Character;
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
    private final boolean sameCharacter;
    private boolean cancelled;

    public CharacterChangeEvent(Player player, Character character, boolean replaced, boolean sameCharacter) {
        this.player = player;
        this.character = character;
        this.replaced = replaced;
        this.sameCharacter = sameCharacter;
        this.cancelled = false;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public boolean characterReplaced() {
        return replaced;
    }

    public boolean sameCharacter() {
        return sameCharacter;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public Player getPlayer() {
        return player;
    }

    public Character getCharacter() {
        return character;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}

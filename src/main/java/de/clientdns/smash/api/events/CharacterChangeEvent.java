package de.clientdns.smash.api.events;

import de.clientdns.smash.api.character.Character;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class CharacterChangeEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final Character before;
    private final Character character;
    private final boolean replaced;

    public CharacterChangeEvent(Player player, Character before, Character character, boolean replaced) {
        this.player = player;
        this.before = before;
        this.character = character;
        this.replaced = replaced;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public Character getBefore() {
        return before;
    }

    public Character getCharacter() {
        return character;
    }

    public boolean isReplaced() {
        return replaced;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }
}

package de.clientdns.smash.character;

import de.clientdns.smash.character.enums.Ability;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public record Character(int id, String name, Ability... abilities) {

    public Character(int id, String name, Ability @NotNull ... abilities) {
        this.id = id;
        this.name = name;
        this.abilities = abilities;
    }
}

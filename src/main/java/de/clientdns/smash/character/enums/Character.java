package de.clientdns.smash.character.enums;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public enum Character {

    MARIO(0, Component.text("Mario").color(NamedTextColor.RED), Ability.STAMP_ATTACK),
    DONKEY_KONG(1, Component.text("Donkey Kong", NamedTextColor.DARK_RED), Ability.STAMP_ATTACK),
    FLASH(2, Component.text("Flash").color(NamedTextColor.RED), Ability.SPEED),
    PIKACHU(3, Component.text("Pikachu", NamedTextColor.YELLOW), Ability.STAMP_ATTACK),
    SUPERMAN(4, Component.text("Superman", NamedTextColor.RED), Ability.STAMP_ATTACK),
    LINK(5, Component.text("Link", NamedTextColor.DARK_GREEN), Ability.SPEED_AND_JUMPBOOST);

    private final int id;
    private final Component name;
    private final Ability[] abilities;

    Character(int id, Component name, Ability... abilities) {
        this.id = id;
        this.name = name;
        this.abilities = abilities;
    }

    public int getId() {
        return id;
    }

    public Component getName() {
        return name;
    }

    public Ability[] getAbilities() {
        return abilities;
    }
}

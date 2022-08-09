package de.clientdns.smash.character;

import de.clientdns.smash.character.enums.Ability;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public enum Character {

    MARIO(0, Component.text("Mario").color(NamedTextColor.RED), Ability.STAMP_ATTACK),
    DONKEY_KONG(1, Component.text("Donkey Kong", NamedTextColor.DARK_RED), Ability.STAMP_ATTACK),
    FLASH(2, Component.text("Flash").color(NamedTextColor.RED), Ability.SPEED),
    PIKACHU(3, Component.text("Pikachu", NamedTextColor.YELLOW), Ability.STAMP_ATTACK),
    SUPERMAN(4, Component.text("Superman", NamedTextColor.RED), Ability.STAMP_ATTACK),
    LINK(5, Component.text("Link", NamedTextColor.DARK_GREEN), Ability.SPEED_AND_JUMPBOOST);

    @Getter
    private final int id;
    @Getter
    private final Component name;
    @Getter
    private final Ability[] abilities;

    Character(int id, Component name, Ability... abilities) {
        this.id = id;
        this.name = name;
        this.abilities = abilities;
    }
}

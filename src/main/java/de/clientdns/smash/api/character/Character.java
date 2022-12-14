package de.clientdns.smash.api.character;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

import java.security.SecureRandom;
import java.util.List;

import static de.clientdns.smash.config.Value.plain;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public enum Character {

    MARIO(0, plain("Mario", RED), List.of(Ability.STAMP_ATTACK)),
    DONKEY_KONG(1, plain("Donkey Kong", DARK_RED), List.of(Ability.STAMP_ATTACK)),
    FLASH(2, plain("Flash", RED), List.of(Ability.SPEED)),
    PIKACHU(3, plain("Pikachu", YELLOW), List.of(Ability.STAMP_ATTACK)),
    SUPERMAN(4, plain("Superman", RED), List.of(Ability.STAMP_ATTACK)),
    LINK(5, plain("Link", DARK_GREEN), List.of(Ability.JUMPBOOST, Ability.SPEED));

    private final int id;
    private final Component name;
    private final List<Ability> abilities;

    Character(int id, Component name, List<Ability> abilities) {
        this.id = id;
        this.name = name;
        this.abilities = abilities;
    }

    public static Character random() {
        SecureRandom random = new SecureRandom();
        return Character.values()[random.nextInt(Character.values().length)];
    }

    public static @Nullable Character findByName(Component name) {
        for (Character character : Character.values())
            if (character.getName().equals(name))
                return character;
        return null;
    }

    public int getId() {
        return id;
    }

    public Component getName() {
        return name;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }
}

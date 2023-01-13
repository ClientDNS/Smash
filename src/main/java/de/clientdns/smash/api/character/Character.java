package de.clientdns.smash.api.character;

import de.clientdns.smash.api.config.MiniMsg;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static net.kyori.adventure.text.format.NamedTextColor.*;

public enum Character implements Comparable<Character> {

    MARIO(0, MiniMsg.plain("Mario", RED), List.of(Ability.STAMP_ATTACK)),
    DONKEY_KONG(1, MiniMsg.plain("Donkey Kong", DARK_RED), List.of(Ability.STAMP_ATTACK)),
    FLASH(2, MiniMsg.plain("Flash", RED), List.of(Ability.SPEED)),
    PIKACHU(3, MiniMsg.plain("Pikachu", YELLOW), List.of(Ability.STAMP_ATTACK)),
    SUPERMAN(4, MiniMsg.plain("Superman", RED), List.of(Ability.STAMP_ATTACK)),
    LINK(5, MiniMsg.plain("Link", DARK_GREEN), List.of(Ability.JUMPBOOST, Ability.SPEED));

    private final int id;
    private final Component name;
    private final List<Ability> abilities;

    Character(int id, Component name, List<Ability> abilities) {
        this.id = id;
        this.name = name;
        this.abilities = abilities;
    }

    public static Character getRandom() {
        return values()[ThreadLocalRandom.current().nextInt(values().length)];
    }

    public static @Nullable Character findByName(Component name) {
        for (Character character : Character.values()) {
            if (character.getName().equals(name)) {
                return character;
            }
        }
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

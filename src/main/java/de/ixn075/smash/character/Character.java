package de.ixn075.smash.character;

import de.ixn075.smash.config.MiniMsg;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.kyori.adventure.text.format.NamedTextColor.DARK_RED;
import static net.kyori.adventure.text.format.NamedTextColor.RED;

public enum Character {

    MARIO(new CharacterData(0, MiniMsg.plain("Mario", RED), List.of(Ability.STAMP_ATTACK))), // Empty string
    DONKEY_KONG(new CharacterData(1, MiniMsg.plain("Donkey Kong", DARK_RED), List.of(Ability.STAMP_ATTACK))),
    FLASH(new CharacterData(2, MiniMsg.plain("Flash", RED), List.of(Ability.SPEED)));

    private final CharacterData data;

    Character(@NotNull CharacterData data) {
        this.data = data;
    }

    public CharacterData data() {
        return data;
    }
}

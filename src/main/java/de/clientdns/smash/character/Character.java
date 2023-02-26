package de.clientdns.smash.character;

import de.clientdns.smash.config.MiniMsg;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.kyori.adventure.text.format.NamedTextColor.*;

public enum Character {

    MARIO(new CharacterData(MiniMsg.plain("Mario", RED), "", List.of(Ability.STAMP_ATTACK))), // Empty string
    DONKEY_KONG(new CharacterData(MiniMsg.plain("Donkey Kong", DARK_RED), "", List.of(Ability.STAMP_ATTACK))),
    FLASH(new CharacterData(MiniMsg.plain("Flash", RED), "", List.of(Ability.SPEED))),
    PIKACHU(new CharacterData(MiniMsg.plain("Pikachu", YELLOW), "", List.of(Ability.STAMP_ATTACK))),
    SUPERMAN(new CharacterData(MiniMsg.plain("Superman", RED), "", List.of(Ability.STAMP_ATTACK))),
    LINK(new CharacterData(MiniMsg.plain("Link", DARK_GREEN), "", List.of(Ability.JUMPBOOST, Ability.SPEED)));

    private final CharacterData data;

    Character(@NotNull CharacterData data) {
        this.data = data;
    }

    public CharacterData getData() {
        return data;
    }
}

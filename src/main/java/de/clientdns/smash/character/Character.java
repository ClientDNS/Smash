package de.clientdns.smash.character;

import de.clientdns.smash.config.MiniMsg;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.kyori.adventure.text.format.NamedTextColor.*;

public enum Character {

    MARIO(new CharacterData(0, MiniMsg.plain("Mario", RED), "", List.of(Ability.STAMP_ATTACK))), // Empty string
    DONKEY_KONG(new CharacterData(1, MiniMsg.plain("Donkey Kong", DARK_RED), "", List.of(Ability.STAMP_ATTACK))),
    FLASH(new CharacterData(2, MiniMsg.plain("Flash", RED), "https://minotar.net/skin/Shlappeh", List.of(Ability.SPEED))),
    PIKACHU(new CharacterData(3, MiniMsg.plain("Pikachu", YELLOW), "", List.of(Ability.STAMP_ATTACK))),
    SUPERMAN(new CharacterData(4, MiniMsg.plain("Superman", RED), "", List.of(Ability.STAMP_ATTACK))),
    LINK(new CharacterData(5, MiniMsg.plain("Link", DARK_GREEN), "", List.of(Ability.JUMPBOOST, Ability.SPEED)));

    private final CharacterData data;

    Character(@NotNull CharacterData data) {
        this.data = data;
    }

    public CharacterData getData() {
        return data;
    }
}

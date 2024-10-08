package de.clientdns.smash.character;

import net.kyori.adventure.text.Component;

import java.util.List;

public record CharacterData(int id, Component name, List<Ability> abilities) {
}

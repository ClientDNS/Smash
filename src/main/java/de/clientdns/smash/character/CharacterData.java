package de.clientdns.smash.character;

import net.kyori.adventure.text.Component;

import java.util.List;

public class CharacterData {

    private final Component name;
    private final String skinUrl;
    private final List<Ability> abilities;

    public CharacterData(Component name, String skinUrl, List<Ability> abilities) {
        this.name = name;
        this.skinUrl = skinUrl;
        this.abilities = abilities;
    }

    public Component getName() {
        return name;
    }

    public String getSkinUrl() {
        return skinUrl;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }
}

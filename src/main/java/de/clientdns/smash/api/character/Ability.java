package de.clientdns.smash.api.character;

public enum Ability {

    STAMP_ATTACK("Stamp Attack"),
    BOW_ATTACK("Bow Attack"),
    LESS_KNOCKBACK("Less Knockback"),
    STRENGTH_KNOCKBACK("Strength Knockback"),
    SPEED("Speed"),
    JUMPBOOST("Jumpboost");

    private final String friendlyName;

    Ability(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}

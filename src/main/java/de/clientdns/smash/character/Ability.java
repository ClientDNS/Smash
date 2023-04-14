package de.clientdns.smash.character;

public enum Ability {

    STAMP_ATTACK("Stampfattacke"),
    BOW_ATTACK("Bogen-Attacke"),
    LESS_KNOCKBACK("Less Knockback"),
    STRENGTH_KNOCKBACK("Strength Knockback"),
    SPEED("Speed"),
    JUMPBOOST("Jump Boost");

    private final String friendlyName;

    Ability(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}

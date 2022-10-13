package de.clientdns.smash.character.enums;

public enum Gadget {

    MINIGUN("Minigun"),
    HOT_STICK("Hot Stick"),
    EXPLOSION_BOW("Explosion Bow");

    private final String friendlyName;

    Gadget(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}

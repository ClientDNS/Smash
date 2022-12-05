package de.clientdns.smash.character;

public enum Gadget {

    MINIGUN("Minigun"),
    HOT_STICK("Hot Stick"),
    EXPLOSIVE_BOW("Explosive Bow");

    private final String friendlyName;

    Gadget(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}

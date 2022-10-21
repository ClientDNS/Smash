package de.clientdns.smash.config;

import de.clientdns.smash.SmashPlugin;

public class Value<V> {

    private final V value;

    public Value(String path) {
        value = SmashPlugin.getPlugin().getSmashConfig().get(path);
    }

    public V get() {
        return value;
    }
}

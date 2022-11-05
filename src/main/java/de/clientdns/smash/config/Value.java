package de.clientdns.smash.config;

import de.clientdns.smash.SmashPlugin;
import org.jetbrains.annotations.NotNull;

public class Value<V> { // V = Expected value type (e.g. String, Integer, Boolean, etc.)

    private final V value;

    /**
     * Retrieves the value from the config.
     *
     * @param path The path to the value
     * @throws NullPointerException If the path is null
     */
    public Value(String path) throws NullPointerException {
        if (SmashPlugin.getPlugin().getSmashConfig().get(path) == null) {
            throw new NullPointerException("Path " + path + " is null");
        } else {
            this.value = SmashPlugin.getPlugin().getSmashConfig().get(path);
        }
    }

    @NotNull
    public V get() {
        if (this.value == null) {
            throw new NullPointerException("Value is null");
        } else {
            return this.value;
        }
    }
}

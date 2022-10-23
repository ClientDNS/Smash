package de.clientdns.smash.config;

import de.clientdns.smash.SmashPlugin;
import org.jetbrains.annotations.NotNull;

public class Value<V> { // V = Expected value type (e.g. String, Integer, Boolean, etc.)

    private final String path;
    private final V value;

    /**
     * Retrieves the value from the config.
     * If the value is not of the expected value type, an exception {@link ClassCastException} is thrown.
     *
     * @param path The path to the value
     */
    public Value(String path) throws NullPointerException {
        this.path = path;
        this.value = SmashPlugin.getPlugin().getSmashConfig().get(path);
    }

    public @NotNull V get() throws NullPointerException {
        if (this.value == null) {
            throw new NullPointerException("Value (path: " + this.path + ") is null.");
        }
        return this.value;
    }
}

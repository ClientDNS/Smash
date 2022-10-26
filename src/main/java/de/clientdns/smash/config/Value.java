package de.clientdns.smash.config;

import de.clientdns.smash.SmashPlugin;
import org.jetbrains.annotations.NotNull;

public class Value<V> { // V = Expected value type (e.g. String, Integer, Boolean, etc.)

    private final V value;

    /**
     * Retrieves the value from the config.
     *
     * @param path The path to the value
     */
    public Value(String path) throws NullPointerException {
        if (!isNull(path)) {
            this.value = SmashPlugin.getPlugin().getSmashConfig().get(path);
        } else {
            throw new NullPointerException("Value of " + path + " is null");
        }
    }

    private boolean isNull(String path) {
        return SmashPlugin.getPlugin().getSmashConfig().get(path) == null;
    }

    @NotNull
    public V get() {
        if (this.value.toString().isBlank()) {
            throw new IllegalStateException("Value is blank");
        }
        if (this.value.toString().isEmpty()) {
            throw new IllegalStateException("Value is empty");
        }
        return this.value;
    }
}

package de.clientdns.smash.api.map;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.api.config.SmashConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class Map implements Serializable {

    @Serial
    private static final long serialVersionUID = 6521080780746144772L;
    private final String name;
    private final Location[] spawnLocations;

    @Contract(pure = true)
    public Map(String name, Location @NotNull [] spawnLocations) {
        this.name = name;
        this.spawnLocations = new Location[spawnLocations.length];
        Bukkit.getLogger().info("Detected map creation '" + name + "'!");
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Map{" +
                "name='" + name + '\'' +
                ", spawnLocations=" + Arrays.toString(spawnLocations) +
                '}';
    }

    public CompletableFuture<Boolean> save() {
        SmashConfig config = SmashPlugin.getPlugin().getSmashConfig();
        config.set("maps." + name, this.toString());
        return CompletableFuture.supplyAsync(config::save);
    }
}

package de.clientdns.smash.map;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class Map {

    private final String name;
    private final Location[] spawnLocations;

    public Map(String name, Location @NotNull [] spawnLocations) {
        this.name = name;
        this.spawnLocations = new Location[spawnLocations.length];
    }
}

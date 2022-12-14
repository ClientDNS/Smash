package de.clientdns.smash.api.map;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class Map {

    private final String name;
    private final Location[] spawnLocations;

    public Map(String name, Location @NotNull [] spawnLocations) {
        this.name = name;
        this.spawnLocations = new Location[spawnLocations.length];
    }

    public String getName() {
        return name;
    }

    public Location[] getSpawnLocations() {
        return spawnLocations;
    }

    public Location getSpawnLocation(int index) {
        return spawnLocations[index];
    }

    public void setSpawnLocation(int index, Location location) {
        spawnLocations[index] = location;
    }
}

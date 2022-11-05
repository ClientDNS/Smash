package de.clientdns.smash.map;

import org.bukkit.Location;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public class Map {

    private final String name;
    private final Location[] spawnLocations;

    @ApiStatus.Experimental
    public Map(String name, Location @NotNull [] spawnLocations) {
        this.name = name;
        this.spawnLocations = new Location[spawnLocations.length];
    }

    public String getName() {
        return this.name;
    }

    public Location[] getSpawnLocations() {
        return this.spawnLocations;
    }

    public Location getSpawnLocation(int index) {
        return this.spawnLocations[index];
    }

    public void setSpawnLocation(int index, Location location) {
        this.spawnLocations[index] = location;
    }
}

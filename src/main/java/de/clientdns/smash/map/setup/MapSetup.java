package de.clientdns.smash.map.setup;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.map.Map;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Optional;

public class MapSetup {

    private final Player player;
    private final String mapName;
    private final String mapBuilder;
    private final Location[] spawnLocations;
    private final int indexSize;

    public MapSetup(Player player, String mapName, String mapBuilder, int indexSize) {
        this.player = player;
        this.mapName = mapName;
        this.mapBuilder = mapBuilder;
        this.indexSize = indexSize;
        this.spawnLocations = new Location[indexSize];
        SmashPlugin.getPlugin().getSetups().put(player, this);
    }

    public void update() {
        SmashPlugin.getPlugin().getSetups().put(player, this); // Old setup will be replaced.
    }

    public Optional<Map> finish(boolean abort) {
        SmashPlugin.getPlugin().getSetups().remove(player);
        return !abort ? Optional.of(new Map(mapName, mapBuilder, spawnLocations)) : Optional.empty();
    }

    public void setSpawnLocation(int index, Location location) {
        spawnLocations[index] = location;
    }

    public Player getPlayer() {
        return player;
    }

    public String getMapName() {
        return mapName;
    }

    public String getMapBuilder() {
        return mapBuilder;
    }

    public int getIndexSize() {
        return indexSize;
    }

    public int countLocations() {
        int count = 0;
        for (Location location : spawnLocations) {
            if (location != null) {
                count++;
            }
        }
        return count;
    }

    public Location[] getSpawnLocations() {
        return spawnLocations;
    }
}

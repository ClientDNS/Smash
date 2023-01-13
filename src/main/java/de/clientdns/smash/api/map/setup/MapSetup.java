package de.clientdns.smash.api.map.setup;

import de.clientdns.smash.api.SmashApi;
import de.clientdns.smash.api.map.Map;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MapSetup {

    private final Player player;
    private final String mapName;
    private final Location[] spawnLocations;
    private final int indexSize;

    public MapSetup(Player player, String mapName, int indexSize) {
        this.player = player;
        this.mapName = mapName;
        this.indexSize = indexSize;
        this.spawnLocations = new Location[indexSize];
        SmashApi.getSetups().put(player, this);
    }

    public Map finish(boolean abort) {
        SmashApi.getSetups().remove(player);
        return !abort ? new Map(mapName, spawnLocations) : null;
    }

    public void setSpawnLocation(int index, Location location) {
        spawnLocations[index] = location;
        player.sendMessage("Index #" + index + " -> " + location);
    }

    public Player getPlayer() {
        return player;
    }

    public String getMapName() {
        return mapName;
    }

    public int getIndexSize() {
        return indexSize;
    }

    public int countNonNullArrayValues() {
        int count = 0;
        for (Location location : spawnLocations) {
            if (location != null) {
                count++;
            }
        }
        return count;
    }
}

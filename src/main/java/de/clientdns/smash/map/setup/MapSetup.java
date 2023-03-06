package de.clientdns.smash.map.setup;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.map.Map;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MapSetup {

    private final Player player;
    private final String name;
    private final Material icon;
    private final Location[] spawnLocations;
    private final int indexSize;

    public MapSetup(Player player, String name, Material icon, int indexSize) {
        this.player = player;
        this.name = name;
        this.icon = icon;
        this.indexSize = indexSize;
        this.spawnLocations = new Location[indexSize];
        SmashPlugin.getPlugin().getSetups().put(player, this);
    }

    public void delete() {
        SmashPlugin.getPlugin().getSetups().remove(player);
    }

    public Map finish() {
        delete();
        return new Map(name, icon, spawnLocations);
    }

    public void setSpawnLocation(int index, Location location) {
        spawnLocations[index] = location;
    }

    public Player getPlayer() {
        return player;
    }

    public String getName() {
        return name;
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

package de.clientdns.smash.api.map.setup;

import de.clientdns.smash.api.exceptions.SetupFailedException;
import de.clientdns.smash.api.events.SetupBeginEvent;
import de.clientdns.smash.api.events.SetupFinishEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MapSetup {

    private final Player player;
    private final String mapName;
    private final List<Location> spawnLocations;

    public MapSetup(Player player, String mapName) {
        this.player = player;
        this.mapName = mapName;
        this.spawnLocations = new ArrayList<>();
    }

    public void start() {
        Bukkit.getPluginManager().callEvent(new SetupBeginEvent(player, this));
    }

    public boolean save() throws SetupFailedException {
        if (spawnLocations.size() < 2)
            throw new SetupFailedException("Not enough spawn locations. (min: 2, current: " + spawnLocations.size() + ")");
        return true;
    }

    public void finish() {
        Bukkit.getPluginManager().callEvent(new SetupFinishEvent(player, this));
    }

    public void addSpawnLocation(Location location) {
        spawnLocations.add(location);
    }

    public List<Location> getSpawnLocations() {
        return spawnLocations;
    }

    public String getMapName() {
        return mapName;
    }

    public Player getPlayer() {
        return player;
    }
}

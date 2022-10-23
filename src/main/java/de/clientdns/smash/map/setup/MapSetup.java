package de.clientdns.smash.map.setup;

import de.clientdns.smash.events.SetupBeginEvent;
import de.clientdns.smash.events.SetupFinishEvent;
import de.clientdns.smash.exceptions.setup.SetupFailedException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MapSetup {

    private final Player player;
    private final String mapName;
    private final ArrayList<Location> spawnLocations;

    public MapSetup(Player player, String mapName) {
        this.player = player;
        this.mapName = mapName;
        this.spawnLocations = new ArrayList<>();
    }

    public void start() {
        Bukkit.getPluginManager().callEvent(new SetupBeginEvent(player, this));
    }

    public boolean save() {
        if (this.spawnLocations.size() < 2) {
            throw new SetupFailedException("Not enough spawn locations. (min: 2, you: " + this.spawnLocations.size() + ")");
        }
        return true;
    }

    public void finish() throws SetupFailedException {
        try {
            if (this.save()) {
                //
            }
        } catch (SetupFailedException e) {
            throw new SetupFailedException("Failed to save map setup.", e);
        }
        Bukkit.getPluginManager().callEvent(new SetupFinishEvent(player, this));
    }

    public void addSpawnLocation(Location location) {
        this.spawnLocations.add(location);
    }

    public List<Location> getSpawnLocations() {
        return this.spawnLocations;
    }

    public String getMapName() {
        return this.mapName;
    }

    public Player getPlayer() {
        return this.player;
    }
}

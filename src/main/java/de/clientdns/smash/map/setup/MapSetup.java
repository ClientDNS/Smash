package de.clientdns.smash.map.setup;

import de.clientdns.smash.SmashPlugin;
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
        SmashPlugin.getPlugin().getSetupManager().add(this.player, this);
    }

    public void delete() {
        SmashPlugin.getPlugin().getSetupManager().remove(this.player);
    }

    public boolean save() throws IllegalStateException {
        if (this.spawnLocations.size() < 2) {
            throw new IllegalStateException("Not enough spawn locations, min 2, you " + this.spawnLocations.size());
        }
        return true;
    }

    public void finish() {
        if (this.save()) {
            this.delete();
        }
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

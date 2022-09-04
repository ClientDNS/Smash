package de.clientdns.smash.map.setup;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MapSetup {

    private final Player player;
    private final String mapName;
    private final Location[] spawnLocations;

    public MapSetup(Player player, String mapName) {
        this.player = player;
        this.mapName = mapName;
        this.spawnLocations = new Location[6];
    }

    public void start() {
        SmashPlugin.getPlugin().getSetupManager().add(player, this);
    }

    public void delete() {
        SmashPlugin.getPlugin().getSetupManager().remove(player);
    }

    public void finish() {
        // TODO => Save map
        delete();
    }

    public void setSpawnLocation(int index, Location location) {
        spawnLocations[index] = location;
    }

    public Location getSpawnLocation(int index) {
        return spawnLocations[index];
    }

    public String getMapName() {
        return mapName;
    }

    public Player getPlayer() {
        return player;
    }
}

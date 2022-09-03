package de.clientdns.smash.setup;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MapSetup {

    private final Player player;
    private final String name;
    private final Location[] spawnLocations = new Location[6];

    public MapSetup(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    public void start() {
        SmashPlugin.getPlugin().getSetupManager().add(player, this);
    }

    public void abort() {
        SmashPlugin.getPlugin().getSetupManager().remove(player);
    }

    public void finish() {
        // TODO => Save map
        SmashPlugin.getPlugin().getSetupManager().remove(player);
    }

    public void setSpawnLocation(int index, Location location) {
        spawnLocations[index] = location;
    }

    public Location getSpawnLocation(int index) {
        return spawnLocations[index];
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }
}

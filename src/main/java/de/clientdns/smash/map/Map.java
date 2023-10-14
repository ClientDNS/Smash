package de.clientdns.smash.map;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.SmashConfig;
import org.bukkit.Location;

public record Map(String name, Location[] spawnLocations) {

    public boolean write() { // Must be saved in the game which can be done with "/config save".
        if (spawnLocations.length < 2) {
            return false;
        }
        SmashConfig config = SmashPlugin.getPlugin().getSmashConfig();
        if (config == null) {
            SmashPlugin.getPlugin().getLogger().info("Could not get config file, exiting.");
            return false;
        }
        config.set("maps." + name + ".spawnLocations", spawnLocations);
        return true;
    }
}

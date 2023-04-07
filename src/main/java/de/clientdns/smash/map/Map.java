package de.clientdns.smash.map;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.SmashConfig;
import org.bukkit.Location;
import org.bukkit.Material;

public record Map(String name, Material item, Location[] spawnLocations) {

    public boolean write() {
        if (spawnLocations.length < 2) {
            return false;
        }
        SmashConfig config = SmashPlugin.getPlugin().getSmashConfig();
        config.set("maps." + name + ".material", item.name());
        config.set("maps." + name + ".spawnLocations", spawnLocations);
        return true;
    }
}

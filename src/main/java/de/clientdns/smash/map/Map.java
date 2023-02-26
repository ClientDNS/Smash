package de.clientdns.smash.map;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.SmashConfig;
import org.bukkit.Location;

import java.util.concurrent.atomic.AtomicBoolean;

public record Map(String name, String builder, Location[] spawnLocations) {

    public boolean save() {
        AtomicBoolean success = new AtomicBoolean();
        if (spawnLocations.length < 2) {
            return success.get();
        }
        SmashConfig config = SmashPlugin.getPlugin().getSmashConfig();
        config.set("maps." + name + ".builder", builder);
        config.set("maps." + name + ".spawnLocations", spawnLocations);
        config.save(exception -> {
            if (exception == null) {
                success.set(true);
            }
        });
        return success.get();
    }
}

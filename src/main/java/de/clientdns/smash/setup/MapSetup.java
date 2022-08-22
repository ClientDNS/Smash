package de.clientdns.smash.setup;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.Location;

public class MapSetup {

    private final String name;
    private final SetupManager setupManager = SmashPlugin.getSetupManager();
    private final Location[] spawnLocations = new Location[6];
    private boolean running = false;

    public MapSetup(String name) {
        this.name = name;
    }

    public void start() {
        running = true;
        setupManager.add(this);
    }

    public void stop() {
        running = false;
        setupManager.remove(this);
    }

    public void finish() {
        if (running) {
            stop();
        }
        setupManager.remove(this);
    }

    public String getName() {
        return name;
    }

    public boolean running() {
        return running;
    }

    public Location[] getSpawnLocations() {
        return spawnLocations;
    }
}

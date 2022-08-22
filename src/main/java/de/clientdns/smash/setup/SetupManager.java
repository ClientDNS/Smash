package de.clientdns.smash.setup;

import java.util.ArrayList;
import java.util.List;

public class SetupManager {

    private final List<MapSetup> runningMapSetups = new ArrayList<>();

    public void add(MapSetup mapSetup) {
        runningMapSetups.add(mapSetup);
    }

    public void remove(MapSetup mapSetup) {
        runningMapSetups.remove(mapSetup);
    }

    public MapSetup get(String mapName) {
        for (MapSetup mapSetup : runningMapSetups) {
            if (mapSetup.getName().equals(mapName)) {
                return mapSetup;
            }
        }
        return null;
    }

    public boolean contains(String mapName) {
        for (MapSetup mapSetup : runningMapSetups) {
            if (mapSetup.getName().equals(mapName)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(MapSetup mapSetup) {
        return runningMapSetups.contains(mapSetup);
    }
}

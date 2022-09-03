package de.clientdns.smash.setup;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SetupManager {

    private final Map<Player, MapSetup> mapSetups = new HashMap<>();

    public void add(Player player, MapSetup setup) {
        mapSetups.put(player, setup);
    }

    public void remove(Player player) {
        mapSetups.remove(player);
    }

    public void remove(MapSetup setup) {
        mapSetups.values().remove(setup);
    }

    public Map<Player, MapSetup> getRunningMapSetups() {
        return mapSetups;
    }

    public boolean contains(Player player) {
        return mapSetups.containsKey(player);
    }

    public MapSetup get(Player player) {
        return mapSetups.get(player);
    }

    public void clear() {
        mapSetups.clear();
    }

    public boolean isEmpty() {
        return mapSetups.isEmpty();
    }

    public int size() {
        return mapSetups.size();
    }
}

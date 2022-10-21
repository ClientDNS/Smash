package de.clientdns.smash.map.setup;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class SetupManager {

    private final Map<Player, MapSetup> mapSetups = new ConcurrentHashMap<>();

    public void add(Player player, MapSetup setup) {
        this.mapSetups.put(player, setup);
    }

    public void remove(Player player) {
        this.mapSetups.remove(player);
    }

    public Optional<MapSetup> get(Player player) {
        if (this.mapSetups.containsKey(player)) {
            return Optional.ofNullable(this.mapSetups.get(player));
        }
        return Optional.empty();
    }

    public void clear() {
        this.mapSetups.clear();
    }
}

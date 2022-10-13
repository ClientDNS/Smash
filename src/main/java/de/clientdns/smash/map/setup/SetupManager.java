package de.clientdns.smash.map.setup;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SetupManager {

    private final Map<Player, MapSetup> mapSetups = new HashMap<>();

    public void add(Player player, MapSetup setup) {
        this.mapSetups.put(player, setup);
    }

    public void remove(Player player) {
        this.mapSetups.remove(player);
    }

    public Optional<MapSetup> get(Player player) {
        if (this.mapSetups.containsKey(player)) {
            return Optional.of(this.mapSetups.get(player));
        }
        return Optional.empty();
    }

    public void clear() {
        this.mapSetups.clear();
    }
}

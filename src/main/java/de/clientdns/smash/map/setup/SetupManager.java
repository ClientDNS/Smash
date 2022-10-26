package de.clientdns.smash.map.setup;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class SetupManager {

    private final Map<Player, MapSetup> setups = new ConcurrentHashMap<>();

    public void add(Player player, MapSetup setup) {
        this.setups.put(player, setup);
    }

    public void remove(Player player) {
        this.setups.remove(player);
    }

    public Optional<MapSetup> get(Player player) {
        if (this.setups.containsKey(player)) {
            return Optional.ofNullable(this.setups.get(player));
        }
        return Optional.empty();
    }

    public void clear() {
        this.setups.clear();
    }
}

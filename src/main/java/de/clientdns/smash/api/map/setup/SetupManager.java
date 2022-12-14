package de.clientdns.smash.api.map.setup;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SetupManager {

    private final Map<Player, MapSetup> setups = new HashMap<>();

    public void add(Player player, MapSetup setup) {
        setups.put(player, setup);
    }

    public void remove(Player player) {
        this.setups.remove(player);
    }

    public MapSetup setup(Player player) {
        if (setups.containsKey(player))
            return setups.get(player);
        return null;
    }

    public void clear() {
        setups.clear();
    }
}

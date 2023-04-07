package de.clientdns.smash.voting;

import de.clientdns.smash.map.Map;
import org.bukkit.entity.Player;

public class Vote {

    private final Player player;
    private Map map;

    public Vote(Player player) {
        this.player = player;
    }

    public void choose(Map map) {
        this.map = map;
    }

    public Player getPlayer() {
        return player;
    }

    public Map getMap() {
        return map;
    }
}

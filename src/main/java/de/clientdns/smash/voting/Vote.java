package de.clientdns.smash.voting;

import de.clientdns.smash.map.Map;
import org.bukkit.entity.Player;

public class Vote {

    private final Player player;
    private Map map;

    public Vote(Player player, Map map) {
        this.player = player;
        this.map = map;
    }

    public Player getPlayer() {
        return player;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}

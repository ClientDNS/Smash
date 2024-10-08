package de.clientdns.smash.voting;

import de.clientdns.smash.map.Map;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class VoteManager {

    private final HashMap<Player, Map> votes = new HashMap<>();

    public void add(Player player, Map map) {
        votes.put(player, map);
    }

    public Map get(Player player) {
        return votes.get(player);
    }

    public void edit(Player player, Map newMap) {
        votes.replace(player, newMap);
    }

    public boolean voted(Player player) {
        return votes.containsKey(player);
    }
}

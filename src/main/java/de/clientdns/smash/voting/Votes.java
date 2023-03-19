package de.clientdns.smash.voting;

import de.clientdns.smash.map.Map;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Votes {

    public static final HashMap<Player, Vote> votes = new HashMap<>();

    public static void vote(Player player, Map map) {
        Vote vote = new Vote(map);
        votes.put(player, vote);
    }

    public static void remove(Player player) {
        votes.remove(player);
    }

    public static Vote get(Player player) {
        return votes.get(player);
    }
}

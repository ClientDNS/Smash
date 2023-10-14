package de.clientdns.smash.voting;

import de.clientdns.smash.map.Map;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class Voting {

    private final Player player;
    private final HashMap<Map, Integer> votes;

    public Voting(Player player) {
        this.player = player;
        this.votes = new HashMap<>();
    }

    public static @Nullable Map getFinalMap() {
        //
        return null;
    }

    public static void addVote(Map map) {

    }

    public void removeVote(Map map) {
        Vote vote = new Vote(player, map);
        if (votes.containsKey(vote.getMap())) {
            votes.put(vote.getMap(), votes.get(vote.getMap()) - 1);
        }
    }

    public HashMap<Map, Integer> getVotes() {
        return votes;
    }
}












































































































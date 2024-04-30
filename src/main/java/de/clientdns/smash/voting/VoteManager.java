package de.clientdns.smash.voting;

import de.clientdns.smash.map.Map;

import java.util.HashMap;
import java.util.UUID;

public class VoteManager {

    private static final HashMap<UUID, Vote> votes = new HashMap<>();

    public Vote update(UUID uuid, Map map) {
        Vote vote = votes.get(uuid);
        votes.replace(uuid, new Vote(uuid, map));
        return votes.get(uuid);
    }

    public Vote add(UUID uuid, Map map) {
        Vote vote = new Vote(uuid, map);
        votes.put(uuid, vote);
        return votes.get(uuid);
    }

    public void remove(UUID uuid) {
        votes.remove(uuid);
    }
}

package de.clientdns.smash.voting;

import de.clientdns.smash.map.Map;

import java.util.UUID;

public class Vote {

    private final UUID uuid;
    private Map map;

    public Vote(UUID uuid, Map map) {
        this.uuid = uuid;
        this.map = map;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}

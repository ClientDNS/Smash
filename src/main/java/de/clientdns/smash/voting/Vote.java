package de.clientdns.smash.voting;

import de.clientdns.smash.map.Map;

public class Vote {

    private Map map;

    public Vote(Map map) {
        this.map = map;
    }

    public Map getMap() {
        return map;
    }

    public void changeMap(Map map) {
        this.map = map;
    }
}

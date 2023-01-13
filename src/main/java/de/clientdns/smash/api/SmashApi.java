package de.clientdns.smash.api;

import de.clientdns.smash.api.gamestate.GameStateManager;
import de.clientdns.smash.api.map.setup.MapSetup;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SmashApi {

    private static final Map<Player, MapSetup> setups = new HashMap<>();

    private static final GameStateManager gameStateManager = new GameStateManager();

    public static GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public static Map<Player, MapSetup> getSetups() {
        return setups;
    }
}

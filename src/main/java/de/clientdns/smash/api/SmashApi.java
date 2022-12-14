package de.clientdns.smash.api;

import de.clientdns.smash.api.gamestate.GameStateManager;
import de.clientdns.smash.api.map.setup.SetupManager;

public class SmashApi {

    private static final GameStateManager gameStateManager = new GameStateManager();
    private static final SetupManager setupManager = new SetupManager();

    public static GameStateManager gameStateManager() {
        return gameStateManager;
    }

    public static SetupManager setupManager() {
        return setupManager;
    }
}

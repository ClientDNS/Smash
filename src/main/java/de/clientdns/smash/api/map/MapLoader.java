package de.clientdns.smash.api.map;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.api.config.SmashConfig;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

public class MapLoader {

    @Contract(pure = true)
    public static @Nullable Map loadMap() {
        SmashConfig config = SmashPlugin.getPlugin().getSmashConfig();
        return null;
    }
}

package de.clientdns.smash.map;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.SmashConfig;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class MapLoader {

    public static boolean contains(String name) {
        SmashConfig config = SmashPlugin.getPlugin().getSmashConfig();
        return config.contains("maps." + name);
    }

    public static @NotNull Set<Map> getMaps() {
        SmashConfig config = SmashPlugin.getPlugin().getSmashConfig();
        Set<Map> maps = new java.util.HashSet<>();
        for (String mapKey : config.getSection("maps").getKeys(false)) {
            maps.add(MapLoader.loadMap(mapKey));
        }
        return maps;
    }

    public static @Nullable Map loadMap(String name) {
        SmashConfig config = SmashPlugin.getPlugin().getSmashConfig();
        if (config.contains(name)) {
            return null;
        }
        String builder = config.getStr("maps." + name + ".builder");
        Location[] locations = config.getLocs("maps." + name + ".spawnLocations");
        return new Map(name, builder, locations);
    }
}

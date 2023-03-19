package de.clientdns.smash.map;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.SmashConfig;
import org.bukkit.Location;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MapLoader {

    private static final SmashConfig config = SmashPlugin.getPlugin().getSmashConfig();

    public static boolean contains(String name) {
        return config.contains("maps." + name);
    }

    public static @NotNull List<Map> getMaps() {
        List<Map> maps = new ArrayList<>();
        for (String mapKey : config.getSection("maps").getKeys(false)) {
            maps.add(loadMap(mapKey));
        }
        return maps;
    }

    public static @Nullable Map loadMap(String name) {
        if (config.contains(name)) {
            return null;
        }
        Material icon = Material.getMaterial(config.getStr("maps." + name + ".material"));
        Location[] locations = config.getLocs("maps." + name + ".spawnLocations");
        return new Map(name, icon, locations);
    }

    public Map random() {
        if (getMaps().isEmpty()) {
            return null;
        }
        int randomIndex = ThreadLocalRandom.current().nextInt(getMaps().size());
        return getMaps().get(randomIndex);
    }
}

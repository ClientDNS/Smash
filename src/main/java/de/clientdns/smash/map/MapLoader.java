package de.clientdns.smash.map;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.SmashConfig;
import org.bukkit.Location;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {

    private static final SmashConfig config = SmashPlugin.getPlugin().getSmashConfig();

    public static boolean contains(String name) {
        return config.contains("maps." + name);
    }

    public static @NotNull List<Map> getMaps() {
        List<Map> maps = new ArrayList<>();
        for (String mapKey : config.getSection("maps").getKeys(false)) {
            maps.add(load(mapKey));
        }
        return maps;
    }

    public static @Nullable Map load(String name) {
        if (config.contains(name)) {
            return null;
        }

        String materialName = config.getStr("maps." + name + ".material");

        if (materialName == null) {
            throw new NullPointerException("Could not retrieve material from config.");
        }

        Location[] locations = config.getLocs("maps." + name + ".spawnLocations");
        Material icon = Material.getMaterial(materialName);

        if (icon == null) {
            throw new NullPointerException("No material " + materialName + " found.");
        }
        return new Map(name, icon, locations);
    }

    public static @Nullable Map random() {
        if (getMaps().isEmpty()) {
            return null;
        }
        int randomIndex = new SecureRandom().nextInt(getMaps().size());
        return getMaps().get(randomIndex);
    }
}

package de.clientdns.smash.mapping.initializer;

import de.clientdns.smash.SmashPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MapInitializer<E extends File> {

    private final List<E> maps;
    File mapsFolder = SmashPlugin.getPlugin().getMapsFolder();

    public MapInitializer() {

        maps = new ArrayList<>();

        for (File file : mapsFolder.listFiles()) {
            SmashPlugin.getPlugin().getLogger().info("[+] found Map: " + file.getName());
            maps.add((E) file);
            SmashPlugin.getPlugin().getLogger().info("[+] added Map: " + file.getName());
        }
    }

    public List<E> getMaps() {
        return maps;
    }
}

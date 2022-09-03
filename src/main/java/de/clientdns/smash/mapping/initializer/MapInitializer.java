package de.clientdns.smash.mapping.initializer;

import de.clientdns.smash.SmashPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MapInitializer<E extends File> {

    private final List<File> maps;
    File mapsFolder = SmashPlugin.getMapsFolder();

    public MapInitializer() {

        maps = Arrays.stream(Objects.requireNonNull(mapsFolder.listFiles())).toList();

        // Loop through all files in the maps folder
        for (File map : maps) {
            // Check if the file is a directory
            if (map.isDirectory()) {
                // Loop through all files in the directory
                for (File file : Objects.requireNonNull(map.listFiles())) {
                    maps.add(file);
                    SmashPlugin.getPlugin().getLogger().info("Found Map: " + file.getName());
                }
            }
        }
    }

    public List<File> getMaps() {
        return maps;
    }


    public static void main(String[] args) {
    }
}

package de.clientdns.smash.mapping;

import de.clientdns.smash.util.ExtendedMap;
import org.bukkit.Location;

import java.io.File;
import java.util.Optional;

public interface Map {

    Optional<File> getMapFile();

    Optional<File> getPropertyFile();

    Optional<String> getName();

    Optional<String> getGame();

    Optional<String> getBuilder();

    Optional<ExtendedMap<String, Location>> getLocations();

    Optional<Location> getLocation(String key);

    void loadLocations(Location absoluteLocation);

}

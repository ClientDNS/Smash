package de.clientdns.smash.mapping;

import de.clientdns.smash.util.ExtendedMap;
import org.bukkit.Location;

import java.io.File;
import java.util.HashMap;
import java.util.Optional;

public class MapImpl<K, V, I> implements Map {

    private final K name;
    private final V builder;
    private final I game;
    private HashMap<String, Location> locations = new HashMap<>();

    public MapImpl(K name, V builder, I game) {
        this.name = name;
        this.builder = builder;
        this.game = game;
    }

    @Override
    public Optional<File> getMapFile() {
        return Optional.empty();
    }

    @Override
    public Optional<File> getPropertyFile() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getName() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getGame() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getBuilder() {
        return Optional.empty();
    }

    @Override
    public Optional<ExtendedMap<String, Location>> getLocations() {
        return Optional.empty();
    }

    @Override
    public Optional<Location> getLocation(String key) {
        return Optional.empty();
    }

    @Override
    public void loadLocations(Location absoluteLocation) {

    }
}

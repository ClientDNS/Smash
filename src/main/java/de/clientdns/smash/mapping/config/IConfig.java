package de.clientdns.smash.mapping.config;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IConfig {

    //Documentation see interface
    Optional<Set<String>> getKeys(boolean deep);

    Optional<String> getString(String path);

    Optional<Integer> getInt(String path);

    Optional<Double> getDouble(String path);

    Optional<Boolean> getBoolean(String path);

    Optional<List<String>> getStringList(String path);

    Optional<List<Integer>> getIntList(String path);

    Optional<List<Double>> getDoubleList(String path);

    Optional<List<?>> getList(String path);

    Optional<Location> getLocation(String path);

    boolean containsNot(String path);

    <K extends String, V> void set(@NotNull K key, @NotNull V value, String description, V defaultValue);

    void setLocation(String name, Location location);

    void save();

    static MapConfig getMapConfig(String path) {
        return new MapConfig<>(path);
    }

}

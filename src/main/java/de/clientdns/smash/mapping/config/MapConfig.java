package de.clientdns.smash.mapping.config;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class MapConfig<K extends String, P extends String> implements IConfig {

    public K CONFIG_FILE_NAME;
    private final char separator = File.separatorChar;
    public P CONFIG_FILE_PATH;
    public FileConfiguration config;

    public MapConfig(String path) {
        CONFIG_FILE_NAME = (K) ("plugins" + separator + "Smash" + separator);
        File configFolder = new File(CONFIG_FILE_PATH);
        File configFile = new File(configFolder, CONFIG_FILE_NAME);
        CompletableFuture.runAsync(() -> {
            try {
                if (configFolder.mkdir()) {
                    SmashPlugin.getPlugin().getLogger().info("[+] Config folder: " + configFolder.getPath());
                }
                if (configFile.createNewFile()) {
                    SmashPlugin.getPlugin().getLogger().info("[+] Config file: " + configFile.getPath());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).thenAccept(Void -> config = SmashPlugin.getPlugin().getConfig()).join();
    }

    @Override
    public Optional<Set<String>> getKeys(boolean deep) {
        return Optional.of(config.getKeys(deep));
    }

    @Override
    public Optional<String> getString(String path) {
        return Optional.ofNullable(config.getString(path));
    }
    @Override
    public Optional<Integer> getInt(String path) {
        return Optional.of(config.getInt(path));
    }
    @Override
    public Optional<Double> getDouble(String path) {
        return Optional.of(config.getDouble(path));
    }
    @Override
    public Optional<Boolean> getBoolean(String path) {
        return Optional.of(config.getBoolean(path));
    }
    @Override
    public Optional<List<String>> getStringList(String path) {
        return Optional.of(config.getStringList(path));
    }
    @Override
    public Optional<List<Integer>> getIntList(String path) {
        return Optional.of(config.getIntegerList(path));
    }
    @Override
    public Optional<List<Double>> getDoubleList(String path) {
        return Optional.of(config.getDoubleList(path));
    }
    @Override
    public Optional<List<?>> getList(String path) {
        return Optional.ofNullable(config.getList(path));
    }
    @Override
    public Optional<Location> getLocation(String path) {
        return Optional.ofNullable(config.getLocation(path));
    }
    @Override
    public boolean containsNot(String path) {
        return !config.contains(path);
    }
    @Override
    public <K extends String, V> void set(@NotNull K key, @NotNull V value, String description, V defaultValue) {
        if (containsNot(key)) {
            config.set(key, value);
            config.setComments(key, List.of(description, "Default value: '" + defaultValue + "'"));
        }
    }
    @Override
    public void setLocation(String name, Location location) {
        if (containsNot("maps." + name)) {
            config.set("maps." + name, location);
        }
    }
    @Override
    public void save() {
        SmashPlugin.getPlugin().saveConfig();
    }
}


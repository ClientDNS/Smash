package de.clientdns.smash.config;

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

public class Config {

    public final String CONFIG_FILE_NAME = "config.yml";
    private final char separator = File.separatorChar;
    public final String CONFIG_FILE_PATH = "plugins" + separator + "Smash" + separator;
    public File configFile;
    public FileConfiguration config;

    public Config() {
        File configFolder = new File(CONFIG_FILE_PATH);
        CompletableFuture.runAsync(() -> {
            try {
                configFile = new File(configFolder, CONFIG_FILE_NAME);
                if (configFolder.mkdirs()) {
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

    public Optional<Set<String>> getKeys(boolean deep) {
        return Optional.of(config.getKeys(deep));
    }

    public Optional<String> getString(String path) {
        return Optional.ofNullable(config.getString(path));
    }

    public Optional<Integer> getInt(String path) {
        return Optional.of(config.getInt(path));
    }

    public Optional<Double> getDouble(String path) {
        return Optional.of(config.getDouble(path));
    }

    public Optional<Boolean> getBoolean(String path) {
        return Optional.of(config.getBoolean(path));
    }

    public Optional<List<String>> getStringList(String path) {
        return Optional.of(config.getStringList(path));
    }

    public Optional<List<Integer>> getIntList(String path) {
        return Optional.of(config.getIntegerList(path));
    }

    public Optional<List<Double>> getDoubleList(String path) {
        return Optional.of(config.getDoubleList(path));
    }

    public Optional<List<?>> getList(String path) {
        return Optional.ofNullable(config.getList(path));
    }

    public Optional<Location> getLocation(String path) {
        return Optional.ofNullable(config.getLocation(path));
    }

    public boolean containsNot(String path) {
        return !config.contains(path);
    }

    public <K extends String, V> void set(@NotNull K key, @NotNull V value, String description) {
        if (containsNot(key)) {
            config.set(key, value);
            config.setComments(key, List.of(description));
        }
    }

    public void setLocation(String name, Location location) {
        if (containsNot("maps." + name)) {
            config.set("maps." + name, location);
        }
    }

    public void save() {
        CompletableFuture.runAsync(() -> {
            try {
                config.save(configFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).join();
    }

    public void close() {
        config = null;
    }

    public void reload() {
        config = SmashPlugin.getPlugin().getConfig();
    }
}

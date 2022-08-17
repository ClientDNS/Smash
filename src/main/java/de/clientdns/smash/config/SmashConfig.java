package de.clientdns.smash.config;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SmashConfig {

    public final String CONFIG_FILE_NAME = "config.yml";
    public final FileConfiguration config;
    private final char separator = File.separatorChar;
    public final String CONFIG_FILE_PATH = "plugins" + separator + "Smash" + separator;

    public SmashConfig() {
        File configFolder = new File(CONFIG_FILE_PATH);
        File configFile = new File(configFolder, CONFIG_FILE_NAME);
        try {
            if (configFolder.mkdir()) {
                SmashPlugin.getPlugin().getLogger().info("Created config folder: " + configFolder.getPath());
            }
            if (configFile.createNewFile()) {
                SmashPlugin.getPlugin().getLogger().info("Created config file: " + configFile.getPath());
            }
            config = SmashPlugin.getPlugin().getConfig();
            SmashPlugin.getPlugin().getLogger().info("Loaded config file: " + configFile.getPath());
        } catch (IOException ioException) {
            throw new RuntimeException("Could not create config file", ioException);
        }
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

    public Optional<List<Boolean>> getBooleanList(String path) {
        return Optional.of(config.getBooleanList(path));
    }

    public <K extends String, V> void set(@NotNull K key, @NotNull V value, List<String> comments) {
        if (config.get(key) == null) {
            config.set(key, value);

            if (comments.size() > 0) {
                config.setComments(key, comments);
            }
        }
    }

    public void save() {
        SmashPlugin.getPlugin().saveConfig();
    }
}

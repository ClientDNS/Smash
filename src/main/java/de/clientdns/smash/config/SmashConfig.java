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

    public final String CONFIG_FILE_PATH = "plugins/Smash/";
    public final String CONFIG_FILE_NAME = "config.yml";
    public final FileConfiguration config;

    public SmashConfig() {
        File configFolder = new File(CONFIG_FILE_PATH);
        File configFile = new File(configFolder, CONFIG_FILE_NAME);
        try {
            if (configFolder.mkdir()) {
                SmashPlugin.getPlugin().getLogger().info("Created config folder: " + configFolder.getPath());
            }
            if (configFile.createNewFile()) {
                SmashPlugin.getPlugin().getLogger().info("Created config file: " + configFile.getPath());
            } else {
                SmashPlugin.getPlugin().getLogger().info("Loaded config file: " + configFile.getPath());
            }
            config = SmashPlugin.getPlugin().getConfig();
        } catch (IOException ioException) {
            throw new RuntimeException("Could not create config file", ioException);
        }
    }

    public Set<String> getKeys(boolean deep) {
        return config.getKeys(deep);
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

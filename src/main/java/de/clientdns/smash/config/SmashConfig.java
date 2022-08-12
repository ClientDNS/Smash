package de.clientdns.smash.config;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class SmashConfig {

    public final String CONFIG_FILE_PATH = "plugins/Smash/";
    public final String CONFIG_FILE_NAME = "config.yml";
    public final FileConfiguration config;

    public SmashConfig() {
        File configFolders = new File(CONFIG_FILE_PATH);
        File configFile = new File(configFolders, CONFIG_FILE_NAME);
        try {
            if (configFolders.mkdirs()) {
                SmashPlugin.getPlugin().getLogger().info("Created config folder: " + configFolders.getPath());
            }
            if (configFile.createNewFile()) {
                SmashPlugin.getPlugin().getLogger().info("Config file created: " + configFile.getPath());
            }
            config = SmashPlugin.getPlugin().getConfig();
        } catch (IOException ioException) {
            throw new RuntimeException("Could not create config file", ioException);
        }
    }

    public Set<String> getKeys(boolean deep) {
        return config.getKeys(deep);
    }

    public Optional<String> getString(String key) {
        return Optional.ofNullable(config.getString(key));
    }

    public Optional<Integer> getInt(String key) {
        return Optional.of(config.getInt(key));
    }

    public Optional<Double> getDouble(String key) {
        return Optional.of(config.getDouble(key));
    }

    public Optional<Boolean> getBoolean(String key) {
        return Optional.of(config.getBoolean(key));
    }

    public <K extends String, V> void set(@NotNull K key, @NotNull V value) {
        if (config.get(key) == null) {
            config.set(key, value);
        } else if (!Objects.equals(config.get(key), value)) {
            config.set(key, value);
        }
    }

    public void save() {
        SmashPlugin.getPlugin().saveConfig();
    }
}

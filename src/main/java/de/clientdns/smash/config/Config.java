package de.clientdns.smash.config;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Config {

    public final String CONFIG_FILE_NAME = "config.yml";
    public final String CONFIG_FILE_PATH = "plugins/Smash/";
    public File configFile;
    public FileConfiguration config;

    public Config() {
        File configFolder = new File(CONFIG_FILE_PATH);
        try {
            configFile = new File(configFolder, CONFIG_FILE_NAME);
            if (configFolder.mkdirs()) {
                SmashPlugin.getPlugin().getLogger().info("Created config folder.");
            }
            if (configFile.createNewFile()) {
                SmashPlugin.getPlugin().getLogger().info("Created config file.");
            }
            config = SmashPlugin.getPlugin().getConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<String> getKeys(boolean deep) {
        return config.getKeys(deep);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String path) {
        return (T) config.get(path);
    }

    public String getString(String path) {
        return config.getString(path);
    }

    public int getInt(String path) {
        return config.getInt(path);
    }

    public boolean getBool(String path) {
        return config.getBoolean(path);
    }

    public Location getLocation(String path) {
        return config.getLocation(path);
    }

    public boolean containsNot(String path) {
        return !config.contains(path);
    }

    public <V> void set(@NotNull String key, @NotNull V value, String... comment) {
        if (containsNot(key)) {
            config.set(key, value);
            config.setInlineComments(key, List.of(comment));
        }
    }

    public boolean save() {
        try {
            config.save(configFile);
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Could not save config file", e);
        }
    }
}

package de.clientdns.smash.config;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
                SmashPlugin.getPlugin().getLogger().info("Created config folder");
            }
            if (configFile.createNewFile()) {
                SmashPlugin.getPlugin().getLogger().info("Created config file");
            }
            config = SmashPlugin.getPlugin().getConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <V> V get(String path) throws NullPointerException {
        if (config.get(path) == null) {
            throw new NullPointerException("Value of " + path + " is null");
        }
        return (V) config.get(path);
    }

    @SuppressWarnings("unchecked")
    public <V> V get(String path, V defaultValue) throws ClassCastException {
        return (V) config.get(path, defaultValue);
    }

    public boolean contains(String path) {
        return config.contains(path);
    }

    public <V> void set(@NotNull String key, @NotNull V value) {
        set(key, value, "");
    }

    public <V> void set(@NotNull String key, @NotNull V value, String comment) {
        if (!contains(key)) {
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

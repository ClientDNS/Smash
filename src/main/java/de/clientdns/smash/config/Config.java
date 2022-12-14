package de.clientdns.smash.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Config {

    private static FileConfiguration config;
    private final Logger logger;
    public File configFile;

    public Config(String fileName) {
        logger = Logger.getLogger(Config.class.getName());
        try {
            this.configFile = new File("plugins/Smash/", fileName);
            if (this.configFile.getParentFile().mkdirs()) {
                logger.info("Created 'Smash' folder.");
            }
            if (this.configFile.createNewFile()) {
                logger.info("Created '" + fileName + "' file.");
            }
            config = YamlConfiguration.loadConfiguration(this.configFile);
        } catch (IOException e) {
            throw new RuntimeException("Could not create config file", e);
        }
    }

    public boolean contains(String path) {
        return config.contains(path);
    }

    public <V> void setDefaultValue(@NotNull KeyValue<V> keyValue) {
        if (!contains(keyValue.key()))
            config.set(keyValue.key(), keyValue.value());
    }

    public String get(String path, String def) throws NullPointerException {
        return config.getString(path, def);
    }

    public int get(String path, int def) throws NullPointerException {
        return config.getInt(path, def);
    }

    public long get(String path, long def) throws NullPointerException {
        return config.getLong(path, def);
    }

    public double get(String path, double def) throws NullPointerException {
        return config.getDouble(path, def);
    }

    public boolean get(String path, boolean def) throws NullPointerException {
        return config.getBoolean(path, def);
    }

    public void save() {
        try {
            config.save(this.configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

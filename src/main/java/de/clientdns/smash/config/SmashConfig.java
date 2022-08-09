package de.clientdns.smash.config;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SmashConfig {

    public final String CONFIG_FILE_NAME = "config.yml";
    public final String CONFIG_FILE_PATH = "plugins/Smash/";
    public final String CONFIG_FILE_FULL_PATH = CONFIG_FILE_PATH + CONFIG_FILE_NAME;

    public SmashConfig() {
        File configFolders = new File(CONFIG_FILE_PATH);
        File configFile = new File(configFolders, CONFIG_FILE_NAME);
        try {
            if (configFolders.mkdirs()) {
                SmashPlugin.getPlugin().getLogger().info("Created config folder: " + configFolders.getPath());
            }
            if (configFile.createNewFile()) {
                SmashPlugin.getPlugin().getLogger().info("Config file created in " + CONFIG_FILE_FULL_PATH);
            }
        } catch (IOException ioException) {
            throw new RuntimeException("Could not create config file.", ioException);
        }
    }

    @Contract(" -> new")
    public @NotNull FileConfiguration getConfiguration() {
        return SmashPlugin.getPlugin().getConfig();
    }

    public boolean set(@NotNull String key, @NotNull Object value) {
        if (getConfiguration().get(key) == null) {
            getConfiguration().set(key, value);
        }
        return Objects.equals(getConfiguration().get(key), value);
    }

    public void save() {
        SmashPlugin.getPlugin().saveConfig();
    }
}

package de.clientdns.smash.config;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.values.ConfigValues;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class Config {

    public final String configFilePath = "plugins/Smash/";
    public final String configFileName = "config.yml";
    public File configFile;
    public FileConfiguration config;

    public Config() {
        File configFolder = new File(this.configFilePath);
        try {
            configFile = new File(configFolder, this.configFileName);
            Logger logger = SmashPlugin.plugin().getLogger();
            if (configFolder.mkdir()) {
                logger.info("Created config folder");
            }
            if (this.configFile.createNewFile()) {
                logger.info("Created config file");
            }
            load();
            setDefaultValues();
            save();
        } catch (IOException e) {
            throw new RuntimeException("Could not create config file", e);
        }
    }

    public <V> V get(String path) throws NullPointerException {
        return get(path, null);
    }

    @SuppressWarnings("unchecked")
    public <V> V get(String path, V defaultValue) throws NullPointerException {
        return (V) config.get(path, defaultValue);
    }

    public Set<String> getKeys(boolean deep) {
        return config.getKeys(deep);
    }

    public boolean notContains(String path) {
        return !config.contains(path);
    }

    public <V> void set(@NotNull String key, @NotNull V value) {
        if (notContains(key)) {
            config.set(key, value);
        }
    }

    public <V> void set(@NotNull String key, @NotNull V value, String comment) {
        if (notContains(key)) {
            config.set(key, value);
            config.setInlineComments(key, List.of(comment));
        }
    }

    public void setDefaultValues() {
        set("prefix", "<gold>Smash</gold> <dark_gray>|</dark_gray> ", "Only in MiniMessage format!");
        set("unknown-command", "<red>Unbekannter Befehl. ($command)</red>", "Only in MiniMessage format!");
        set("permission-required", "<red>Du hast keine Berechtigung, dies zu tun.</red>", "Only in MiniMessage format!");
        set("player-required", "<red>Du musst ein Spieler sein, um dies zu tun.</red>", "Only in MiniMessage format!");
        set("player-not-found", "<red>Der Spieler wurde nicht gefunden.</red>", "Only in MiniMessage format!");
        set("cannot-switch-gamemode", "<red>Du kannst deinen Spielmodus nicht ändern, während du in einem Spiel bist.</red>", "Only in MiniMessage format!");
        set("config-reloaded", "<green>Die Config wurde neu geladen.</green>", "Only in MiniMessage format!");
        set("min-players", 2, "The minimum amount of players required to start a game.");
    }

    public void reload() {
        ConfigValues.reset();
        this.load();
    }

    private void load() {
        this.config = YamlConfiguration.loadConfiguration(this.configFile);
    }

    public void save() {
        try {
            this.config.save(this.configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

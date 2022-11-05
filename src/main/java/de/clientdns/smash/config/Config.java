package de.clientdns.smash.config;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.values.ConfigValues;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Config {

    public final String configFilePath = "plugins/Smash/";
    public final String configFileName = "config.yml";
    public File configFile;
    public FileConfiguration config;

    public Config() {
        File configFolder = new File(this.configFilePath);
        try {
            configFile = new File(configFolder, this.configFileName);
            if (configFolder.mkdirs()) {
                SmashPlugin.getPlugin().getLogger().info("Created config folder");
            }
            if (this.configFile.createNewFile()) {
                SmashPlugin.getPlugin().getLogger().info("Created config file");
            }
            load();
        } catch (IOException e) {
            throw new RuntimeException("Could not create config file", e);
        }
    }

    @SuppressWarnings("unchecked")
    public <V> V get(String path) throws NullPointerException, ClassCastException {
        return (V) config.get(path);
    }

    @SuppressWarnings("unchecked")
    public <V> V get(String path, V defaultValue) throws NullPointerException, ClassCastException {
        return (V) config.get(path, defaultValue);
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
        set("min-players", 2, "The minimum amount of players required to start a game");
        set("messages.prefix", "<gold>Smash</gold> <dark_gray>|</dark_gray> ", "Only in MiniMessage format!");
        set("messages.permission-required", "<red>Du hast keine Berechtigung, dies zu tun.</red>", "Only in MiniMessage format!");
        set("messages.player-required", "<red>Du musst ein Spieler sein, um dies zu tun.</red>", "Only in MiniMessage format!");
        set("messages.player-not-found", "<red>Der Spieler wurde nicht gefunden.</red>", "Only in MiniMessage format!");
        set("messages.cannot-switch-gamemode", "<red>Du kannst deinen Spielmodus nicht ändern, während du in einem Spiel bist.</red>", "Only in MiniMessage format!");
    }

    public void reload() {
        SmashPlugin.getPlugin().getLogger().info("Performing config reload");
        this.load();
    }

    private void load() {
        this.config = YamlConfiguration.loadConfiguration(this.configFile);
        SmashPlugin.getPlugin().getLogger().info("Resetting internal config values");
        ConfigValues.reset();
    }

    public void save() {
        try {
            this.config.save(this.configFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

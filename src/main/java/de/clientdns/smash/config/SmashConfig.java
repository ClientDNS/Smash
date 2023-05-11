package de.clientdns.smash.config;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class SmashConfig {

    private final Logger logger;
    private final File configFile;
    private boolean changed;
    private FileConfiguration fileConfiguration;

    /**
     * Creates a new instance of a SmashConfig class. It can be used to create a new file
     *
     * @param fileName The name to create a file under it.
     */
    public SmashConfig(@NotNull @Pattern("[a-z_\\-.]+") String fileName) {
        this.logger = SmashPlugin.getPlugin().getLogger();
        this.configFile = new File("plugins/Smash/", fileName);
        this.changed = false;
        try {
            if (configFile.getParentFile().mkdirs()) {
                logger.info(String.format("'%s' created.", configFile.getParentFile().getName()));
            }
            if (configFile.createNewFile()) {
                logger.info(String.format("'%s' file created.", fileName));
            }
        } catch (Throwable e) {
            throw new RuntimeException("Error while creating file", e);
        }
    }

    public <V> void set(@NotNull String path, V value) {
        if (fileConfiguration.contains(path)) {
            return;
        }
        fileConfiguration.set(path, value);
        if (!changed) changed = true;
    }

    public <V> void setWithComment(@NotNull String path, V value, String comment) {
        if (fileConfiguration.contains(path)) {
            return;
        }
        fileConfiguration.set(path, value);
        fileConfiguration.setComments(path, List.of(comment));
        if (!changed) changed = true;
    }

    public void reset() {
        setWithComment("deny-gamemode-switch", true, "Prevents player from changing gamemode in-game.");
        setWithComment("min-players", 2, "Sets the minimum amount of players to able to start the game.");
        set("prefix", "<gold>Smash</gold> <dark_gray>|</dark_gray> ");
        set("unknown-command", "<red>Unknown command. ($command)</red>");
        set("join-message", "<green>$name joined the server.</green>");
        set("quit-message", "<red>$name left the server.</red>");
        set("character-select", "<green>Your character has been set to $name.</green>");
        set("character-switch", "<green>Your character has been changed to $name.</green>");
        set("permission-required", "<red>You have no permission to do that.</red>");
        set("player-required", "<red>You have to be a player to do that.</red>");
        set("player-not-found", "<red>The player was not found.</red>");
        set("switch-gamemode", "<red>You cannot change your gamemode while playing.</red>");
        set("maps", List.of());
    }

    public String getStr(String path) {
        return getStr(path, null);
    }

    public String getStr(String path, String def) {
        return fileConfiguration.getString(path, def);
    }

    public int getInt(String path) {
        return getInt(path, 0);
    }

    public int getInt(String path, int def) {
        return fileConfiguration.getInt(path, def);
    }

    public boolean getBool(String path) {
        return getBool(path, false);
    }

    public boolean getBool(String path, boolean def) {
        return fileConfiguration.getBoolean(path, def);
    }

    public List<String> getStrList(String path) {
        return fileConfiguration.getStringList(path);
    }

    public List<Integer> getIntList(String path) {
        return fileConfiguration.getIntegerList(path);
    }

    public List<Boolean> getBoolList(String path) {
        return fileConfiguration.getBooleanList(path);
    }

    public Location[] getLocs(String path) {
        List<?> configList = getList(path);
        List<Location> locs = new ArrayList<>();
        if (configList != null) {
            for (Object object : configList) {
                if (object instanceof Location location) {
                    locs.add(location);
                }
            }
        }

        Location[] locationArray = new Location[locs.size()];

        int i = 0;
        for (Location location : locs) {
            locationArray[i] = location;
            i++;
        }
        return locationArray;
    }

    public List<?> getList(String path) {
        return fileConfiguration.getList(path);
    }

    public ConfigurationSection getSection(String path) {
        return fileConfiguration.getConfigurationSection(path);
    }

    public boolean noMaps() {
        if (isSection("maps")) return getSection("maps").getKeys(false).isEmpty();
        if (isList("maps")) return getList("maps").isEmpty();
        return true;
    }

    public boolean isChanged() {
        return changed;
    }

    public void save(@NotNull Consumer<Throwable> consumer) {
        try {
            if (!changed) {
                logger.info("No changes detected, canceling...");
            } else { // There are changes
                fileConfiguration.save(configFile);
                discardChanges();
            }
        } catch (IOException exception) {
            consumer.accept(exception);
        } finally {
            consumer.accept(null);
        }
    }

    public void discardChanges() {
        this.changed = false;
    }

    public boolean contains(String path) {
        return fileConfiguration.contains(path);
    }

    public boolean isSection(String path) {
        return fileConfiguration.isConfigurationSection(path);
    }

    public boolean isList(String path) {
        return fileConfiguration.isList(path);
    }

    public boolean exists() {
        return configFile.exists();
    }

    public boolean empty() {
        return fileConfiguration.getKeys(true).isEmpty();
    }

    public void load() {
        fileConfiguration = YamlConfiguration.loadConfiguration(configFile);
    }
}

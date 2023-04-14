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
                logger.info(String.format("Created '%s' folder.", configFile.getParentFile().getName()));
            }
            if (configFile.createNewFile()) {
                logger.info(String.format("Created '%s' file.", fileName));
            }
        } catch (Throwable e) {
            throw new RuntimeException("Failed to create file", e);
        }
    }

    public <V> void set(@NotNull String path, V value) {
        if (fileConfiguration.contains(path)) {
            return;
        }
        fileConfiguration.set(path, value);
        if (!changed) changed = true;
    }

    public void reset() {
        set("deny-gamemode-switch", true);
        set("min-players", 2);
        set("prefix", "<red>Smash</red> <dark_gray>|</dark_gray> ");
        set("unknown-command", "<red>Unbekannter Befehl. ($command)</red>");
        set("join-message", "<green>$name ist dem Server beigetreten.</green>");
        set("quit-message", "<red>$name hat den Server verlassen.</red>");
        set("permission-required", "<red>Du hast keine Berechtigung, dies zu tun.</red>");
        set("player-required", "<red>Du musst ein Spieler sein, um dies zu tun.</red>");
        set("player-not-found", "<red>Der Spieler wurde nicht gefunden.</red>");
        set("switch-gamemode", "<red>Du kannst deinen Spielmodus nicht ändern.</red>");
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
                logger.info("Keine Änderungen erkannt, abbrechen.");
                consumer.accept(null);
            }
            fileConfiguration.save(configFile);
            discardChanges();
            consumer.accept(null);
        } catch (IOException exception) {
            consumer.accept(exception);
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

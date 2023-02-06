package de.clientdns.smash.config;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class SmashConfig {

    private final Logger logger;
    private final File configFile;
    private final String fileName;
    private FileConfiguration fileConfiguration;

    /**
     * Creates a new instance of a SmashConfig class. It can be used to create a new file
     *
     * @param fileName The name to create a file under it.
     */
    public SmashConfig(@NotNull @Pattern("[a-z_\\-.]+") String fileName) {
        this.fileName = fileName;
        this.logger = SmashPlugin.getPlugin().getLogger();
        this.configFile = new File("plugins/Smash/", fileName);
        try {
            load();
        } catch (IOException e) {
            throw new RuntimeException("Failed to create file", e);
        }
        set("deny-gamemode-switch", true);
        set("min-players", 2);
        set("prefix", "<gold>Smash</gold> <dark_gray>|</dark_gray> ");
        set("unknown-command", "<red>Unbekannter Befehl. ($command)</red>");
        set("join-message", "<green>$name ist dem Server beigetreten.</green>");
        set("quit-message", "<red>$name hat den Server verlassen.</red>");
        set("permission-required", "<red>Du hast keine Berechtigung, dies zu tun.</red>");
        set("player-required", "<red>Du musst ein Spieler sein, um dies zu tun.</red>");
        set("player-not-found", "<red>Der Spieler wurde nicht gefunden.</red>");
        set("switch-gamemode", "<red>Du kannst deinen Spielmodus nicht ändern.</red>");
        set("maps", List.of());
        save();
    }

    public void set(@NotNull String path, Object value) {
        if (fileConfiguration.contains(path)) {
            return;
        }
        fileConfiguration.set(path, value);
    }

    public String getString(@NotNull String path) {
        return fileConfiguration.getString(path);
    }

    public int getInt(@NotNull String path) {
        return fileConfiguration.getInt(path);
    }

    public boolean getBoolean(@NotNull String path) {
        return fileConfiguration.getBoolean(path);
    }

    public List<?> getList(String path) {
        return fileConfiguration.getList(path);
    }

    public List<String> getStringList(String path) {
        return fileConfiguration.getStringList(path);
    }

    public boolean save() {
        try {
            fileConfiguration.save(configFile);
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public void load() throws IOException {
        if (configFile.getParentFile().mkdirs()) {
            logger.info(String.format("Created '%s' folder.", configFile.getParentFile().getName()));
        }
        if (configFile.createNewFile()) {
            logger.info(String.format("Created '%s' file.", fileName));
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(configFile);
    }
}

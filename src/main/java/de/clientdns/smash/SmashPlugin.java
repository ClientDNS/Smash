package de.clientdns.smash;

import de.clientdns.smash.commands.ConfigCommand;
import de.clientdns.smash.commands.SetupCommand;
import de.clientdns.smash.config.SmashConfig;
import de.clientdns.smash.gamestate.GameStateManager;
import de.clientdns.smash.listeners.*;
import de.clientdns.smash.listeners.custom.GameStateChangeListener;
import de.clientdns.smash.map.MapLoader;
import de.clientdns.smash.map.setup.MapSetup;
import de.clientdns.smash.player.PlayerManager;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SmashPlugin extends JavaPlugin {

    private static SmashPlugin plugin;
    private GameStateManager gameStateManager;
    private Map<Player, MapSetup> setups;
    private SmashConfig smashConfig;

    public static SmashPlugin getPlugin() {
        return plugin;
    }

    @Override
    public void onLoad() {
        if (plugin == null) {
            plugin = this;
        } else {
            getLogger().severe("Konnte keine neue Plugin-Instanz zuweisen, deaktiviere Plugin.");
            getServer().getPluginManager().disablePlugin(this);
        }
        double classVersion = NumberUtils.toDouble(System.getProperty("java.class.version"));
        if (classVersion < 61.0) {
            getLogger().warning("Du nutzt eine nicht unterstütze Java-Version! (Klasse: " + classVersion + ")");
            getLogger().warning("Bitte aktualisiere auf Java 17 (Klasse: 61) oder höher.");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onEnable() {
        smashConfig = new SmashConfig("smash.yml");
        if (!smashConfig.exists()) {
            getLogger().severe("Konnte die Konfiguration nicht finden, deaktiviere Plugin.");
            getServer().getPluginManager().disablePlugin(this);
        } else {
            smashConfig.load();
            if (smashConfig.empty()) {
                getLogger().warning("Konfiguration ist leer, setze auf Standardwerte zurück.");
                smashConfig.reset();
                smashConfig.save(exception -> {
                    if (exception == null) {
                        getLogger().info("Standardwerte in Konfiguration gespeichert.");
                    } else {
                        getLogger().severe("Konfiguration ist leer (konnte Standardwerte nicht speichern), deaktiviere Plugin.");
                        getServer().getPluginManager().disablePlugin(this);
                    }
                });
            }
        }

        if (getSmashConfig().noMaps()) {
            getLogger().warning("Keine Karten in der Konfiguration gefunden.");
            getLogger().info("Richte eine Karte mit '/setup start' ein.");
        } else {
            for (String mapKey : getSmashConfig().getSection("maps").getKeys(false)) {
                de.clientdns.smash.map.Map map = MapLoader.load(mapKey);
                if (map != null) {
                    getLogger().info("'" + mapKey + "' vorgeladen.");
                } else {
                    getLogger().warning("Fehler beim Vorladen von " + mapKey + ", ignoriere es.");
                }
            }
        }

        List<Listener> listeners = new ArrayList<>();
        // custom events
        listeners.add(new GameStateChangeListener());

        listeners.add(new AsyncPlayerPreLoginListener());
        listeners.add(new BlockBreakListener());
        listeners.add(new BlockPlaceListener());
        listeners.add(new ChunkLoadListener());
        listeners.add(new EntityDamageListener());
        listeners.add(new EntityPickupItemListener());
        listeners.add(new FoodLevelChangeListener());
        listeners.add(new InventoryClickListener());
        listeners.add(new PlayerDeathListener());
        listeners.add(new PlayerDropItemListener());
        listeners.add(new PlayerGameModeChangeListener());
        listeners.add(new PlayerInteractListener());
        listeners.add(new PlayerItemHeldListener());
        listeners.add(new PlayerJoinListener());
        listeners.add(new PlayerMoveListener());
        listeners.add(new PlayerQuitListener());

        for (Listener eventListener : listeners) {
            getServer().getPluginManager().registerEvents(eventListener, this);
        }

        List<Command> commands = new ArrayList<>();
        commands.add(new ConfigCommand("config"));
        commands.add(new SetupCommand("setup"));

        for (Command command : commands) {
            getServer().getCommandMap().register("smash", command);
        }

        for (World world : Bukkit.getWorlds()) {
            world.setDifficulty(Difficulty.PEACEFUL);
            world.getWorldBorder().reset();
            world.setThundering(false);
            world.setStorm(false);
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
            world.setGameRule(GameRule.DO_ENTITY_DROPS, false);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            world.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
            world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
            world.setGameRule(GameRule.DO_WARDEN_SPAWNING, false);
            world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
            world.setGameRule(GameRule.DO_MOB_LOOT, false);
            world.setGameRule(GameRule.MOB_GRIEFING, false);
            world.setGameRule(GameRule.NATURAL_REGENERATION, false);
            world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, false);
            world.setGameRule(GameRule.LOG_ADMIN_COMMANDS, false);
            world.setGameRule(GameRule.KEEP_INVENTORY, false);
            world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
            world.setGameRule(GameRule.UNIVERSAL_ANGER, false);
            world.setGameRule(GameRule.REDUCED_DEBUG_INFO, true);
            world.setGameRule(GameRule.MAX_ENTITY_CRAMMING, 8);
        }
        gameStateManager = new GameStateManager();
        setups = new HashMap<>();
    }

    @Override
    public void onDisable() {
        PlayerManager.clearCharacters();
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public Map<Player, MapSetup> getSetups() {
        return setups;
    }

    public SmashConfig getSmashConfig() {
        return smashConfig;
    }
}

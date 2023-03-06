package de.clientdns.smash;

import de.clientdns.smash.commands.ConfigCommand;
import de.clientdns.smash.commands.SetupCommand;
import de.clientdns.smash.config.SmashConfig;
import de.clientdns.smash.gamestate.GameStateManager;
import de.clientdns.smash.listeners.*;
import de.clientdns.smash.listeners.custom.GameStateChangeListener;
import de.clientdns.smash.map.Map;
import de.clientdns.smash.map.MapLoader;
import de.clientdns.smash.map.setup.MapSetup;
import de.clientdns.smash.player.PlayerManager;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ConcurrentHashMap;

public final class SmashPlugin extends JavaPlugin {

    private static SmashPlugin plugin;
    private GameStateManager gameStateManager;
    private ConcurrentHashMap<Player, MapSetup> setups;
    private SmashConfig smashConfig;

    public static SmashPlugin getPlugin() {
        return plugin;
    }

    @Override
    public void onLoad() {
        if (plugin == null) {
            plugin = this;
        } else {
            getLogger().severe("Cannot assign another plugin instance, disabling.");
            getServer().getPluginManager().disablePlugin(this);
        }
        double classVersion = NumberUtils.toDouble(System.getProperty("java.class.version"));
        if (classVersion < 61.0) {
            getLogger().warning("You are using an unsupported java version (class: " + classVersion + ")!");
            getLogger().warning("Please update to Java 17 (class: 61).");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onEnable() {
        smashConfig = new SmashConfig("smash.yml");
        if (!smashConfig.exists()) {
            getLogger().severe("Could not find config file, disabling.");
            getServer().getPluginManager().disablePlugin(this);
        } else {
            smashConfig.load();
            if (smashConfig.empty()) {
                smashConfig.reset();
                smashConfig.save(exception -> {
                    if (exception == null) {
                        getLogger().info("Saved default values to config.");
                    } else {
                        getLogger().severe("Config is empty (could not save default values), disabling.");
                        getServer().getPluginManager().disablePlugin(this);
                    }
                });
            } else {
                smashConfig.load();
                getLogger().info("Successfully loaded configuration.");
            }
        }

        if (getSmashConfig().noMaps()) {
            getLogger().warning("No maps found in config.");
        } else {
            for (String mapKey : getSmashConfig().getSection("maps").getKeys(false)) {
                Map map = MapLoader.loadMap(mapKey);
                if (map != null) {
                    Location[] locations = getSmashConfig().getLocs("maps." + mapKey + ".spawnLocations");
                    getLogger().info("Loaded " + map.name() + " with icon " + map.item() + " and " + locations.length + " spawn locations.");
                }
            }
        }

        // custom events
        getServer().getPluginManager().registerEvents(new GameStateChangeListener(), this);

        getServer().getPluginManager().registerEvents(new AsyncPlayerPreLoginListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new ChunkLoadListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new EntityPickupItemListener(), this);
        getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDropItemListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerGameModeChangeListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemHeldListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

        getServer().getCommandMap().register("smash", new ConfigCommand("config"));
        getServer().getCommandMap().register("smash", new SetupCommand("setup"));

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
        setups = new ConcurrentHashMap<>();
    }

    @Override
    public void onDisable() {
        PlayerManager.clearCharacters();
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public ConcurrentHashMap<Player, MapSetup> getSetups() {
        return setups;
    }

    public SmashConfig getSmashConfig() {
        return smashConfig;
    }
}

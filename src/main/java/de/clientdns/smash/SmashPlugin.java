package de.clientdns.smash;

import de.clientdns.smash.character.CharacterCache;
import de.clientdns.smash.commands.SetupCommand;
import de.clientdns.smash.config.Config;
import de.clientdns.smash.gamestate.GameStateManager;
import de.clientdns.smash.listeners.*;
import de.clientdns.smash.mapping.config.IConfig;
import de.clientdns.smash.mapping.config.MapConfig;
import de.clientdns.smash.setup.SetupManager;
import org.bukkit.GameRule;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Steerable;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public class SmashPlugin extends JavaPlugin {

    private static SmashPlugin plugin;
    private static Config config;

    private static File mapsFolder;
    private static CharacterCache characterCache;
    private static GameStateManager gameStateManager;
    private static SetupManager setupManager;

    public static CharacterCache getCharacterCache() {
        return characterCache;
    }

    public static Config getSmashConfig() {
        return config;
    }

    public static SmashPlugin getPlugin() {
        return plugin;
    }

    public static GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public static SetupManager getSetupManager() {
        return setupManager;
    }

    public static File getMapsFolder() {
        return mapsFolder;
    }

    @Override
    public void onLoad() {
        plugin = this;

        // Initiating config file
        loadConfig();

        // Initiating character cache
        characterCache = new CharacterCache();

        // Initiating setup manager
        setupManager = new SetupManager();
    }

    @Override
    public void onEnable() {
        // Initiating event listeners
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDropItemListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLoginListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

        // Initiating commands
        Objects.requireNonNull(getCommand("setup")).setExecutor(new SetupCommand());

        // Initiating game rules
        getServer().getWorlds().forEach(world -> {
            world.getEntities().stream().filter(Item.class::isInstance).forEach(Entity::remove);
            world.setTime(1000L);
            world.setThundering(false);
            world.setStorm(false);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
            world.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
            world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
            world.setGameRule(GameRule.DO_WARDEN_SPAWNING, false);
            world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
            world.setGameRule(GameRule.DO_MOB_LOOT, false);
            world.setGameRule(GameRule.MOB_GRIEFING, false);
            world.setGameRule(GameRule.NATURAL_REGENERATION, false);
            world.setGameRule(GameRule.DO_ENTITY_DROPS, false);
            world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, false);
            world.setGameRule(GameRule.LOG_ADMIN_COMMANDS, false);
            world.setGameRule(GameRule.KEEP_INVENTORY, false);
            world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
            world.setGameRule(GameRule.UNIVERSAL_ANGER, false);
            world.setGameRule(GameRule.MAX_ENTITY_CRAMMING, 8);
        });

        // Initiating game state manager
        gameStateManager = new GameStateManager();
        generateMapDirectory();
    }

    @Override
    public void onDisable() {
        // Clearing character cache
        characterCache.clear();
    }

    public void loadConfig() {
        config = new Config();

        // "Messages"
        config.set("config.messages.prefix", "§8[§6Smash§8]§r ", "The prefix of the plugin", "§8[§6Smash§8]§r ");
        config.save();
    }

    public void generateMapDirectory() {
        mapsFolder = new File("plugins/Smash/maps/");
        if (!mapsFolder.exists()) {
            mapsFolder.mkdir();
        }

        IConfig mapConfig = new MapConfig<>("plugins/Smash/maps/clemens");
        mapConfig.set("name",  "Clemens", "The name of the map", "Clemens");
    }
}

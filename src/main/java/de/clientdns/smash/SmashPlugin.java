package de.clientdns.smash;

import de.clientdns.smash.character.CharacterCache;
import de.clientdns.smash.commands.SetupCommand;
import de.clientdns.smash.config.Config;
import de.clientdns.smash.gamestate.GameStateManager;
import de.clientdns.smash.listeners.*;
import de.clientdns.smash.listeners.custom.GameStateChangeListener;
import de.clientdns.smash.map.setup.SetupManager;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SmashPlugin extends JavaPlugin {

    private static SmashPlugin plugin;
    private Config config;
    private CharacterCache characterCache;
    private GameStateManager gameStateManager;
    private SetupManager setupManager;

    public static SmashPlugin getPlugin() {
        return plugin;
    }

    public CharacterCache getCharacterCache() {
        return characterCache;
    }

    public Config getSmashConfig() {
        return config;
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public SetupManager getSetupManager() {
        return setupManager;
    }

    @Override
    public void onLoad() {
        plugin = this;

        // Initiating config file
        initConfig();

        // Initiating character cache
        characterCache = new CharacterCache();

        // Initiating setup manager
        setupManager = new SetupManager();

        // Initiating game state manager
        gameStateManager = new GameStateManager();
    }

    @Override
    public void onEnable() {
        // Initiating event listeners
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new EntityPickupItemListener(), this);
        getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
        getServer().getPluginManager().registerEvents(new GameStateChangeListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDropItemListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemHeldListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLoginListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

        // Initiating commands
        SetupCommand setupCommand = new SetupCommand();
        Objects.requireNonNull(getCommand("setup")).setExecutor(setupCommand);
        Objects.requireNonNull(getCommand("setup")).setTabCompleter(setupCommand);

        // Initiating game rules
        Bukkit.getWorlds().forEach(world -> {
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
    }

    @Override
    public void onDisable() {
        // Clearing character cache
        characterCache.clear();
    }

    private void initConfig() {
        config = new Config();

        config.set("config.min-players", 2);

        // Messages
        config.set("config.messages.prefix", "<gold>Smash</gold> <dark_gray>|</dark_gray> ", "Only in MiniMessage format!");
        config.set("config.messages.permission-required", "<red>Du hast keine Berechtigung, dies zu tun.</red>", "Only in MiniMessage format!");
        config.set("config.messages.player-required", "<red>Du musst ein Spieler sein, um dies zu tun.</red>", "Only in MiniMessage format!");
        config.set("config.messages.player-not-found", "<red>Der Spieler wurde nicht gefunden.</red>", "Only in MiniMessage format!");
        config.set("config.messages.player-not-online", "<red>Der Spieler ist nicht online.</red>", "Only in MiniMessage format!");

        if (!config.save()) {
            return;
        }
        getLogger().info("Config file loaded!");
    }
}

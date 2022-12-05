package de.clientdns.smash;

import de.clientdns.smash.commands.ConfigCommand;
import de.clientdns.smash.commands.SetupCommand;
import de.clientdns.smash.commands.UptimeCommand;
import de.clientdns.smash.config.Config;
import de.clientdns.smash.gamestate.GameStateManager;
import de.clientdns.smash.listeners.*;
import de.clientdns.smash.listeners.custom.CharacterChangeListener;
import de.clientdns.smash.listeners.custom.GameStateChangeListener;
import de.clientdns.smash.listeners.custom.SetupBeginListener;
import de.clientdns.smash.listeners.custom.SetupFinishListener;
import de.clientdns.smash.map.setup.SetupManager;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Instant;
import java.util.Objects;

public final class SmashPlugin extends JavaPlugin {

    private static SmashPlugin plugin;
    private Config config;
    private GameStateManager gameStateManager;
    private SetupManager setupManager;
    private Instant startTime;

    public static SmashPlugin plugin() {
        return plugin;
    }

    public Config configuration() {
        return this.config;
    }

    public GameStateManager gameStateManager() {
        return this.gameStateManager;
    }

    public SetupManager setupManager() {
        return this.setupManager;
    }

    public Instant startTime() {
        return this.startTime;
    }

    @Override
    public void onLoad() {
        plugin = this;
        this.startTime = Instant.now();
        this.config = new Config();
        this.gameStateManager = new GameStateManager();
        this.setupManager = new SetupManager();
    }

    @Override
    public void onEnable() {
        this.checkJava();
        this.initListeners();
        this.initCommands();
        this.setWorldProperties();
    }

    void initListeners() {
        this.getServer().getPluginManager().registerEvents(new AsyncPlayerPreLoginListener(), this);
        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        this.getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        this.getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        this.getServer().getPluginManager().registerEvents(new EntityPickupItemListener(), this);
        this.getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
        this.getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDropItemListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerGameModeChangeListener(), this);
        this.getServer().getPluginManager().registerEvents(new GameStateChangeListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerItemHeldListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

        // custom events
        this.getServer().getPluginManager().registerEvents(new CharacterChangeListener(), this);
        this.getServer().getPluginManager().registerEvents(new GameStateChangeListener(), this);
        this.getServer().getPluginManager().registerEvents(new SetupBeginListener(), this);
        this.getServer().getPluginManager().registerEvents(new SetupFinishListener(), this);
    }

    void initCommands() {
        ConfigCommand configCommand = new ConfigCommand();
        SetupCommand setupCommand = new SetupCommand();
        UptimeCommand uptimeCommand = new UptimeCommand();

        Objects.requireNonNull(this.getCommand("config")).setExecutor(configCommand);
        Objects.requireNonNull(this.getCommand("setup")).setExecutor(setupCommand);
        Objects.requireNonNull(this.getCommand("uptime")).setExecutor(uptimeCommand);

        // Tab completer
        Objects.requireNonNull(this.getCommand("config")).setTabCompleter(configCommand);
        Objects.requireNonNull(this.getCommand("setup")).setTabCompleter(setupCommand);
        Objects.requireNonNull(this.getCommand("uptime")).setTabCompleter(uptimeCommand);
    }

    void setWorldProperties() {
        for (World world : Bukkit.getWorlds()) {
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
        }
    }

    void checkJava() {
        if (Runtime.version().feature() < 17)
            this.getLogger().severe("You are using an outdated java version (" + Runtime.version().feature() + ")! Please update to Java 17!");
    }
}

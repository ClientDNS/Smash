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
import org.bukkit.World;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;
import java.util.Objects;

public final class SmashPlugin extends JavaPlugin {
    private static SmashPlugin plugin;
    private Config config;
    private CharacterCache characterCache;
    private GameStateManager gameStateManager;
    private SetupManager setupManager;

    public SmashPlugin() {
        super();
    }

    private SmashPlugin(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file); // This constructor is needed for MockBukkit to work properly
    }

    public static SmashPlugin getPlugin() {
        return plugin;
    }

    public CharacterCache getCharacterCache() {
        return this.characterCache;
    }

    public Config getSmashConfig() {
        return this.config;
    }

    public GameStateManager getGameStateManager() {
        return this.gameStateManager;
    }

    public SetupManager getSetupManager() {
        return this.setupManager;
    }

    @Override
    public void onLoad() {
        plugin = this;
        this.characterCache = new CharacterCache();
        this.setupManager = new SetupManager();
        this.gameStateManager = new GameStateManager();
        this.initConfig();
    }

    @Override
    public void onEnable() {
        if (Runtime.version().feature() < 17) {
            this.getLogger().warning("You are using an unsupported Java version. Please use Java 17 or higher."); // TODO: Add link to download page
        }
        this.getServer().getPluginManager().registerEvents(new AsyncPlayerPreLoginListener(), this);
        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        this.getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        this.getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        this.getServer().getPluginManager().registerEvents(new EntityPickupItemListener(), this);
        this.getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
        this.getServer().getPluginManager().registerEvents(new GameStateChangeListener(), this); // custom event
        this.getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerDropItemListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerItemHeldListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

        SetupCommand setupCommand = new SetupCommand();
        Objects.requireNonNull(getCommand("setup")).setExecutor(setupCommand);
        Objects.requireNonNull(getCommand("setup")).setTabCompleter(setupCommand);

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

    @Override
    public void onDisable() {
        this.characterCache.clear();
    }

    private void initConfig() {
        if (isBukkit()) {
            this.config = new Config();
            this.config.set("min-players", 2);
            this.config.set("messages.prefix", "<gold>Smash</gold> <dark_gray>|</dark_gray> ", "Only in MiniMessage format!");
            this.config.set("messages.permission-required", "<red>Du hast keine Berechtigung, dies zu tun.</red>", "Only in MiniMessage format!");
            this.config.set("messages.player-required", "<red>Du musst ein Spieler sein, um dies zu tun.</red>", "Only in MiniMessage format!");
            this.config.set("messages.player-not-found", "<red>Der Spieler wurde nicht gefunden.</red>", "Only in MiniMessage format!");
            if (this.config.save()) {
                getLogger().info("Config file loaded!");
            }
        }
    }

    private boolean isBukkit() {
        try {
            // However, this is not a good way to detect MockBukkit, but it works for now
            SmashPlugin.getPlugin().getClassLoader().loadClass("org.bukkit.command.SimpleCommandMap.server");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}

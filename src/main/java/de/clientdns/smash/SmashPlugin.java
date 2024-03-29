package de.clientdns.smash;

import de.clientdns.smash.commands.ConfigCommand;
import de.clientdns.smash.commands.SetupCommand;
import de.clientdns.smash.config.SmashConfig;
import de.clientdns.smash.gamestate.GameStateManager;
import de.clientdns.smash.listeners.*;
import de.clientdns.smash.listeners.custom.GameStateChangeListener;
import de.clientdns.smash.map.setup.MapSetup;
import de.clientdns.smash.timer.GameTimer;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class SmashPlugin extends JavaPlugin {

    private static SmashPlugin plugin;
    private GameStateManager gameStateManager;
    private Instant then;
    private HashMap<Player, MapSetup> setups;
    private SmashConfig smashConfig;
    private GameTimer gameTimer;

    public static SmashPlugin getPlugin() {
        return plugin;
    }

    @Override
    public void onLoad() {
        if (plugin == null) {
            plugin = this;
            then = Instant.now();
        } else {
            getLogger().severe("Could not assign new/another plugin instance, deactivating plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        double classVersion = NumberUtils.toDouble(System.getProperty("java.class.version"));
        if (classVersion < 61.0) {
            getLogger().warning("You are using a unsupported Java Version! (class: " + classVersion + ")");
            getLogger().warning("Please update to at least Java 17 (class: 61).");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        gameStateManager = new GameStateManager();
        setups = new HashMap<>();
    }

    @Override
    public void onEnable() {
        smashConfig = new SmashConfig("smash.yml");
        if (!smashConfig.exists()) {
            getLogger().severe("Could not check configuration file, deactivating plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        } else {
            smashConfig.load();
            if (smashConfig.empty()) {
                getLogger().warning("Configuration file is empty, resetting to default values.");
                smashConfig.reset();
                smashConfig.save(exception -> {
                    if (exception == null) {
                        getLogger().info("Default values saved in configuration.");
                    } else {
                        getLogger().severe("Error while saving default values to config." + exception);
                    }
                });
            }
        }

        /*if (getSmashConfig().getInt("min-players") < 2) {
            getLogger().warning("Minimum players cannot be lower than 2, deactivating plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }*/

        if (getSmashConfig().noMaps()) {
            getLogger().warning("No maps found in configuration.");
            getLogger().warning("Setup with '/setup start'.");
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
        listeners.add(new PlayerQuitListener());

        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }

        List<Command> commands = new ArrayList<>();
        commands.add(new ConfigCommand("config"));
        commands.add(new SetupCommand("setup"));

        for (Command command : commands) {
            getServer().getCommandMap().register("smash", command);
        }

        for (World world : getServer().getWorlds()) {
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

        gameTimer = new GameTimer();

        getLogger().info("Took " + Duration.between(then, Instant.now()).toMillis() + " ms to start.");
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public HashMap<Player, MapSetup> getSetups() {
        return setups;
    }

    public SmashConfig getSmashConfig() {
        return smashConfig;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }
}

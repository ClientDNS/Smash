package de.clientdns.smash;

import de.clientdns.smash.commands.ConfigCommand;
import de.clientdns.smash.commands.SetupCommand;
import de.clientdns.smash.config.PluginConfig;
import de.clientdns.smash.gamestate.GameStateManager;
import de.clientdns.smash.listeners.*;
import de.clientdns.smash.listeners.custom.GameStateChangeListener;
import de.clientdns.smash.map.setup.MapSetup;
import de.clientdns.smash.player.PlayerManager;
import de.clientdns.smash.timer.GameTimer;
import de.clientdns.smash.voting.VoteManager;
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
    private PlayerManager playerManager;
    private HashMap<Player, MapSetup> setups;
    private PluginConfig pluginConfig;
    private VoteManager voteManager;
    private GameTimer gameTimer;

    public static SmashPlugin getPlugin() {
        return plugin;
    }

    @Override
    public void onLoad() {
        if (plugin == null) {
            getLogger().info("Assign plugin instance.");
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
        playerManager = new PlayerManager();
        setups = new HashMap<>();
    }

    @Override
    public void onEnable() {
        pluginConfig = new PluginConfig("smash.yml");
        getLogger().info("Initializing config.");
        if (!pluginConfig.exists()) {
            getLogger().severe("Could not find configuration file, deactivating plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        } else {
            getLogger().info("Loading config.");
            pluginConfig.load();
            if (pluginConfig.empty()) {
                getLogger().warning("Configuration file is empty, resetting to default values.");
                pluginConfig.reset();
                pluginConfig.save(exception -> {
                    if (exception == null) {
                        getLogger().info("Default values saved in configuration.");
                    } else {
                        getLogger().severe("Error while saving default values to config." + exception);
                    }
                });
            }
        }

        // DEACTIVATE: DEBUG COMPLICATIONS
        /*if (getSmashConfig().getInt("min-players") < 2) {
            getLogger().warning("Minimum players cannot be lower than 2, deactivating plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }*/

        if (getSmashConfig().noMaps()) {
            getLogger().warning("No maps found in configuration. Setup with '/setup start'.");
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

        voteManager = new VoteManager();
        gameTimer = new GameTimer();

        getLogger().info("Took " + Duration.between(then, Instant.now()).toMillis() + " ms to start.");
    }

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public HashMap<Player, MapSetup> getSetups() {
        return setups;
    }

    public PluginConfig getSmashConfig() {
        if (pluginConfig == null)
            plugin.getLogger().info("Config is not initialized.");
        return pluginConfig;
    }

    public VoteManager getVoteManager() {
        return voteManager;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }
}

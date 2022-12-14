package de.clientdns.smash;

import de.clientdns.smash.commands.SetupCommand;
import de.clientdns.smash.config.Config;
import de.clientdns.smash.listeners.*;
import de.clientdns.smash.listeners.custom.CharacterChangeListener;
import de.clientdns.smash.listeners.custom.GameStateChangeListener;
import de.clientdns.smash.listeners.custom.SetupBeginListener;
import de.clientdns.smash.listeners.custom.SetupFinishListener;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class SmashPlugin extends JavaPlugin {

    private static SmashPlugin plugin;
    private Config config;

    public static SmashPlugin plugin() {
        return plugin;
    }

    public Config configuration() {
        return config;
    }

    @Override
    public void onLoad() {
        plugin = this;
        config = new Config("config.yml");
    }

    @Override
    public void onEnable() {
        checkJava();
        initListeners();
        initCommands();
        setWorldProperties();
    }

    @Override
    public void onDisable() {
        getLogger().info("Cancelling running/scheduled tasks...");
        Bukkit.getScheduler().cancelTasks(this);
    }

    void initListeners() {
        getServer().getPluginManager().registerEvents(new AsyncPlayerPreLoginListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new EntityPickupItemListener(), this);
        getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDropItemListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerGameModeChangeListener(), this);
        getServer().getPluginManager().registerEvents(new GameStateChangeListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemHeldListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

        // custom events
        getServer().getPluginManager().registerEvents(new CharacterChangeListener(), this);
        getServer().getPluginManager().registerEvents(new GameStateChangeListener(), this);
        getServer().getPluginManager().registerEvents(new SetupBeginListener(), this);
        getServer().getPluginManager().registerEvents(new SetupFinishListener(), this);
    }

    void initCommands() {

        CommandMap map = Bukkit.getCommandMap();

        SetupCommand setupCommand = new SetupCommand("setup", "", "/", List.of());
        map.register("smash", setupCommand);
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
            getLogger().severe("You are using an outdated java version (" + Runtime.version().feature() + ")! Please update to Java 17!");
    }
}

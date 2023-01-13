package de.clientdns.smash;

import de.clientdns.smash.api.config.SmashConfig;
import de.clientdns.smash.api.player.PlayerManager;
import de.clientdns.smash.commands.SetupCommand;
import de.clientdns.smash.listeners.*;
import de.clientdns.smash.listeners.custom.CharacterChangeListener;
import de.clientdns.smash.listeners.custom.GameStateChangeListener;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmashPlugin extends JavaPlugin {

    private static SmashPlugin plugin;
    private SmashConfig smashConfig;

    public static SmashPlugin getPlugin() {
        return plugin;
    }

    @Override
    public void onLoad() {
        plugin = this;
        if (Runtime.version().feature() < 17) {
            getLogger().severe("You are using an outdated java version (" + Runtime.version().feature() + ")! Please update to Java 17!");
            return;
        }
        smashConfig = new SmashConfig("smash.yml");
    }

    @Override
    public void onEnable() {
        initListeners();
        initCommands();
        setWorldProperties();
    }

    @Override
    public void onDisable() {
        if (PlayerManager.reset()) {
            getLogger().info("Successfully cleared player character cache.");
        }
    }

    void initListeners() {
        // custom events
        getServer().getPluginManager().registerEvents(new CharacterChangeListener(), this);
        getServer().getPluginManager().registerEvents(new GameStateChangeListener(), this);

        getServer().getPluginManager().registerEvents(new AsyncPlayerPreLoginListener(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new ChunkLoadListener(), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new EntityPickupItemListener(), this);
        getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDropItemListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerGameModeChangeListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerItemHeldListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
    }

    void initCommands() {
        CommandMap map = Bukkit.getCommandMap();
        map.register("smash", new SetupCommand("setup"));
    }

    void setWorldProperties() {
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
            world.setGameRule(GameRule.MAX_ENTITY_CRAMMING, 8);
        }
    }

    public SmashConfig getSmashConfig() {
        return smashConfig;
    }
}

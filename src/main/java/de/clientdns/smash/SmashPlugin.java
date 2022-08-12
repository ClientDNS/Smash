package de.clientdns.smash;

import de.clientdns.smash.character.CharacterCache;
import de.clientdns.smash.config.SmashConfig;
import de.clientdns.smash.events.*;
import lombok.Getter;
import org.bukkit.GameRule;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;

public class SmashPlugin extends JavaPlugin {

    @Getter
    private static SmashPlugin plugin;
    @Getter
    private static SmashConfig smashConfig;
    @Getter
    private static CharacterCache characterCache;
    private int bukkitTask;

    @Override
    public void onEnable() {

        plugin = this;

        // Initiating config file
        smashConfig = new SmashConfig();

        // Initiating character cache
        characterCache = new CharacterCache();

        // Initiating event listeners
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerGameModeChangeListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);

        // Initiating game rules
        getServer().getWorlds().forEach(world -> {
            world.setTime(1000);
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
            world.setGameRule(GameRule.REDUCED_DEBUG_INFO, false);
            world.setGameRule(GameRule.DO_ENTITY_DROPS, false);
            world.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, false);
            world.setGameRule(GameRule.LOG_ADMIN_COMMANDS, false);
            world.setGameRule(GameRule.KEEP_INVENTORY, false);
            world.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
            world.setGameRule(GameRule.SPECTATORS_GENERATE_CHUNKS, true);
            world.setGameRule(GameRule.UNIVERSAL_ANGER, false);
            world.setGameRule(GameRule.MAX_ENTITY_CRAMMING, 8);
        });

        // Starting scheduler tasks
        bukkitTask = getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> getServer().getWorlds().forEach(world -> world.getEntities().stream().filter(Item.class::isInstance).forEach(Entity::remove)), 0, 10);
    }

    @Override
    public void onDisable() {
        // Stopping scheduler tasks
        if (getServer().getScheduler().isCurrentlyRunning(bukkitTask)) {
            getServer().getScheduler().cancelTask(bukkitTask);
        }

        // Clearing character cache
        characterCache.clear();
    }
}

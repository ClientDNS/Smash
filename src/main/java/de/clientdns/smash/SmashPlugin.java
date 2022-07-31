package de.clientdns.smash;

import de.clientdns.smash.events.EntityDamageListener;
import de.clientdns.smash.events.FoodLevelChangeListener;
import de.clientdns.smash.events.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;
import java.time.Instant;

public class SmashPlugin extends JavaPlugin {

    private Instant instant;

    @Override
    public void onLoad() {
        instant = Instant.now();
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
        getServer().getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getLogger().info("SmashPlugin enabled in " + Duration.between(instant, Instant.now()).toMillis() + "ms");
    }
}

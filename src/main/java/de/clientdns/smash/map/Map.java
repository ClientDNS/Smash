package de.clientdns.smash.map;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.SmashConfig;
import org.bukkit.Location;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Map implements Serializable {

    @Serial
    private static final long serialVersionUID = 6521080780746144772L;
    private final String name;
    private final String builder;
    private final Location[] spawnLocations;

    @Contract(pure = true)
    public Map(String name, String builder, Location @NotNull [] spawnLocations) {
        this.name = name;
        this.builder = builder;
        this.spawnLocations = spawnLocations;
    }

    public String getName() {
        return name;
    }

    public String getBuilder() {
        return builder;
    }

    @Override
    public String toString() {
        return "Map{" + "name='" + name + '\'' + ", builder='" + builder + '\'' + ", spawnLocations=" + Arrays.toString(spawnLocations) + '}';
    }

    public void save(@NotNull Consumer<Boolean> success) {
        SmashConfig config = SmashPlugin.getPlugin().getSmashConfig();
        List<String> maps = config.getStringList("maps");
        maps.add(this.toString());
        config.set("maps", maps);
        success.accept(config.save());
    }
}

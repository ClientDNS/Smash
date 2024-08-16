package de.clientdns.smash.item;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.security.SecureRandom;
import java.util.List;

public class ItemSpreading {

    private final Location location;
    private final List<ItemStack> items;

    public ItemSpreading(Location location, List<ItemStack> items) {
        this.location = location;
        this.items = items;

        Bukkit.getScheduler().runTaskTimer(SmashPlugin.getPlugin(), () -> {
            Location spreadLoc = spread(5);
            spreadLoc.add(0, 5, 0);
        }, 0, 20 * 45); // hint: 20 ticks equals 1 second; times 45 equals 45 seconds
    }

    public Location spread(int radius) {
        World world = location.getWorld();

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                Location spreadLocation = location.clone().add(x, 1, z);
                if (spreadLocation.getBlock().getType().equals(Material.AIR)) {
                    break;
                }

                SecureRandom random = new SecureRandom();
                int randomIndex = random.nextInt(items.size());

                Item item = world.dropItem(spreadLocation, items.get(randomIndex));
                return item.getLocation();
            }
        }
        return null;
    }
}

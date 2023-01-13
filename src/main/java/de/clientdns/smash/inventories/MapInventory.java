package de.clientdns.smash.inventories;

import de.clientdns.smash.api.config.MiniMsg;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import static net.kyori.adventure.text.format.NamedTextColor.GOLD;

public class MapInventory {

    public static void open(@NotNull Player player) {
        player.openInventory(MapInventory.create());
    }

    private static @NotNull Inventory create() {
        return Bukkit.createInventory(null, 27, MiniMsg.plain("Maps", GOLD));
    }
}

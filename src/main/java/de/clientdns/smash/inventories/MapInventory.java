package de.clientdns.smash.inventories;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public class MapInventory {

    public static void open(@NotNull Player player) {
        player.openInventory(MapInventory.create());
    }

    private static @NotNull Inventory create() {
        return Bukkit.createInventory(null, 27, text("Maps", GOLD));
    }
}

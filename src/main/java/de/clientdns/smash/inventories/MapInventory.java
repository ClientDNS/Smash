package de.clientdns.smash.inventories;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class MapInventory {

    public static void open(@NotNull Player player) {
        player.openInventory(create());
    }

    private static @NotNull Inventory create() {
        return Bukkit.createInventory(null, 9 * 3, MiniMessage.miniMessage().deserialize("<gold>Maps</gold>"));
    }
}

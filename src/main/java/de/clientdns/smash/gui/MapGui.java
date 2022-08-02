package de.clientdns.smash.gui;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class MapGui {

    public static void open(@NotNull Player player) {
        player.openInventory(MapGui.create());
    }

    private static @NotNull Inventory create() {
        return Bukkit.createInventory(null, 54, MiniMessage.miniMessage().deserialize("<gold>Maps</gold>"));
    }
}

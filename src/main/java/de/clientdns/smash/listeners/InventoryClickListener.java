package de.clientdns.smash.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class InventoryClickListener implements Listener {

    @EventHandler
    void on(@NotNull InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null) {
            event.setCancelled(true);
            return;
        }
        if (event.getCurrentItem().getItemMeta() == null) {
            event.setCancelled(true);
            return;
        }
        Component itemName = event.getCurrentItem().getItemMeta().displayName();
        if (itemName == null) {
            event.setCancelled(true);
            return;
        }
        if (!event.getClick().equals(ClickType.LEFT)) {
            return;
        }
        event.setCancelled(true);
    }
}

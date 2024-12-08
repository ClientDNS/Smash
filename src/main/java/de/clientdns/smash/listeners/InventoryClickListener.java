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
    void on(@NotNull InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getCurrentItem() == null) {
            e.setCancelled(true);
            return;
        }
        if (e.getCurrentItem().getItemMeta() == null) {
            e.setCancelled(true);
            return;
        }
        Component itemName = e.getCurrentItem().getItemMeta().displayName();
        if (itemName == null) {
            e.setCancelled(true);
            return;
        }
        if (!e.getClick().equals(ClickType.LEFT)) {
            return;
        }
        e.setCancelled(true);
    }
}

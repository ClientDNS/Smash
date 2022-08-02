package de.clientdns.smash.events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;

public class InventoryClickListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull InventoryClickEvent event) {
        if (event.getClick() == ClickType.LEFT) {
            Player player = (Player) event.getWhoClicked();
            InventoryView view = event.getView();

            if (event.getCurrentItem() == null) {
                return;
            }

            player.sendMessage(Component.text("Not implemented yet!", NamedTextColor.RED));
            event.setCancelled(true);
        } else {
            event.setCancelled(true);
        }
    }
}

package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.character.CharacterCache;
import de.clientdns.smash.character.enums.Character;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class InventoryClickListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull InventoryClickEvent event) {
        if (event.getClickedInventory() == null) {
            event.setCancelled(true);
            return;
        }

        if (event.getCurrentItem() == null) {
            event.setCancelled(true);
            return;
        }
        ItemStack item = event.getCurrentItem();

        if (item.getItemMeta() == null) {
            event.setCancelled(true);
            return;
        }
        Component displayName = item.getItemMeta().displayName();

        if (event.getClick() != ClickType.LEFT) {
            event.setCancelled(true);
            return;
        }

        Player player = (Player) event.getWhoClicked();
        if (SmashPlugin.getPlugin().getGameStateManager().isLobbyState()) {
            CharacterCache cache = SmashPlugin.getPlugin().getCharacterCache();
            if (Character.MARIO.getName().equals(displayName)) {
                cache.put(player, Character.MARIO);
            } else if (Character.DONKEY_KONG.getName().equals(displayName)) {
                cache.put(player, Character.DONKEY_KONG);
            } else if (Character.FLASH.getName().equals(displayName)) {
                cache.put(player, Character.FLASH);
            } else if (Character.PIKACHU.getName().equals(displayName)) {
                cache.put(player, Character.PIKACHU);
            } else if (Character.SUPERMAN.getName().equals(displayName)) {
                cache.put(player, Character.SUPERMAN);
            } else if (Character.LINK.getName().equals(displayName)) {
                cache.put(player, Character.LINK);
            }
            event.setCancelled(true);
            return;
        } else if (SmashPlugin.getPlugin().getGameStateManager().isIngameState()) {
            event.setCancelled(true);
            return;
        } else if (SmashPlugin.getPlugin().getGameStateManager().isEndState()) {
            event.setCancelled(false);
            return;
        }
        event.setCancelled(true);
    }
}

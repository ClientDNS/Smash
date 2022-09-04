package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.character.PlayerManager;
import de.clientdns.smash.character.enums.Character;
import de.clientdns.smash.inventories.CharacterInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class InventoryClickListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull InventoryClickEvent event) {
        if (event.getClick() != ClickType.LEFT) {
            event.setCancelled(true);
        }

        if (event.getCurrentItem() == null) {
            return;
        }
        event.setCancelled(true);
        ItemStack item = event.getCurrentItem();

        Player player = (Player) event.getWhoClicked();
        InventoryView view = event.getView();

        PlayerManager playerManager = new PlayerManager(player);

        if (SmashPlugin.getPlugin().getGameStateManager().isLobbyState()) {
            if (item.displayName().equals(CharacterInventory.MARIO.displayName())) {
                player.getInventory().close();
                playerManager.setCharacter(Character.MARIO);
            } else if (item.displayName().equals(CharacterInventory.DONKEY_KONG.displayName())) {
                player.getInventory().close();
                playerManager.setCharacter(Character.DONKEY_KONG);
            } else if (item.displayName().equals(CharacterInventory.FLASH.displayName())) {
                player.getInventory().close();
                playerManager.setCharacter(Character.FLASH);
            } else if (item.displayName().equals(CharacterInventory.PIKACHU.displayName())) {
                player.getInventory().close();
                playerManager.setCharacter(Character.PIKACHU);
            } else if (item.displayName().equals(CharacterInventory.SUPERMAN.displayName())) {
                player.getInventory().close();
                playerManager.setCharacter(Character.SUPERMAN);
            } else if (item.displayName().equals(CharacterInventory.LINK.displayName())) {
                player.getInventory().close();
                playerManager.setCharacter(Character.LINK);
            }
        } else {
            event.setCancelled(true);
        }
    }
}

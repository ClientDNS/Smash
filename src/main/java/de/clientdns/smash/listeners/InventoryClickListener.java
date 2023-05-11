package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.character.Character;
import de.clientdns.smash.gamestate.GameState;
import de.clientdns.smash.player.PlayerManager;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
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
        if (event.getCurrentItem().getItemMeta() == null) {
            event.setCancelled(true);
            return;
        }
        Component displayName = event.getCurrentItem().getItemMeta().displayName();
        if (!event.getClick().equals(ClickType.LEFT)) {
            event.setCancelled(true);
            return;
        }

        if (SmashPlugin.getPlugin().getGameStateManager().getCurrentState().equals(GameState.LOBBY)) {
            Player player = (Player) event.getWhoClicked();
            if (Character.MARIO.data().name().equals(displayName)) {
                PlayerManager.set(player, Character.MARIO);
            } else if (Character.DONKEY_KONG.data().name().equals(displayName)) {
                PlayerManager.set(player, Character.DONKEY_KONG);
            } else if (Character.FLASH.data().name().equals(displayName)) {
                PlayerManager.set(player, Character.FLASH);
            } else if (Character.PIKACHU.data().name().equals(displayName)) {
                PlayerManager.set(player, Character.PIKACHU);
            } else if (Character.SUPERMAN.data().name().equals(displayName)) {
                PlayerManager.set(player, Character.SUPERMAN);
            } else if (Character.LINK.data().name().equals(displayName)) {
                PlayerManager.set(player, Character.LINK);
            }
        }
        event.setCancelled(true);
    }
}

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

        if (!SmashPlugin.getPlugin().getGameStateManager().isLobbyState()) {
            event.setCancelled(true);
            return;
        }

        Player player = (Player) event.getWhoClicked();
        CharacterCache cache = SmashPlugin.getPlugin().getCharacterCache();

        if (Character.MARIO.getName().equals(displayName)) {
            cache.putOrReplace(player, Character.MARIO);
        } else if (Character.DONKEY_KONG.getName().equals(displayName)) {
            cache.putOrReplace(player, Character.DONKEY_KONG);
        } else if (Character.FLASH.getName().equals(displayName)) {
            cache.putOrReplace(player, Character.FLASH);
        } else if (Character.PIKACHU.getName().equals(displayName)) {
            cache.putOrReplace(player, Character.PIKACHU);
        } else if (Character.SUPERMAN.getName().equals(displayName)) {
            cache.putOrReplace(player, Character.SUPERMAN);
        } else if (Character.LINK.getName().equals(displayName)) {
            cache.putOrReplace(player, Character.LINK);
        }
        event.setCancelled(true);
    }
}

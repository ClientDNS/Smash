package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.character.CharacterCache;
import de.clientdns.smash.character.enums.Character;
import de.clientdns.smash.inventories.CharacterInventory;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
        ItemStack item = event.getCurrentItem();

        Player player = (Player) event.getWhoClicked();
        InventoryView view = event.getView();

        CharacterCache cache = SmashPlugin.getCharacterCache();
        Character currentCharacter;

        if (item.displayName().equals(CharacterInventory.MARIO.displayName())) {
            cache.replace(player, Character.MARIO);
            currentCharacter = cache.get(player).orElse(Character.MARIO);
            player.getInventory().close();
            player.sendMessage(Component.text("Du hast den Charakter ", NamedTextColor.GRAY).append(currentCharacter.getName()).append(Component.text(" ausgewählt.", NamedTextColor.GRAY)));
        } else if (item.displayName().equals(CharacterInventory.DONKEY_KONG.displayName())) {
            cache.replace(player, Character.DONKEY_KONG);
            currentCharacter = cache.get(player).orElse(Character.DONKEY_KONG);
            player.getInventory().close();
            player.sendMessage(Component.text("Du hast den Charakter ", NamedTextColor.GRAY).append(currentCharacter.getName()).append(Component.text(" ausgewählt.", NamedTextColor.GRAY)));
        } else if (item.displayName().equals(CharacterInventory.FLASH.displayName())) {
            cache.replace(player, Character.FLASH);
            currentCharacter = cache.get(player).orElse(Character.FLASH);
            player.getInventory().close();
            player.sendMessage(Component.text("Du hast den Charakter ", NamedTextColor.GRAY).append(currentCharacter.getName()).append(Component.text(" ausgewählt.", NamedTextColor.GRAY)));
        } else if (item.displayName().equals(CharacterInventory.PIKACHU.displayName())) {
            cache.replace(player, Character.PIKACHU);
            currentCharacter = cache.get(player).orElse(Character.PIKACHU);
            player.getInventory().close();
            player.sendMessage(Component.text("Du hast den Charakter ", NamedTextColor.GRAY).append(currentCharacter.getName()).append(Component.text(" ausgewählt.", NamedTextColor.GRAY)));
        } else if (item.displayName().equals(CharacterInventory.SUPERMAN.displayName())) {
            cache.replace(player, Character.SUPERMAN);
            currentCharacter = cache.get(player).orElse(Character.SUPERMAN);
            player.getInventory().close();
            player.sendMessage(Component.text("Du hast den Charakter ", NamedTextColor.GRAY).append(currentCharacter.getName()).append(Component.text(" ausgewählt.", NamedTextColor.GRAY)));
        } else if (item.displayName().equals(CharacterInventory.LINK.displayName())) {
            cache.replace(player, Character.LINK);
            currentCharacter = cache.get(player).orElse(Character.LINK);
            player.getInventory().close();
            player.sendMessage(Component.text("Du hast den Charakter ", NamedTextColor.GRAY).append(currentCharacter.getName()).append(Component.text(" ausgewählt.", NamedTextColor.GRAY)));
        }
    }
}
package de.clientdns.smash.events;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.character.Character;
import de.clientdns.smash.character.CharacterCache;
import de.clientdns.smash.gui.CharacterGui;
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
        if (event.getClick() == ClickType.LEFT) {
            Player player = (Player) event.getWhoClicked();
            InventoryView view = event.getView();

            if (event.getCurrentItem() == null) {
                return;
            }

            ItemStack item = event.getCurrentItem();
            CharacterCache cache = SmashPlugin.getCharacterCache();
            Character currentCharacter;

            if (item.displayName().equals(CharacterGui.MARIO.displayName())) {
                cache.remove(player);
                cache.add(player, Character.MARIO);
                currentCharacter = cache.get(player);
                player.getInventory().close();
                player.sendMessage(Component.text("Du hast den Charakter ", NamedTextColor.GRAY).append(currentCharacter.getName()).append(Component.text(" ausgewählt.", NamedTextColor.GRAY)));
            } else if (item.displayName().equals(CharacterGui.DONKEY_KONG.displayName())) {
                cache.remove(player);
                cache.add(player, Character.DONKEY_KONG);
                currentCharacter = cache.get(player);
                player.getInventory().close();
                player.sendMessage(Component.text("Du hast den Charakter ", NamedTextColor.GRAY).append(currentCharacter.getName()).append(Component.text(" ausgewählt.", NamedTextColor.GRAY)));
            } else if (item.displayName().equals(CharacterGui.FLASH.displayName())) {
                cache.remove(player);
                cache.add(player, Character.FLASH);
                currentCharacter = cache.get(player);
                player.getInventory().close();
                player.sendMessage(Component.text("Du hast den Charakter ", NamedTextColor.GRAY).append(currentCharacter.getName()).append(Component.text(" ausgewählt.", NamedTextColor.GRAY)));
            } else if (item.displayName().equals(CharacterGui.PIKACHU.displayName())) {
                cache.remove(player);
                cache.add(player, Character.PIKACHU);
                currentCharacter = cache.get(player);
                player.getInventory().close();
                player.sendMessage(Component.text("Du hast den Charakter ", NamedTextColor.GRAY).append(currentCharacter.getName()).append(Component.text(" ausgewählt.", NamedTextColor.GRAY)));
            } else if (item.displayName().equals(CharacterGui.SUPERMAN.displayName())) {
                cache.remove(player);
                cache.add(player, Character.SUPERMAN);
                currentCharacter = cache.get(player);
                player.getInventory().close();
                player.sendMessage(Component.text("Du hast den Charakter ", NamedTextColor.GRAY).append(currentCharacter.getName()).append(Component.text(" ausgewählt.", NamedTextColor.GRAY)));
            } else if (item.displayName().equals(CharacterGui.LINK.displayName())) {
                cache.remove(player);
                cache.add(player, Character.LINK);
                currentCharacter = cache.get(player);
                player.getInventory().close();
                player.sendMessage(Component.text("Du hast den Charakter ", NamedTextColor.GRAY).append(currentCharacter.getName()).append(Component.text(" ausgewählt.", NamedTextColor.GRAY)));
            }
        } else {
            event.setCancelled(true);
        }
    }
}

package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.character.Character;
import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.gamestate.GameState;
import de.clientdns.smash.player.PlayerManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
            event.setCancelled(true);
            return;
        }

        if (SmashPlugin.getPlugin().getGameStateManager().is(GameState.LOBBY)) {
            PlayerManager playerManager = SmashPlugin.getPlugin().getPlayerManager();
            if (itemName.equals(Character.MARIO.data().name())) {
                player.sendMessage(MiniMsg.plain("Detected name ").append(itemName));
                playerManager.set(player, Character.MARIO);
            } else if (itemName.equals(Character.DONKEY_KONG.data().name())) {
                player.sendMessage(MiniMsg.plain("Detected name ").append(itemName));
                playerManager.set(player, Character.DONKEY_KONG);
            } else if (itemName.equals(Character.FLASH.data().name())) {
                player.sendMessage(MiniMsg.plain("Detected name ").append(itemName));
                playerManager.set(player, Character.FLASH);
            } else if (itemName.equals(Character.PIKACHU.data().name())) {
                player.sendMessage(MiniMsg.plain("Detected name ").append(itemName));
                playerManager.set(player, Character.PIKACHU);
            } else if (itemName.equals(Character.SUPERMAN.data().name())) {
                player.sendMessage(MiniMsg.plain("Detected name ").append(itemName));
                playerManager.set(player, Character.SUPERMAN);
            } else if (itemName.equals(Character.LINK.data().name())) {
                player.sendMessage(MiniMsg.plain("Detected name ").append(itemName));
                playerManager.set(player, Character.LINK);
            } else {
                player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("This character does not exist.", NamedTextColor.GRAY)));
                return;
            }
            event.setCancelled(true);
            return;
        }
        event.setCancelled(true);
    }
}

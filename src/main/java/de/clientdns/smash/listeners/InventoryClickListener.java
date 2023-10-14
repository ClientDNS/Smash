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
        if (event.getCurrentItem() == null) {
            event.setCancelled(true);
            return;
        }
        Component displayName = event.getCurrentItem().getItemMeta().displayName();
        if (displayName == null) {
            event.setCancelled(true);
            return;
        }
        if (!event.getClick().equals(ClickType.LEFT)) {
            event.setCancelled(true);
            return;
        }

        if (SmashPlugin.getPlugin().getGameStateManager().is(GameState.LOBBY)) {
            Player player = (Player) event.getWhoClicked();
            player.sendMessage(displayName);
            if (displayName.equals(Character.MARIO.data().name())) {
                PlayerManager.set(player, Character.MARIO);
            } else if (displayName.equals(Character.DONKEY_KONG.data().name())) {
                PlayerManager.set(player, Character.DONKEY_KONG);
            } else if (displayName.equals(Character.FLASH.data().name())) {
                PlayerManager.set(player, Character.FLASH);
            } else if (displayName.equals(Character.PIKACHU.data().name())) {
                PlayerManager.set(player, Character.PIKACHU);
            } else if (displayName.equals(Character.SUPERMAN.data().name())) {
                PlayerManager.set(player, Character.SUPERMAN);
            } else if (displayName.equals(Character.LINK.data().name())) {
                PlayerManager.set(player, Character.LINK);
            } else {
                player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("This item does nothing.", NamedTextColor.GRAY)));
            }
        }
        event.setCancelled(true);
    }
}

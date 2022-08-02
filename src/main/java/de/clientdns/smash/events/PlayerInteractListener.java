package de.clientdns.smash.events;

import de.clientdns.smash.gui.CharacterGui;
import de.clientdns.smash.gui.MapGui;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerInteractListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            switch (event.getMaterial()) {
                case CHEST -> {
                    event.setCancelled(true);
                    CharacterGui.open(player);
                }
                case MAP -> {
                    event.setCancelled(true);
                    MapGui.open(player);
                }
                case SLIME_BALL -> {
                    event.setCancelled(true);
                    player.kick(Component.text("Du hast das Spiel verlassen!", NamedTextColor.RED));
                }
            }
        }
    }
}

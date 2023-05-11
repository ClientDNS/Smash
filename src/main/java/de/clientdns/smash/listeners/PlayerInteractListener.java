package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.builder.Item;
import de.clientdns.smash.builder.Skull;
import de.clientdns.smash.character.Character;
import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.inventory.InventoryCreator;
import de.clientdns.smash.map.Map;
import de.clientdns.smash.map.MapLoader;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static net.kyori.adventure.text.Component.empty;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public class PlayerInteractListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            event.setCancelled(true);
            return;
        }
        Player player = event.getPlayer();
        switch (event.getMaterial()) {
            case CHEST -> {
                List<ItemStack> items = new ArrayList<>(List.of());
                for (Character character : Character.values()) {
                    items.add(new Skull(1, character.data().name()).build());
                }
                Item explanation = new Item(Material.OAK_WALL_SIGN, 1, empty(), List.of(empty(), MiniMsg.plain("Every character has it's own abilities!", GRAY)));
                new InventoryCreator(3, MiniMsg.plain("Characters", NamedTextColor.GOLD)).edit(editor -> {
                    for (int i = 0; i < items.size(); i++) {
                        editor.set(i, items.get(i));
                    }
                    editor.set(22, explanation.build());
                }).accept(player::openInventory);
                event.setCancelled(true);
            }
            case MAP -> new InventoryCreator(1, MiniMsg.plain("Maps", NamedTextColor.GOLD)).edit(editor -> {
                if (SmashPlugin.getPlugin().getSmashConfig().noMaps()) {
                    player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("No maps set up", RED)));
                    event.setCancelled(true);
                    return;
                }
                for (Map map : MapLoader.getConfigurationMaps()) {
                    editor.add(new Item(map.item(), 1, MiniMsg.plain(map.name(), GREEN)).build());
                    event.setCancelled(true);
                }
            }).accept(player::openInventory, !SmashPlugin.getPlugin().getSmashConfig().noMaps());
            default -> event.setCancelled(true);
        }
    }
}

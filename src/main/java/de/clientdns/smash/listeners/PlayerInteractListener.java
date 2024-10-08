package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.builder.GameInventory;
import de.clientdns.smash.builder.Item;
import de.clientdns.smash.builder.Skull;
import de.clientdns.smash.character.Character;
import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.map.loader.MapLoader;
import de.clientdns.smash.strings.Strings;
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

import static net.kyori.adventure.text.format.NamedTextColor.GREEN;
import static net.kyori.adventure.text.format.NamedTextColor.RED;

public class PlayerInteractListener implements Listener {

    @EventHandler
    void on(@NotNull PlayerInteractEvent event) {
        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
            event.setCancelled(true);
            return;
        }
        Player player = event.getPlayer();

        if (SmashPlugin.getPlugin().getSmashConfig().noMaps()) {
            player.sendActionBar(MiniMsg.mini("prefix").append(MiniMsg.plain("No maps found in config!", RED)));
            event.setCancelled(true);
            return;
        }

        switch (event.getMaterial()) {
            case CHEST -> {
                List<ItemStack> items = new ArrayList<>(List.of());
                for (Character character : Character.values()) {
                    items.add(new Skull(1, character.data().name()).build());
                }
                new GameInventory(Strings.CHARACTERS_SELECTION_NAME).edit(editor -> {
                    for (int i = 0; i < items.size(); i++) {
                        editor.set(i, items.get(i));
                    }
                }).accept(player::openInventory);
                event.setCancelled(true);
            }
            case MAP -> new GameInventory(MiniMsg.plain("Maps", NamedTextColor.GOLD)).edit(editor -> {
                for (String mapName : MapLoader.getLoadedMaps().keySet()) {
                    editor.add(new Item(Material.PAPER, 1, MiniMsg.plain(mapName, GREEN)).build());
                    event.setCancelled(true);
                }
            }).accept(player::openInventory, !SmashPlugin.getPlugin().getSmashConfig().noMaps());
            default -> event.setCancelled(true);
        }
    }
}

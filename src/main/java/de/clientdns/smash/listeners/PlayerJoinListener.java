package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.Constants;
import de.clientdns.smash.countdown.LobbyCountdown;
import de.clientdns.smash.util.ItemStackUtil;
import de.clientdns.smash.util.PlayerUtil;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static net.kyori.adventure.text.Component.empty;
import static net.kyori.adventure.text.Component.text;

public class PlayerJoinListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (SmashPlugin.getPlugin().getGameStateManager().isLobbyState()) {
            player.setGameMode(GameMode.SURVIVAL);

            player.setHealth(20);
            player.setFoodLevel(20);

            player.setExp(0);
            player.setLevel(0);

            player.setAllowFlight(false);
            player.setFlying(false);

            // Remove 1.9+ cooldown
            player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(1024);
            player.saveData();

            if (!player.getInventory().isEmpty()) player.getInventory().clear();

            ItemStack characters = new ItemStackUtil(Material.CHEST, 1, text("Charaktere", NamedTextColor.GOLD)).loreLines(empty(), text("Ändere deinen Charakter", NamedTextColor.GRAY), empty()).build();
            player.getInventory().setItem(2, characters);

            ItemStack maps = new ItemStackUtil(Material.MAP, 1, text("Maps", NamedTextColor.GOLD)).loreLines(empty(), text("Stimme für eine Map ab", NamedTextColor.GRAY), empty()).build();
            player.getInventory().setItem(6, maps);

            int online = Bukkit.getOnlinePlayers().size();
            int minPlayers = Constants.minPlayers();
            int maxPlayers = Bukkit.getMaxPlayers();

            PlayerUtil.broadcast(text(player.getName() + " ist dem Server beigetreten.", NamedTextColor.GREEN));
            if (online >= minPlayers) {
                LobbyCountdown.start(); // Start countdown if minimum players are reached
            }
        } else if (SmashPlugin.getPlugin().getGameStateManager().isIngameState()) {
            player.setGameMode(GameMode.SPECTATOR);
        }
        event.joinMessage(empty());
    }
}

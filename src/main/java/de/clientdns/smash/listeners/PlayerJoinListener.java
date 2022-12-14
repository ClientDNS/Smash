package de.clientdns.smash.listeners;

import de.clientdns.smash.api.SmashApi;
import de.clientdns.smash.api.builder.ItemStackBuilder;
import de.clientdns.smash.countdown.LobbyCountdown;
import de.clientdns.smash.api.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static de.clientdns.smash.config.Value.get;
import static de.clientdns.smash.config.Value.plain;
import static net.kyori.adventure.text.Component.empty;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public class PlayerJoinListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.setHealth(20);
        player.setFoodLevel(20);
        player.setExp(0);
        player.setLevel(0);

        if (SmashApi.gameStateManager().lobbyState()) {
            player.setGameMode(GameMode.SURVIVAL);
            player.setAllowFlight(false);
            player.setFlying(false);

            setAttackSpeed(player);

            if (!player.getInventory().isEmpty()) player.getInventory().clear();

            new ItemStackBuilder(Material.CHEST, 1, plain("Charaktere", GOLD), List.of(empty(), plain("Ändere deinen Charakter", GRAY), empty())).make(characters -> player.getInventory().setItem(2, characters));
            new ItemStackBuilder(Material.MAP, 1, plain("Maps", GOLD), List.of(empty(), plain("Stimme für eine Map ab", GRAY), empty())).make(maps -> player.getInventory().setItem(6, maps));

            int online = Bukkit.getOnlinePlayers().size();
            int minPlayers = get("min-players", 2);

            PlayerUtil.broadcast(plain(player.getName() + " ist dem Server beigetreten.", GREEN));
            if (online >= minPlayers) {
                LobbyCountdown.start(); // Start countdown if minimum players are reached
            }
        } else if (SmashApi.gameStateManager().ingameState()) {
            player.setGameMode(GameMode.SPECTATOR);

            setAttackSpeed(player);

            if (!player.getInventory().isEmpty()) player.getInventory().clear();
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                if (onlinePlayer.getGameMode().equals(GameMode.SPECTATOR))
                    onlinePlayer.sendMessage(plain("[+] " + player.getName(), GREEN));
            }
        }
        event.joinMessage(empty());
    }

    private void setAttackSpeed(@NotNull Player player) {
        AttributeInstance attackSpeed = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        if (attackSpeed != null)
            attackSpeed.setBaseValue(1024); // Remove 1.9+ cooldown
        player.saveData();
    }
}

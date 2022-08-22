package de.clientdns.smash.listeners;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.Constants;
import de.clientdns.smash.countdown.LobbyCountdown;
import de.clientdns.smash.gamestate.GameState;
import de.clientdns.smash.util.ItemStackUtil;
import net.kyori.adventure.text.Component;
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

import java.util.Objects;

public class PlayerJoinListener implements Listener {

    @SuppressWarnings("unused")
    @EventHandler
    void on(@NotNull PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.setGameMode(GameMode.SURVIVAL);

        player.setHealth(20);
        player.setFoodLevel(20);

        player.setExp(0);
        player.setLevel(0);

        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_SPEED)).setBaseValue(1024);
        player.saveData();

        if (!player.getInventory().isEmpty()) player.getInventory().clear();

        ItemStack characters = new ItemStackUtil().name(Component.text("Charaktere", NamedTextColor.GOLD)).loreLines(" ", "<gray>Ändere deinen Charakter</gray>", " ").material(Material.CHEST).build();
        player.getInventory().setItem(2, characters);

        ItemStack maps = new ItemStackUtil().name(Component.text("Maps", NamedTextColor.GOLD)).loreLines(" ", "<gray>Stimme für eine Map ab</gray>", " ").material(Material.MAP).build();
        player.getInventory().setItem(6, maps);

        if (SmashPlugin.getGameStateManager().getCurrentState().equals(GameState.LOBBY)) {
            int online = player.getServer().getOnlinePlayers().size();
            int minPlayers = 1;
            int maxPlayers = Bukkit.getMaxPlayers();
            event.joinMessage(Constants.prefix().append(Component.text("§e" + player.getName() + " §7ist dem Server beigetreten§8. [§a" + online + "§8/§a" + maxPlayers + "§8]")));
            if (online >= minPlayers) {
                if (!LobbyCountdown.isRunning()) LobbyCountdown.start(player);
            } else {
                if (LobbyCountdown.isRunning()) LobbyCountdown.stop(player);
            }
        } else if (SmashPlugin.getGameStateManager().getCurrentState().equals(GameState.INGAME)) {
            event.joinMessage(Component.empty());
            player.setGameMode(GameMode.SPECTATOR);
            player.setExp(0);
            player.setLevel(0);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_SPEED)).setBaseValue(0);
            player.saveData();
        }
    }
}

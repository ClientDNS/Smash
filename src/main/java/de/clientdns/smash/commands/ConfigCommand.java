package de.clientdns.smash.commands;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.values.ConfigValues;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public class ConfigCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (!player.hasPermission("smash.config")) {
                sender.sendMessage("§cDu hast keine Berechtigung für diesen Befehl.");
                return false;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    player.sendMessage(ConfigValues.prefix().append(text("Die Config wird neu geladen...", YELLOW)));
                    SmashPlugin.getPlugin().getSmashConfig().reload();
                    player.sendMessage(ConfigValues.prefix().append(text("Die Config wurde neu geladen.", GREEN)));
                } else {
                    sender.sendMessage(ConfigValues.prefix().append(text("Unbekannter Befehl.", RED)));
                }
            } else {
                sender.sendMessage(ConfigValues.prefix().append(text("/config <reload>", RED)));
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("smash.config")) {
            if (args.length == 1) {
                return Stream.of("reload").filter(s -> s.startsWith(args[0])).toList();
            }
        }
        return List.of();
    }
}

package de.clientdns.smash.commands;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.values.ConfigValues;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public class ConfigCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("smash.config")) {
            sender.sendMessage(ConfigValues.prefix().append(ConfigValues.permissionRequired()));
            return false;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("keys")) {
                int keys = 0;
                for (String key : SmashPlugin.plugin().configuration().getKeys(false)) {
                    sender.sendMessage(ConfigValues.prefix().append(text(++keys + ". " + key, RED)));
                }
                return true;
            } else if (args[0].equalsIgnoreCase("reload")) {
                SmashPlugin.plugin().configuration().reload();
                sender.sendMessage(ConfigValues.prefix().append(ConfigValues.configReloaded()));
                return true;
            } else {
                sender.sendMessage(ConfigValues.prefix().append(text("Unbekannter Befehl. (" + args[0] + ")", RED)));
                return true;
            }
        } else {
            sender.sendMessage(ConfigValues.prefix().append(text("/config <keys, reload>", RED)));
            return false;
        }
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("smash.config")) {
            if (args.length == 1) {
                return Stream.of("keys", "reload").filter(s -> s.startsWith(args[0])).toList();
            }
        }
        return null;
    }
}

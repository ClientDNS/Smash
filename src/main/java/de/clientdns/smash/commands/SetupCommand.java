package de.clientdns.smash.commands;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.values.ConfigValues;
import de.clientdns.smash.map.setup.MapSetup;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public class SetupCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (!player.hasPermission("smash.setup")) {
                sender.sendMessage(ConfigValues.prefix().append(ConfigValues.permissionRequired()));
                return false;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("create")) {
                    player.sendMessage(ConfigValues.prefix().append(text("Du hast einen Namen für die Map vergessen. :D", GRAY)));
                    return false;
                }
                if (args[0].equalsIgnoreCase("finish")) {
                    if (SmashPlugin.plugin().setupManager().setup(player).isPresent()) {
                        MapSetup mapSetup = SmashPlugin.plugin().setupManager().setup(player).get();
                        mapSetup.finish();
                        player.sendMessage(ConfigValues.prefix().append(text("Du hast die Map erstellt.", GREEN)));
                    } else {
                        player.sendMessage(ConfigValues.prefix().append(text("Du hast keine Map-Erstellung gestartet.", RED)));
                    }
                    return true;
                } else {
                    sender.sendMessage(ConfigValues.prefix().append(ConfigValues.unknownCommand(args[0])));
                }
            } else if (args.length == 2) {
                String mapName = args[1];
                if (mapName.length() > 16) {
                    player.sendMessage(ConfigValues.prefix().append(text("Der Name der Map darf nicht länger als 16 Zeichen sein.", RED)));
                    return false;
                }
                MapSetup setup = new MapSetup(player, mapName);
                if (args[0].equalsIgnoreCase("create")) {
                    if (SmashPlugin.plugin().setupManager().setup(player).isEmpty()) {
                        setup.start();
                        sender.sendMessage(ConfigValues.prefix().append(text("Map-Erstellung '" + mapName + "' gestartet.", GREEN)));
                    } else {
                        sender.sendMessage(ConfigValues.prefix().append(text("Map-Erstellung '" + mapName + "' läuft bereits.", RED)));
                    }
                } else {
                    sender.sendMessage(ConfigValues.prefix().append(ConfigValues.unknownCommand(args[0])));
                }
            } else {
                sender.sendMessage(ConfigValues.prefix().append(text("/setup <create> <map name (max. 16)>", RED)));
            }
        } else {
            sender.sendMessage(ConfigValues.prefix().append(ConfigValues.playerRequired()));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (sender.hasPermission("smash.setup")) {
            if (args.length == 1) {
                return Stream.of("create", "finish").filter(s -> s.startsWith(args[0])).toList();
            }
        }
        return null;
    }
}

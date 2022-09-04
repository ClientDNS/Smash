package de.clientdns.smash.commands;

import com.google.common.collect.ImmutableList;
import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.Constants;
import de.clientdns.smash.map.setup.MapSetup;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SetupCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (!player.hasPermission("smash.setup")) {
                sender.sendMessage(Constants.prefix().append(Constants.permissionRequired()));
                return false;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("create")) {
                    player.sendMessage(Constants.prefix().append(Component.text("Ich glaube, du hast einen Namen für die Map vergessen. :)", NamedTextColor.GREEN)));
                    return false;
                }
                if (args[0].equalsIgnoreCase("abort")) {
                    if (SmashPlugin.getPlugin().getSetupManager().contains(player)) {
                        MapSetup mapSetup = SmashPlugin.getPlugin().getSetupManager().getSetup(player);
                        mapSetup.delete();
                        player.sendMessage(Constants.prefix().append(Component.text("Du hast die Map-Erstellung abgebrochen.", NamedTextColor.GRAY)));
                    } else {
                        player.sendMessage(Constants.prefix().append(Component.text("Du hast keine Map-Erstellung gestartet.", NamedTextColor.GRAY)));
                    }
                    return true;
                } else if (args[0].equalsIgnoreCase("finish")) {
                    if (SmashPlugin.getPlugin().getSetupManager().contains(player)) {
                        MapSetup mapSetup = SmashPlugin.getPlugin().getSetupManager().getSetup(player);
                        mapSetup.finish();
                        player.sendMessage(Constants.prefix().append(Component.text("Du hast die Map erfolgreich erstellt.", NamedTextColor.GRAY)));
                    } else {
                        player.sendMessage(Constants.prefix().append(Component.text("Du hast keine Map-Erstellung gestartet.", NamedTextColor.GRAY)));
                    }
                } else {
                    player.sendMessage(Constants.prefix().append(Component.text("/setup <abort>", NamedTextColor.RED)));
                    player.sendMessage(Constants.prefix().append(Component.text("/setup <finish>", NamedTextColor.RED)));
                }
            } else if (args.length == 2) {
                String mapName = args[1];
                MapSetup mapSetup = new MapSetup(player, mapName);
                if (args[0].equalsIgnoreCase("create")) {
                    if (!SmashPlugin.getPlugin().getSetupManager().contains(player)) {
                        mapSetup.start();
                        sender.sendMessage(Component.text("Map setup with name " + mapName + " started.", NamedTextColor.GREEN));
                    } else {
                        sender.sendMessage(Component.text("Map setup with name " + mapName + " already running.", NamedTextColor.RED));
                    }
                } else {
                    sender.sendMessage(Component.text("Unknown command.", NamedTextColor.RED));
                }
            } else {
                sender.sendMessage(Component.text("/setup <create> <map name>", NamedTextColor.RED));
                return false;
            }
        } else {
            sender.sendMessage(Constants.prefix().append(Constants.playerRequired()));
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (sender.hasPermission("smash.setup")) {
            if (args.length == 1) {
                return List.of("create", "abort", "finish");
            }
        }
        return ImmutableList.of();
    }
}

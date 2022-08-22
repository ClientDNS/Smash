package de.clientdns.smash.commands;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.setup.MapSetup;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetupCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (player.hasPermission("smash.setup")) {
                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("create")) {
                        String mapName = args[1];
                        if (!SmashPlugin.getSetupManager().contains(mapName)) {
                            MapSetup mapSetup = new MapSetup(mapName);
                            mapSetup.start();
                            sender.sendMessage(Component.text("Map setup with name " + mapName + " started.", NamedTextColor.GREEN));
                        } else {
                            sender.sendMessage(Component.text("Map setup with name " + mapName + " already running.", NamedTextColor.RED));
                        }
                    } else if (args[0].equalsIgnoreCase("finish")) {
                        String mapName = args[1];
                        if (SmashPlugin.getSetupManager().contains(mapName)) {
                            SmashPlugin.getSetupManager().get(mapName).finish();
                            sender.sendMessage(Component.text("Map setup with name " + mapName + " finished.", NamedTextColor.GREEN));
                        } else {
                            sender.sendMessage(Component.text("Map setup with name " + mapName + " not found.", NamedTextColor.RED));
                        }
                    } else {
                        sender.sendMessage(Component.text("Invalid argument: '" + args[0] + "'", NamedTextColor.YELLOW));
                        return false;
                    }
                } else {
                    sender.sendMessage(Component.text("/setup <create|finish> <map name>", NamedTextColor.RED));
                    return true;
                }
            } else {
                sender.sendMessage(Component.text("You don't have permission to do this!", NamedTextColor.RED));
            }
        } else {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }
        return false;
    }
}

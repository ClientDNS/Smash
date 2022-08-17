package de.clientdns.smash.commands;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ConfigCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String string, @NotNull String[] args) {
        if (sender.hasPermission("smash.reload")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    SmashPlugin.getPlugin().loadConfig();
                    sender.sendMessage("§aConfig reloaded");
                    return true;
                } else {
                    sender.sendMessage("§cInvalid argument");
                    return false;
                }
            } else {
                sender.sendMessage("§c/config reload");
                return false;
            }
        } else {
            sender.sendMessage("§cYou don't have permission to do this!");
        }
        return false;
    }
}

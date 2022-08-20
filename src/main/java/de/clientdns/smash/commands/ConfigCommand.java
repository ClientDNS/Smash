package de.clientdns.smash.commands;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.Constants;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
                    sender.sendMessage(Constants.prefix().append(Component.text("Config reloaded.", NamedTextColor.GREEN)));
                    return true;
                } else {
                    sender.sendMessage(Constants.prefix().append(Component.text("Invalid argument: " + args[0], NamedTextColor.RED)));
                    return false;
                }
            } else {
                sender.sendMessage(Constants.prefix().append(Component.text("§c/config §8<§creload§8>")));
                return false;
            }
        } else {
            sender.sendMessage(Component.text("You don't have permission to do this!", NamedTextColor.RED));
        }
        return false;
    }
}

package de.clientdns.smash.commands;

import de.clientdns.smash.config.MiniMsg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

import static net.kyori.adventure.text.format.NamedTextColor.RED;

public class SmashDebugCommand extends Command {

    public SmashDebugCommand(String name, String description, String usageMessage) {
        super(name, description, usageMessage, List.of("sd"));
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("You have to be a player to do that.", RED)));
            return false;
        }
        if (!sender.hasPermission("smash.debug")) {
            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("permission-required")));
            return false;
        }

        return false;
    }

    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        if (sender.hasPermission("smash.debug")) {
            if (args.length == 1) {
                return Stream.of("gamestate", "vote").filter(s -> s.startsWith(args[0])).toList();
            }
        }
        return List.of();
    }
}

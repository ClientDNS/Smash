package de.clientdns.smash.commands;

import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.map.loader.MapLoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.kyori.adventure.text.format.NamedTextColor.RED;

public class VoteCommand extends Command {

    public VoteCommand(String name, String description, String usageMessage) {
        super(name, description, usageMessage, List.of());
    }

    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String label, String @NotNull [] args) {
        if (args.length == 1) {
            return MapLoader.getLoadedMaps().keySet().stream().filter(s -> s.startsWith(args[0])).toList();
        }
        return List.of();
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("You have to be a player to do that.", RED)));
            return false;
        }
        return false;
    }
}

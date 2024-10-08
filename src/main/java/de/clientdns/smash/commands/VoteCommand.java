package de.clientdns.smash.commands;

import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.map.loader.MapLoader;
import de.clientdns.smash.strings.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.kyori.adventure.text.format.NamedTextColor.GRAY;
import static net.kyori.adventure.text.format.NamedTextColor.GREEN;

public class VoteCommand extends Command {

    public VoteCommand(String name, String description, String usageMessage) {
        super(name, description, usageMessage, List.of("v"));
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
            sender.sendMessage(MiniMsg.mini("prefix").append(Strings.ONLY_PLAYERS));
            return false;
        }
        if (args.length == 1) {
            String lowerCaseArg = args[0].toLowerCase();
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("You voted for '" + lowerCaseArg + "'.", GREEN)));
        } else {
            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Use following arguments:", GRAY)));
            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("/vote <map>", GREEN)));
        }
        return false;
    }
}

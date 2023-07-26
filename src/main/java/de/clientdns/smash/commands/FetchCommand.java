package de.clientdns.smash.commands;

import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.util.UUIDUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static net.kyori.adventure.text.format.NamedTextColor.GREEN;
import static net.kyori.adventure.text.format.NamedTextColor.RED;

public class FetchCommand extends Command {

    public FetchCommand(@NotNull String name) {
        super(name, "/", "/", List.of());
    }

    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        if (sender.hasPermission("smash.fetch")) {
            if (args.length == 1) {
                return Arrays.stream(UUIDUtil.cached.values().toArray(new String[0])).filter(s -> s.startsWith(args[0])).toList();
            }
        }
        return List.of();
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("player-required")));
            return false;
        }
        if (!player.hasPermission("smash.setup")) {
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("permission-required")));
            return false;
        }
        if (args.length == 1) {
            String name = args[0];
            if (name.isEmpty()) {
                sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Name is empty.", RED)));
                return false;
            }
            if (name.isBlank()) {
                sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Name is blank.", RED)));
                return false;
            }
            Optional<String> uuid = UUIDUtil.uuid(args[0]);
            uuid.ifPresent(s -> sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("UUID of '" + name + "' is '" + uuid.get() + "'.", GREEN))));
        }
        return false;
    }
}

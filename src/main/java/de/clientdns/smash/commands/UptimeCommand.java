package de.clientdns.smash.commands;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.values.ConfigValues;
import de.clientdns.smash.util.FormatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public class UptimeCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.hasPermission("smash.setup")) {
            sender.sendMessage(ConfigValues.prefix().append(ConfigValues.permissionRequired()));
            return false;
        }
        if (args.length == 0) {
            Duration time = Duration.between(SmashPlugin.plugin().startTime(), Instant.now());
            sender.sendMessage(ConfigValues.prefix().append(text(FormatUtil.formatSeconds(time.getSeconds()), RED)));
        } else {
            sender.sendMessage(ConfigValues.prefix().append(ConfigValues.unknownCommand(args[0])));
            return false;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("smash.whatever")) {
            if (args.length == 1) {
                String[] commands = SmashPlugin.plugin().configuration().getKeys(true).toArray(new String[0]);
                return Stream.of(commands).filter(s -> s.startsWith(args[0])).toList();
            }
        }
        return null;
    }
}

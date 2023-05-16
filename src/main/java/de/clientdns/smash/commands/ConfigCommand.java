package de.clientdns.smash.commands;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.config.SmashConfig;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

import static net.kyori.adventure.text.format.NamedTextColor.*;

public class ConfigCommand extends Command {

    public ConfigCommand(String name) {
        super(name, "/", "/", List.of());
    }

    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        if (sender.hasPermission("smash.config")) {
            if (args.length == 1) {
                return Stream.of("reload", "save").filter(s -> s.startsWith(args[0])).toList();
            }
        }
        return List.of();
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!sender.hasPermission("smash.setup")) {
            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("permission-required")));
            return false;
        }
        if (args.length == 1) {
            SmashConfig config = SmashPlugin.getPlugin().getSmashConfig();
            switch (args[0].toLowerCase()) {
                case "reload" -> {
                    if (config.leftChanges()) {
                        sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("There are detected changes.", RED)));
                        sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Use '/config save' to save the file.", RED)));
                        return false;
                    }
                    config.load();
                    sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Configuration file reloaded.", GREEN)));
                }
                case "save" -> {
                    if (!config.leftChanges()) {
                        sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("No changes detected.", RED)));
                        return false;
                    }
                    config.save(exception -> {
                        if (exception == null) {
                            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Configuration file saved.", GREEN)));
                        }
                    });
                }
            }
        } else {
            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Use following arguments:", GRAY)));
            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("- reload", GREEN)));
            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("- save", GREEN)));
            return false;
        }
        return false;
    }
}

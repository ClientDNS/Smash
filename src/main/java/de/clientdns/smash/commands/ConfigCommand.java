package de.clientdns.smash.commands;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.config.PluginConfig;
import de.clientdns.smash.strings.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

import static net.kyori.adventure.text.format.NamedTextColor.*;

public class ConfigCommand extends Command {

    public ConfigCommand(String name, String description, String usageMessage) {
        super(name, description, usageMessage, List.of("cfg"));
    }

    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        if (sender.hasPermission("smash.config")) {
            if (args.length == 1) {
                return Stream.of("delete", "discard", "reload", "save").filter(s -> s.startsWith(args[0])).toList();
            }
        }
        return List.of();
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!sender.hasPermission("smash.setup")) {
            sender.sendMessage(MiniMsg.mini("prefix").append(Strings.PERMISSION_REQUIRED));
            return false;
        }
        if (args.length == 1) {
            PluginConfig config = SmashPlugin.getPlugin().getSmashConfig();
            switch (args[0].toLowerCase()) {
                case "delete" -> {
                    if (config.isChanged()) {
                        sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Open changes detected.", RED)));
                        sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Save with /config save", RED)));
                        sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("before deleting the config file.", RED)));
                        return false;
                    }
                    if (SmashPlugin.getPlugin().getSmashConfig().delete()) {
                        sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Config file deleted.", GREEN)));
                    } else {
                        sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Could not delete config file.", RED)));
                    }
                }
                case "discard" -> {
                    if (!config.isChanged()) {
                        sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("No changes detected.", RED)));
                        return false;
                    }
                    config.discardChanges();
                    sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Changes discarded.", GREEN)));
                }
                case "reload" -> {
                    if (config.isChanged()) {
                        sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("There are detected changes!", RED)));
                        sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("You can not reload without saving your changes.", RED)));
                        sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Use '/config save' to save the file.", RED)));
                        return false;
                    }
                    config.load();
                    sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Configuration reloaded.", GREEN)));
                }
                case "save" -> {
                    if (!config.isChanged()) {
                        sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("No changes detected.", RED)));
                        return false;
                    }
                    config.save(exception -> {
                        if (exception == null) {
                            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Configuration saved.", GREEN)));
                        }
                    });
                }
                default -> {
                    sender.sendMessage(MiniMsg.mini("prefix").append(Strings.UNKNOWN_COMMAND.replaceText(builder -> builder.matchLiteral("$command").replacement(args[0]))));
                    return false;
                }
            }
        } else {
            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Use following arguments:", GRAY)));
            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("- delete", GREEN)));
            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("- discard", GREEN)));
            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("- reload", GREEN)));
            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("- save", GREEN)));
            return false;
        }
        return false;
    }
}

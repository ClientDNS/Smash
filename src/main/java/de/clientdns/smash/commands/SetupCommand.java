package de.clientdns.smash.commands;

import de.clientdns.smash.api.SmashApi;
import de.clientdns.smash.api.map.setup.MapSetup;
import de.clientdns.smash.config.Value;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

import static de.clientdns.smash.config.Value.get;
import static de.clientdns.smash.config.Value.plain;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public class SetupCommand extends Command {

    public SetupCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String label, @NotNull String @NotNull [] args) {
        if (sender.hasPermission("smash.setup"))
            if (args.length == 1) return Stream.of("create", "finish").filter(s -> s.startsWith(args[0])).toList();
        return List.of();
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String a, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (!player.hasPermission("smash.setup")) {
                sender.sendMessage(get("prefix").append(get("permission-required")));
                return false;
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("create")) {
                    player.sendMessage(get("prefix").append(plain("Du hast einen Namen für die Map vergessen. :D", GRAY)));
                    return false;
                }
                if (args[0].equalsIgnoreCase("finish")) {
                    if (SmashApi.setupManager().setup(player) != null) {
                        MapSetup mapSetup = SmashApi.setupManager().setup(player);
                        mapSetup.finish();
                        player.sendMessage(get("prefix").append(plain("Du hast die Map erstellt.", GREEN)));
                    } else
                        player.sendMessage(get("prefix").append(plain("Du hast keine Map-Erstellung gestartet.", RED)));
                    return true;
                } else
                    sender.sendMessage(get("prefix").append(get("unknown-command").replaceText(builder -> builder.matchLiteral("%command%").replacement(args[0]))));
            } else if (args.length == 2) {
                String mapName = args[1];
                if (mapName.length() > 16) {
                    player.sendMessage(get("prefix").append(plain("Der Name der Map darf nicht länger als 16 Zeichen sein.", RED)));
                    return false;
                }
                MapSetup setup = new MapSetup(player, mapName);
                if (args[0].equalsIgnoreCase("create")) {
                    if (SmashApi.setupManager().setup(player) == null) {
                        setup.start();
                        sender.sendMessage(Value.get("prefix").append(plain("Map-Erstellung '" + mapName + "' gestartet.", GREEN)));
                    } else
                        sender.sendMessage(get("prefix").append(plain("Map-Erstellung '" + mapName + "' läuft bereits.", RED)));
                } else
                    sender.sendMessage(get("prefix").append(get("unknown-command").replaceText(builder -> builder.matchLiteral("%command%").replacement(args[0]))));
            } else {
                sender.sendMessage(get("prefix").append(plain("/setup <create> <map name (max. 16)>", RED)));
                return false;
            }
        } else {
            sender.sendMessage(get("prefix").append(get("player-required")));
            return false;
        }
        return false;
    }
}

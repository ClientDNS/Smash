package de.clientdns.smash.commands;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.gamestate.GameState;
import de.clientdns.smash.map.Map;
import de.clientdns.smash.map.loader.MapLoader;
import de.clientdns.smash.map.setup.MapSetup;
import de.clientdns.smash.strings.Strings;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

import static net.kyori.adventure.text.format.NamedTextColor.*;

public class SetupCommand extends Command {

    public SetupCommand(String name, String description, String usageMessage) {
        super(name, description, usageMessage, List.of());
    }

    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        if (sender.hasPermission("smash.setup")) {
            if (args.length == 1) {
                return Stream.of("abort", "finish", "set", "start").filter(s -> s.startsWith(args[0])).toList();
            }
        }
        return List.of();
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(MiniMsg.mini("prefix").append(Strings.ONLY_PLAYERS));
            return false;
        }
        if (!player.hasPermission("smash.setup")) {
            player.sendMessage(MiniMsg.mini("prefix").append(Strings.PERMISSION_REQUIRED));
            return false;
        }
        if (args.length == 1) {
            if (!SmashPlugin.getPlugin().getGameStateManager().is(GameState.LOBBY)) {
                player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("The setup is not possible while playing.", RED)));
                return false;
            }
            switch (args[0].toLowerCase()) {
                case "abort" -> {
                    if (SmashPlugin.getPlugin().getSetups().get(player) == null) {
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("No setup started.", RED)));
                        return false;
                    }
                    MapSetup mapSetup = SmashPlugin.getPlugin().getSetups().get(player);
                    mapSetup.delete();
                    player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("You aborted the setup.", RED)));
                    return true;
                }
                case "finish" -> {
                    if (SmashPlugin.getPlugin().getSetups().get(player) == null) {
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("No setup started.", RED)));
                        return false;
                    }
                    MapSetup mapSetup = SmashPlugin.getPlugin().getSetups().get(player);
                    if (mapSetup.countLocations() < mapSetup.getIndexSize()) {
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Not enough spawn positions! (" + mapSetup.countLocations() + " of " + mapSetup.getIndexSize() + ")", RED)));
                        return false;
                    }
                    Map map = mapSetup.finish();
                    if (map == null) {
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Error while saving map.", GREEN)));
                        return true;
                    }
                    if (map.write()) {
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Map '" + map.name() + "' cached.", GREEN)));
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Save the map with '/config save'.", YELLOW)));
                    } else {
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Map '" + map.name() + "' not cached because of an error.", RED)));
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Look in the console to find out, what's wrong.", RED)));
                    }
                    return true;
                }
                default ->
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("unknown-command").replaceText(builder -> builder.matchLiteral("$command").replacement(args[0]))));
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("set")) {
                if (SmashPlugin.getPlugin().getSetups().get(player) == null) {
                    player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("No setup started.", RED)));
                    return false;
                }
                MapSetup mapSetup = SmashPlugin.getPlugin().getSetups().get(player);
                if (!NumberUtils.isParsable(args[1])) {
                    sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Index '" + args[1] + "' is not a valid number.", RED)));
                    return false;
                }
                int index = NumberUtils.toInt(args[1]);
                if (index < 0) {
                    player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Positions below 0 are not allowed.", RED)));
                    return false;
                }
                if (index >= mapSetup.getIndexSize()) {
                    player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("There is a set limit of " + mapSetup.getIndexSize() + " positions.", RED)));
                    return false;
                }
                mapSetup.setSpawnLocation(index, player.getLocation());
                player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Spawn location '" + index + "' set.", GREEN)));
            } else {
                sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("unknown-command").replaceText(builder -> builder.matchLiteral("$command").replacement(args[0]))));
                return false;
            }
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("start")) {
                String mapName = args[1];
                if (MapLoader.contains(mapName)) {
                    player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Map '" + mapName + "' already exists.", RED)));
                    return false;
                }
                if (SmashPlugin.getPlugin().getSetups().get(player) != null) {
                    MapSetup mapSetup = SmashPlugin.getPlugin().getSetups().get(player);
                    sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Setup '" + mapSetup.getName() + "' already running.", YELLOW)));
                    return false;
                }
                if (!NumberUtils.isParsable(args[2])) {
                    sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Index size '" + args[2] + "' is not a valid number.", RED)));
                    return false;
                }
                int indexSize = NumberUtils.toInt(args[2]);
                MapSetup setup = new MapSetup(player, mapName, indexSize);
                sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Setup '" + setup.getName() + "' started.", GREEN)));
            } else {
                player.sendMessage(MiniMsg.mini("prefix").append(Strings.UNKNOWN_COMMAND.replaceText(builder -> builder.matchLiteral("$command").replacement(args[0]))));
                return false;
            }
        } else {
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Use following arguments:", GRAY)));
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("- abort", GREEN)));
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("- finish", GREEN)));
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("- set <index (beginning with 0)>", GREEN)));
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("- start <map-name> <size of spawn locations>", GREEN)));
            return false;
        }
        return false;
    }
}

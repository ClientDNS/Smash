package de.clientdns.smash.commands;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.gamestate.GameState;
import de.clientdns.smash.map.Map;
import de.clientdns.smash.map.MapLoader;
import de.clientdns.smash.map.setup.MapSetup;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

import static net.kyori.adventure.text.format.NamedTextColor.*;

public class SetupCommand extends Command {

    public SetupCommand(String name) {
        super(name, "/", "/", List.of());
    }

    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        if (sender.hasPermission("smash.setup")) {
            if (args.length == 1) {
                return Stream.of("start", "abort", "set", "finish").filter(s -> s.startsWith(args[0])).toList();
            }
        }
        return List.of();
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("player-required")));
            return false;
        }
        if (!player.hasPermission("smash.setup")) {
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("permission-required")));
            return false;
        }
        if (args.length == 1) {
            if (!SmashPlugin.getPlugin().getGameStateManager().getCurrentState().equals(GameState.LOBBY)) {
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
        } else if (args.length == 4) {
            if (args[0].equalsIgnoreCase("start")) {
                String mapName = args[1];
                int actual;
                if (mapName.length() > 16) {
                    actual = mapName.length();
                    player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Map name cannot be longer than 16 chars. ('" + mapName + "': " + actual + ")", RED)));
                    return false;
                }
                if (MapLoader.contains(mapName)) {
                    player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Map '" + mapName + "' already exists.", RED)));
                    return false;
                }
                Material material = Material.getMaterial(args[2]);
                if (material == null) {
                    sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Material '" + args[3] + "' is not a valid material.", RED)));
                    return false;
                }
                if (material.isAir()) {
                    sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Material '" + args[3] + "' could not be set.", RED)));
                    return false;
                }
                if (SmashPlugin.getPlugin().getSetups().get(player) != null) {
                    MapSetup mapSetup = SmashPlugin.getPlugin().getSetups().get(player);
                    sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Setup '" + mapSetup.getName() + "' already running.", YELLOW)));
                    return false;
                }
                if (!NumberUtils.isParsable(args[3])) {
                    sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Index size '" + args[3] + "' is not a valid number.", RED)));
                    return false;
                }
                int indexSize = NumberUtils.toInt(args[3]);
                MapSetup setup = new MapSetup(player, mapName, material, indexSize);
                sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Setup '" + setup.getName() + "' started.", GREEN)));
            } else {
                player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("unknown-command").replaceText(builder -> builder.matchLiteral("$command").replacement(args[0]))));
                return false;
            }
        } else {
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Use following arguments:", GRAY)));
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("- abort", GREEN)));
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("- finish", GREEN)));
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("- set <index (beginning with 0)>", GREEN)));
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("- start <map-name> <icon-material> <size of spawn locations>", GREEN)));
            return false;
        }
        return false;
    }
}

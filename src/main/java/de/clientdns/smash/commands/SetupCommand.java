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
                return Stream.of("start", "abort", "setlocation", "finish").filter(s -> s.startsWith(args[0])).toList();
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
                player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Das Setup ist während des Spiels nicht möglich.", RED)));
                return false;
            }
            switch (args[0].toLowerCase()) {
                case "abort" -> {
                    if (SmashPlugin.getPlugin().getSetups().get(player) == null) {
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Du hast keine Map-Erstellung gestartet.", RED)));
                        return false;
                    }
                    MapSetup mapSetup = SmashPlugin.getPlugin().getSetups().get(player);
                    player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Breche Map-Erstellung '" + mapSetup.getName() + "' ab...", RED)));
                    mapSetup.delete();
                    player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Du hast die Map-Erstellung abgebrochen.", RED)));
                    return true;
                }
                case "finish" -> {
                    if (SmashPlugin.getPlugin().getSetups().get(player) == null) {
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Du hast keine Map-Erstellung gestartet.", RED)));
                        return false;
                    }
                    MapSetup mapSetup = SmashPlugin.getPlugin().getSetups().get(player);
                    if (mapSetup.countLocations() < mapSetup.getIndexSize()) {
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Nicht genug Spawn-Positionen! (" + mapSetup.countLocations() + " von " + mapSetup.getIndexSize() + ")", RED)));
                        return false;
                    }
                    Map map = mapSetup.finish();
                    if (map.write()) {
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Map '" + map.name() + "' abgelegt.", GREEN)));
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Speicher die Map mit '/config save'.", YELLOW)));
                    }
                    return true;
                }
                default ->
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("unknown-command").replaceText(builder -> builder.matchLiteral("$command").replacement(args[0]))));
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("setlocation")) {
                if (SmashPlugin.getPlugin().getSetups().get(player) == null) {
                    player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Du hast keine Map-Erstellung gestartet.", RED)));
                    return false;
                }
                MapSetup mapSetup = SmashPlugin.getPlugin().getSetups().get(player);
                if (!NumberUtils.isParsable(args[1])) {
                    sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("'" + args[1] + "' ist keine valide Zahl.", RED)));
                    return false;
                }
                int index = NumberUtils.toInt(args[1]);
                if (index < 0) {
                    player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Es sind keine Positionen unter 0 erlaubt.", RED)));
                    return false;
                }
                if (index >= mapSetup.getIndexSize()) {
                    player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Es dürfen nur " + mapSetup.getIndexSize() + " Positionen gesetzt werden.", RED)));
                    return false;
                }
                mapSetup.setSpawnLocation(index, player.getLocation());
                player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Position '" + index + "' gesetzt.", GREEN)));
            } else {
                sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("unknown-command").replaceText(builder -> builder.matchLiteral("$command").replacement(args[0]))));
                return false;
            }
        } else if (args.length == 4) {
            if (args[0].equalsIgnoreCase("start")) {
                String mapName = args[1];
                Material icon = Material.getMaterial(args[2]);
                int actual;
                if (mapName.length() > 16) {
                    actual = mapName.length();
                    player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Der Name der Map darf nicht länger als 16 Zeichen sein. ('" + mapName + "': " + actual + ")", RED)));
                    return false;
                }
                if (MapLoader.contains(mapName)) {
                    player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Map '" + mapName + "' existiert bereits.", RED)));
                    return false;
                }
                if (icon == null) {
                    sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("'" + args[3] + "' ist keine valides Item.", RED)));
                    return false;
                }
                if (icon.isAir()) {
                    sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("'" + args[3] + "' kann nicht gesetzt werden.", RED)));
                    return false;
                }
                if (SmashPlugin.getPlugin().getSetups().get(player) != null) {
                    MapSetup mapSetup = SmashPlugin.getPlugin().getSetups().get(player);
                    sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Map-Erstellung '" + mapSetup.getName() + "' läuft bereits.", YELLOW)));
                    return false;
                }
                if (!NumberUtils.isParsable(args[3])) {
                    sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("'" + args[3] + "' ist keine valide Zahl.", RED)));
                    return false;
                }
                int indexSize = NumberUtils.toInt(args[3]);
                MapSetup setup = new MapSetup(player, mapName, icon, indexSize);
                sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Map-Erstellung '" + setup.getName() + "' gestartet.", GREEN)));
            } else {
                player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("unknown-command").replaceText(builder -> builder.matchLiteral("$command").replacement(args[0]))));
                return false;
            }
        } else {
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Benutze folgende Argumente:", GRAY)));
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("- abort", GREEN)));
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("- finish", GREEN)));
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("- setlocation <index (beginnend mit 0)>", GREEN)));
            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("- start <map-name> <icon-material> <size of spawn locations>", GREEN)));
            return false;
        }
        return false;
    }
}

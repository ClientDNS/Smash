package de.clientdns.smash.commands;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.gamestate.GameState;
import de.clientdns.smash.map.Map;
import de.clientdns.smash.map.setup.MapSetup;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
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
        if (sender instanceof Player player) {
            if (!player.hasPermission("smash.setup")) {
                player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("permission-required")));
                return false;
            }
            if (args.length == 1) {
                if (!SmashPlugin.getPlugin().getGameStateManager().getGameState().equals(GameState.LOBBY)) {
                    player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Das Setup ist während des Spiels nicht möglich.", GRAY)));
                    return false;
                }
                switch (args[0].toLowerCase()) {
                    case "abort" -> {
                        if (SmashPlugin.getPlugin().getSetups().get(player) == null) {
                            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Du hast keine Map-Erstellung gestartet.", RED)));
                            return false;
                        }
                        MapSetup mapSetup = SmashPlugin.getPlugin().getSetups().get(player);
                        mapSetup.finish(true);
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Du hast die Map-Erstellung abgebrochen.", RED)));
                    }
                    case "finish" -> {
                        if (SmashPlugin.getPlugin().getSetups().get(player) == null) {
                            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Du hast keine Map-Erstellung gestartet.", RED)));
                            return false;
                        }
                        MapSetup mapSetup = SmashPlugin.getPlugin().getSetups().get(player);
                        int current = mapSetup.countLocations();
                        if (current < mapSetup.getIndexSize()) {
                            int max = mapSetup.getIndexSize();
                            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Nicht genug Spawn-Positionen! (" + current + "/" + max + ")", RED)));
                            return false;
                        }
                        Optional<Map> finishedSetup = mapSetup.finish(false); // 'false' prevents the method to abort the setup.
                        finishedSetup.ifPresent(map -> map.save(finished -> {
                            if (finished) {
                                player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Du hast die Map '" + mapSetup.getMapName() + "' erstellt.", GREEN)));
                            }
                        }));
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
                    try {
                        int index = Integer.parseInt(args[1]);
                        if (index < 0) {
                            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Es sind keine negativen Positionen erlaubt.", RED)));
                            return false;
                        }
                        if (index >= mapSetup.getIndexSize()) {
                            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Es dürfen nur " + mapSetup.getIndexSize() + " Positionen gesetzt werden.", RED)));
                            return false;
                        }
                        mapSetup.setSpawnLocation(index, player.getLocation());
                        mapSetup.update();
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Position beim Index '" + index + "' gesetzt.", GREEN)));
                    } catch (NumberFormatException e) {
                        sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Position '" + args[1] + "' ist keine Zahl.", RED)));
                    }
                } else {
                    sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("unknown-command").replaceText(builder -> builder.matchLiteral("$command").replacement(args[0]))));
                }
            } else if (args.length == 4) {
                if (args[0].equalsIgnoreCase("start")) {
                    String mapName = args[1];
                    String builderName = args[2];
                    if (mapName.length() > 16) {
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Der Name der Map darf nicht länger als 16 Zeichen sein. ('" + mapName + "')", RED)));
                        return false;
                    }
                    if (builderName.length() > 32) {
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Der Name der Map darf nicht länger als 32 Zeichen sein. ('" + builderName + "')", RED)));
                        return false;
                    }
                    if (SmashPlugin.getPlugin().getSetups().get(player) != null) {
                        MapSetup mapSetup = SmashPlugin.getPlugin().getSetups().get(player);
                        sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Deine Map-Erstellung '" + mapSetup.getMapName() + "' läuft bereits.", RED)));
                        return false;
                    }
                    try {
                        int indexSize = Integer.parseInt(args[3]);
                        MapSetup setup = new MapSetup(player, mapName, builderName, indexSize);
                        sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Map-Erstellung '" + setup.getMapName() + "' gestartet.", GREEN)));
                    } catch (NumberFormatException e) {
                        sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("'" + args[3] + "' ist keine valide Zahl.", GREEN)));
                    }
                } else {
                    player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("unknown-command").replaceText(builder -> builder.matchLiteral("$command").replacement(args[0]))));
                    return false;
                }
            }
        } else {
            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("player-required")));
            return false;
        }
        return false;
    }
}

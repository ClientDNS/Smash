package de.clientdns.smash.commands;

import de.clientdns.smash.api.SmashApi;
import de.clientdns.smash.api.config.MiniMsg;
import de.clientdns.smash.api.gamestate.GameState;
import de.clientdns.smash.api.map.Map;
import de.clientdns.smash.api.map.setup.MapSetup;
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
        if (sender.hasPermission("smash.setup")) if (args.length == 1)
            return Stream.of("create", "list", "abort", "set", "finish").filter(s -> s.startsWith(args[0])).toList();
        return List.of();
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            if (!player.hasPermission("smash.setup")) {
                sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("permission-required")));
                return false;
            }
            if (args.length == 1) {
                if (!SmashApi.getGameStateManager().getGameState().equals(GameState.LOBBY)) {
                    player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Das Setup ist während des Spiels nicht möglich.", GRAY)));
                    return false;
                }
                switch (args[0]) {
                    case "list" -> {
                        if (SmashApi.getSetups().isEmpty()) {
                            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Es laufen aktuell keine Map-Erstellungen.", RED)));
                            return false;
                        }
                        int count = 0;
                        for (MapSetup mapSetup : SmashApi.getSetups().values()) {
                            count++;
                            player.sendMessage(MiniMsg.mini("prefix").
                                    append(MiniMsg.plain("#" + count , GRAY).
                                            append(MiniMsg.plain(" | ", DARK_GRAY).
                                                    append(MiniMsg.plain(mapSetup.getPlayer().getName() + " => " + mapSetup.getMapName(), GREEN)))));
                        }
                    }
                    case "abort" -> {
                        if (SmashApi.getSetups().get(player) == null) {
                            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Du hast keine Map-Erstellung gestartet.", RED)));
                            return false;
                        }
                        MapSetup mapSetup = SmashApi.getSetups().get(player);
                        mapSetup.finish(true);
                        player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Du hast die Map-Erstellung abgebrochen.", RED)));
                    }
                    case "finish" -> {
                        if (SmashApi.getSetups().get(player) == null) {
                            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Du hast keine Map-Erstellung gestartet.", RED)));
                            return false;
                        }
                        MapSetup mapSetup = SmashApi.getSetups().get(player);
                        int current = mapSetup.countLocations();
                        if (current < mapSetup.getIndexSize()) {
                            int max = mapSetup.getIndexSize();
                            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Nicht genug Spawn-Positionen! (" + current + "/" + max + ")", RED)));
                            return false;
                        }
                        Map map = mapSetup.finish(false); // 'false' prevents the finish method to abort the setup.
                        map.save().thenAccept(finished -> {
                            if (finished) {
                                player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Du hast die Map '" + mapSetup.getMapName() + "' erstellt.", GREEN)));
                            }
                        });
                        return true;
                    }
                    default ->
                            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("unknown-command").replaceText(builder -> builder.matchLiteral("$command").replacement(args[0]))));
                }
            } else if (args.length == 2) {
                switch (args[0]) {
                    case "set" -> {
                        if (SmashApi.getSetups().get(player) == null) {
                            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Du hast keine Map-Erstellung gestartet.", RED)));
                            return false;
                        }
                        MapSetup mapSetup = SmashApi.getSetups().get(player);
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
                            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Position beim Index '" + index + "' gesetzt.", GREEN)));
                        } catch (NumberFormatException e) {
                            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Position '" + args[1] + "' ist keine Zahl.", RED)));
                        }
                    }
                    default ->
                            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("unknown-command").replaceText(builder -> builder.matchLiteral("$command").replacement(args[0]))));
                }
            } else if (args.length == 3) {
                switch (args[0]) {
                    case "create" -> {
                        String mapName = args[1];
                        if (mapName.length() > 16) {
                            player.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Der Name der Map darf nicht länger als 16 Zeichen sein. ('" + mapName + "')", RED)));
                            return false;
                        }
                        if (SmashApi.getSetups().get(player) != null) {
                            MapSetup mapSetup = SmashApi.getSetups().get(player);
                            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Deine Map-Erstellung '" + mapSetup.getMapName() + "' läuft bereits.", RED)));
                            return false;
                        }
                        try {
                            int indexSize = Integer.parseInt(args[2]);
                            MapSetup setup = new MapSetup(player, mapName, indexSize);
                            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("Map-Erstellung '" + setup.getMapName() + "' gestartet.", GREEN)));
                        } catch (NumberFormatException e) {
                            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("'" + args[2] + "' ist keine valide Zahl.", GREEN)));
                        }
                    }
                }
            } else {
                sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("/setup create <map name (max. 16 chars)>, <location amount>", RED)));
                sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.plain("/setup set <index>", RED)));
                return false;
            }
        } else {
            sender.sendMessage(MiniMsg.mini("prefix").append(MiniMsg.mini("player-required")));
            return false;
        }
        return false;
    }
}

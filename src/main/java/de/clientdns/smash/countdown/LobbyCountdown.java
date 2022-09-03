package de.clientdns.smash.countdown;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.Constants;
import de.clientdns.smash.gamestate.GameState;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicInteger;

public class LobbyCountdown {

    private static int taskId = -1;

    public static void start(Player player) {
        AtomicInteger seconds = new AtomicInteger(30);
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(SmashPlugin.getPlugin(), () -> {
            switch (seconds.get()) {
                case 30:
                    Bukkit.broadcast(Constants.prefix().append(Component.text("§7Das Spiel startet in §e30 Sekunden§8.")));
                    break;
                case 15:
                    Bukkit.broadcast(Constants.prefix().append(Component.text("§7Das Spiel startet in §e15 Sekunden§8.")));
                    break;
                case 10:
                    Bukkit.broadcast(Constants.prefix().append(Component.text("§7Das Spiel startet in §e10 Sekunden§8.")));
                    break;
                case 5: case 3: case 2:


                    //Process for loading the map



                    Bukkit.broadcast(Constants.prefix().append(Component.text("§7Das Spiel startet in §e" + seconds + " Sekunden§8.")));
                    break;
                case 1:
                    Bukkit.broadcast(Constants.prefix().append(Component.text("§7Das Spiel startet in §eeiner Sekunde§8.")));
                    break;
                case 0:
                    stop(player);
                    SmashPlugin.getGameStateManager().setCurrentState(GameState.INGAME);
                    player.setGameMode(GameMode.ADVENTURE);
                    break;
                default:
                    break;
            }
            player.setLevel(seconds.getAndDecrement());
        }, 0L, 20L);
    }

    public static void stop(Player player) {
        if (isRunning()) {
            Bukkit.getScheduler().cancelTask(taskId);
            player.setLevel(0);
        }
    }

    public static boolean isRunning() {
        return Bukkit.getScheduler().isCurrentlyRunning(taskId) && taskId != -1;
    }
}

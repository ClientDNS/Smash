package de.clientdns.smash.countdown;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.Constants;
import de.clientdns.smash.gamestate.GameState;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

public class LobbyCountdown {

    private static int taskId;
    private static int seconds;

    public static void start() {
        if (!SmashPlugin.getPlugin().getGameStateManager().getGameState().equals(GameState.LOBBY)) {
            throw new IllegalStateException("LobbyCountdown can only be started in LOBBY state");
        }
        taskId = -1;
        seconds = 15;
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(SmashPlugin.getPlugin(), () -> {
            switch (seconds) {
                case 15, 10, 5, 3, 2 ->
                        Bukkit.broadcast(Constants.prefix().append(Component.text("§7Das Spiel startet in §e" + seconds + " Sekunden§8.")));
                case 1 ->
                        Bukkit.broadcast(Constants.prefix().append(Component.text("§7Das Spiel startet in §eeiner Sekunde§8.")));
                case 0 -> {
                    stop();
                    SmashPlugin.getPlugin().getGameStateManager().setCurrentState(GameState.INGAME);
                    return;
                }
            }
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.setLevel(seconds);
                player.setExp(seconds / 15f);
            });
            seconds--;
        }, 0L, 20L);
    }

    public static void stop() {
        if (taskId == -1) {
            return;
        }
        if (Bukkit.getScheduler().isCurrentlyRunning(taskId)) {
            Bukkit.getScheduler().cancelTask(taskId);
            Bukkit.getOnlinePlayers().forEach(player -> {
                player.setLevel(0);
                player.setExp(0f);
            });
            taskId = -1;
        }
    }
}

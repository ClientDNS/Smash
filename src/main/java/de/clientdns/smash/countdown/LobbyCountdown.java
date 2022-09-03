package de.clientdns.smash.countdown;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.Constants;
import de.clientdns.smash.gamestate.GameState;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LobbyCountdown {

    private static int taskId;
    private static int seconds;

    public static void start(Player player) {
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
                    stop(player);
                    SmashPlugin.getPlugin().getGameStateManager().setCurrentState(GameState.INGAME);
                    return;
                }
            }
            player.setLevel(seconds);
            player.setExp(seconds / 15f);
            seconds--;
        }, 0L, 20L);
    }

    public static void stop(@NotNull Player player) {
        if (taskId == -1) {
            return;
        }
        if (Bukkit.getScheduler().isCurrentlyRunning(taskId)) {
            Bukkit.getScheduler().cancelTask(taskId);
            player.setLevel(0);
            player.setExp(0);
            taskId = -1;
        }
    }
}

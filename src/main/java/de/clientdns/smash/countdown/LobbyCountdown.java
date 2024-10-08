package de.clientdns.smash.countdown;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.gamestate.GameState;
import de.clientdns.smash.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import static net.kyori.adventure.text.format.NamedTextColor.GREEN;
import static net.kyori.adventure.text.format.NamedTextColor.YELLOW;

public class LobbyCountdown {

    private static int taskId = -1;
    private static int seconds;

    public static void start() {
        if (!SmashPlugin.getPlugin().getGameStateManager().is(GameState.LOBBY)) {
            throw new IllegalStateException("Lobby-Countdown can only be started in LOBBY-State.");
        }
        seconds = 15;
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(SmashPlugin.getPlugin(), () -> {
            switch (seconds) {
                case 15, 10, 5, 4, 3, 2 -> {
                    if (seconds == 5) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.getInventory().clear();
                        }
                    }
                    PlayerUtil.broadcast(MiniMsg.mini("prefix").append(MiniMsg.plain("The game starts in " + seconds + " seconds.", YELLOW)));
                }
                case 1 ->
                        PlayerUtil.broadcast(MiniMsg.mini("prefix").append(MiniMsg.plain("The game starts in " + seconds + " second.", YELLOW)));
                case 0 -> {
                    forceStop();
                    SmashPlugin.getPlugin().getGameStateManager().setCurrentState(GameState.INGAME);
                    PlayerUtil.broadcast(MiniMsg.mini("prefix").append(MiniMsg.plain("Let the game begin!", GREEN)));
                    // TODO: Teleport all players to the different spawn locations
                    return;
                }
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.setLevel(seconds);
                player.setExp(seconds / 15f);
            }
            seconds--;
        }, 0L, 20L);
    }

    public static void forceStop() {
        if (taskId != -1) {
            BukkitScheduler bukkitScheduler = Bukkit.getScheduler();
            if (bukkitScheduler.isQueued(taskId)) {
                bukkitScheduler.cancelTask(taskId);
            }
            if (bukkitScheduler.isCurrentlyRunning(taskId)) {
                bukkitScheduler.cancelTask(taskId);
            }
            taskId = -1; // (reset)
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setLevel(0);
            player.setExp(0);
        }
    }
}

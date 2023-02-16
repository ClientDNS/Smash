package de.clientdns.smash.countdown;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.gamestate.GameState;
import de.clientdns.smash.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static net.kyori.adventure.text.format.NamedTextColor.GREEN;
import static net.kyori.adventure.text.format.NamedTextColor.YELLOW;

public class LobbyCountdown {

    private static int taskId = -1;
    private static int seconds;

    public static void start() {
        if (!SmashPlugin.getPlugin().getGameStateManager().getGameState().equals(GameState.LOBBY)) {
            throw new IllegalStateException("LobbyCountdown can only be started in LOBBY state.");
        }
        seconds = 15;
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(SmashPlugin.getPlugin(), () -> {
            switch (seconds) {
                case 15, 10, 5, 4, 3, 2 -> {
                    if (seconds == 3) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.getInventory().clear();
                        }
                    }
                    PlayerUtil.broadcast(MiniMsg.mini("prefix").append(MiniMsg.plain("Das Spiel startet in " + seconds + " Sekunden.", YELLOW)));
                }
                case 1 -> PlayerUtil.broadcast(MiniMsg.mini("prefix").append(MiniMsg.plain("Das Spiel startet in einer Sekunde.", YELLOW)));
                case 0 -> {
                    PlayerUtil.broadcast(MiniMsg.mini("prefix").append(MiniMsg.plain("Das Spiel kann beginnen!", GREEN)));
                    forceStopScheduler();
                    SmashPlugin.getPlugin().getGameStateManager().setGameState(GameState.INGAME);
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

    public static void forceStopScheduler() {
        if (taskId != -1) {
            Bukkit.getScheduler().cancelTask(taskId);
            taskId = -1; // taskId will be set to -1 when scheduling fails and I guess, would set it back to it when the
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setLevel(0);
            player.setExp(0);
        }
    }
}

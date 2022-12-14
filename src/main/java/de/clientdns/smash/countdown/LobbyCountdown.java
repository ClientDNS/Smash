package de.clientdns.smash.countdown;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.api.SmashApi;
import de.clientdns.smash.api.gamestate.GameState;
import de.clientdns.smash.api.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static de.clientdns.smash.config.Value.plain;
import static net.kyori.adventure.text.format.NamedTextColor.YELLOW;

public class LobbyCountdown {

    private static int taskId;
    private static int seconds;

    public static void start() {
        if (!SmashApi.gameStateManager().lobbyState()) {
            throw new IllegalStateException("LobbyCountdown can only be started in LOBBY state, tried to start in " + SmashApi.gameStateManager().gameState());
        }
        seconds = 15;
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(SmashPlugin.plugin(), () -> {
            switch (seconds) {
                case 15, 10, 5, 4, 3, 2 ->
                        PlayerUtil.broadcast(plain("Das Spiel startet in " + seconds + " Sekunden.", YELLOW));
                case 1 -> PlayerUtil.broadcast(plain("Das Spiel startet in einer Sekunde.", YELLOW));
                case 0 -> {
                    forceStop();
                    SmashApi.gameStateManager().gameState(GameState.INGAME);
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
            Bukkit.getScheduler().cancelTask(taskId);
            taskId = -1;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setLevel(0);
            player.setExp(0);
        }
    }
}

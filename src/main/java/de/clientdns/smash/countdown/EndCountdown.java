package de.clientdns.smash.countdown;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.gamestate.GameState;
import de.clientdns.smash.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static net.kyori.adventure.text.format.NamedTextColor.YELLOW;

public class EndCountdown {

    private static int seconds;

    public static void start() {
        if (!SmashPlugin.getPlugin().getGameStateManager().getGameState().equals(GameState.END)) {
            return;
        }
        seconds = 15;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SmashPlugin.getPlugin(), () -> {
            switch (seconds) {
                case 15, 10, 5, 4, 3, 2 ->
                        PlayerUtil.broadcast(MiniMsg.plain("Der Server startet in " + seconds + " Sekunden neu.", YELLOW));
                case 1 -> PlayerUtil.broadcast(MiniMsg.plain("Der Server startet in einer Sekunde neu...", YELLOW));
                case 0 -> {
                    Bukkit.shutdown();
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
}

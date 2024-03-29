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
        if (!SmashPlugin.getPlugin().getGameStateManager().is(GameState.END)) {
            throw new IllegalStateException("Ending-Countdown can only be started in END-State.");
        }
        seconds = 15;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SmashPlugin.getPlugin(), () -> {
            switch (seconds) {
                case 15, 10, 5, 4, 3, 2 ->
                        PlayerUtil.broadcast(MiniMsg.mini("prefix").append(MiniMsg.plain("The server restarts in " + seconds + " seconds.", YELLOW)));
                case 1 ->
                        PlayerUtil.broadcast(MiniMsg.mini("prefix").append(MiniMsg.plain("The server restarts in " + seconds + " second.", YELLOW)));
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

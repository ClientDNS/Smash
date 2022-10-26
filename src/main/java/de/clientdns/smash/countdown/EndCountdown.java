package de.clientdns.smash.countdown;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.util.PlayerUtil;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static net.kyori.adventure.text.Component.text;

public class EndCountdown {

    private static int seconds;

    public static void start() {
        if (!SmashPlugin.getPlugin().getGameStateManager().isEndState()) {
            throw new IllegalStateException("EndCountdown can only be started in END state, tried to start in " + SmashPlugin.getPlugin().getGameStateManager().getGameState());
        }
        seconds = 15;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SmashPlugin.getPlugin(), () -> {
            switch (seconds) {
                case 15, 10, 5, 4, 3, 2 ->
                        PlayerUtil.broadcast(text("Der Server startet in " + seconds + " Sekunden neu.", NamedTextColor.YELLOW));
                case 1 ->
                        PlayerUtil.broadcast(text("Der Server startet in einer Sekunde neu...", NamedTextColor.YELLOW));
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
package de.clientdns.smash.countdown;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.util.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public class EndCountdown {

    private static int seconds;

    public static void start() {
        if (!SmashPlugin.plugin().gameStateManager().isEndState()) {
            throw new IllegalStateException("EndCountdown can only be started in END state, tried to start in " + SmashPlugin.plugin().gameStateManager().getGameState());
        }
        seconds = 15;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SmashPlugin.plugin(), () -> {
            switch (seconds) {
                case 15, 10, 5, 4, 3, 2 ->
                        PlayerUtil.broadcast(text("Der Server startet in " + seconds + " Sekunden neu.", YELLOW));
                case 1 -> PlayerUtil.broadcast(text("Der Server startet in einer Sekunde neu...", YELLOW));
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

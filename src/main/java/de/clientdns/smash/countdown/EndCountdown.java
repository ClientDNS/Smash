package de.clientdns.smash.countdown;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.Constants;
import de.clientdns.smash.gamestate.GameState;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

public class EndCountdown {

    private static int seconds;

    public static void start() {
        if (!SmashPlugin.getPlugin().getGameStateManager().getGameState().equals(GameState.END)) {
            throw new IllegalStateException("EndCountdown can only be started in END state");
        }
        seconds = 15;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SmashPlugin.getPlugin(), () -> {
            switch (seconds) {
                case 15, 10, 5, 3, 2 ->
                        Bukkit.broadcast(Constants.prefix().append(Component.text("§7Der Server startet in §e" + seconds + " Sekunden §7neu§8.")));
                case 1 ->
                        Bukkit.broadcast(Constants.prefix().append(Component.text("§7Der Server startet in §eeiner Sekunde §7neu§8..")));
                case 0 -> {
                    Bukkit.shutdown();
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
}

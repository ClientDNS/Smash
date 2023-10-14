package de.clientdns.smash.timer;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.util.FormatUtil;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class GameTimer {

    private final BukkitScheduler scheduler;
    private int seconds = 0;
    private BukkitTask task;

    public GameTimer() {
        this.scheduler = Bukkit.getScheduler();
    }

    public void start() {
        task = scheduler.runTaskTimerAsynchronously(SmashPlugin.getPlugin(), () -> {
            Bukkit.broadcast(MiniMsg.plain(FormatUtil.formatSeconds(seconds), NamedTextColor.GREEN));
            seconds++;
        }, 0, 20);
    }

    public void stop() {
        if (!isRunning()) {
            task.cancel();
        }
    }

    public boolean isRunning() {
        if (task == null) {
            return false;
        }
        return !task.isCancelled();
    }

    public int getSeconds() {
        return seconds;
    }
}

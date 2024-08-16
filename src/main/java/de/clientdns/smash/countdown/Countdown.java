package de.clientdns.smash.countdown;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

public class Countdown {

    private BukkitScheduler bs;
    private SmashPlugin pl;
    private int delay;
    private int period;
    private int taskId;

    public Countdown(int delay, int period) {
        this.bs = Bukkit.getScheduler();
        this.pl = SmashPlugin.getPlugin();
        this.delay = delay;
        this.period = period;
        this.taskId = -1;
    }

    public void refresh() {
        this.bs = Bukkit.getScheduler();
    }

    public void start() {
        refresh();

        this.bs.runTaskTimer(this.pl, new Runnable() {
            @Override
            public void run() {

            }
        }, this.delay, this.period);
    }

    public void stop() {
        refresh();
        bs.getPendingTasks().remove(taskId);
        bs.cancelTask(taskId);
    }

    public boolean running() {
        refresh();
        boolean running = bs.isCurrentlyRunning(taskId);
        boolean queued = bs.isQueued(taskId);
        return running || queued;
    }
}

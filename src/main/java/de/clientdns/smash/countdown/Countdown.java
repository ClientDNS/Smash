package de.clientdns.smash.countdown;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

public class Countdown {

    private BukkitScheduler bs;
    private int delay;
    private int period;
    private int taskId;
    private boolean async;

    public Countdown(int period, boolean async) {
        this.bs = Bukkit.getScheduler();
        this.delay = 0;
        this.period = period;
        this.taskId = -1;
        this.async = async;
    }

    public Countdown(int delay, int period, boolean async) {
        this.delay = delay;
        this.period = period;
        this.async = async;
    }

    public void refresh() {
        this.bs = Bukkit.getScheduler();
    }

    public void start() {
        refresh();
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

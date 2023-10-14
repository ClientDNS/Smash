package de.clientdns.smash.scoreboard;

import de.clientdns.smash.SmashPlugin;
import de.clientdns.smash.config.MiniMsg;
import de.clientdns.smash.timer.GameTimer;
import de.clientdns.smash.util.FormatUtil;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;

public class PlayerScoreboard {

    private final Player player;
    private final Scoreboard scoreboard;
    private final GameTimer gameTimer;
    private Objective objective;
    private BukkitTask task;

    public PlayerScoreboard(@NotNull Player player) {
        this.player = player;
        this.scoreboard = player.getScoreboard();
        this.gameTimer = SmashPlugin.getPlugin().getGameTimer();
        this.objective = scoreboard.getObjective("dummy");
    }

    public void set() {
        if (objective == null) {
            objective = scoreboard.registerNewObjective("abc", Criteria.AIR, MiniMsg.mini("prefix").
                    append(MiniMsg.plain("Overview", NamedTextColor.GREEN)));
            task = Bukkit.getScheduler().runTaskTimer(SmashPlugin.getPlugin(), this::update, 0, 10);
        }
    }

    public void update() {
        objective.getScore(" ").setScore(7);
        objective.getScore("Game time:").setScore(6);

        int seconds;
        boolean gameTimerRunning = gameTimer.isRunning();
        seconds = SmashPlugin.getPlugin().getGameTimer().getSeconds();

        objective.getScore("- " + (gameTimerRunning ? FormatUtil.formatSeconds(seconds) : "/")).setScore(5);

        player.setScoreboard(scoreboard);
    }

    public Player getPlayer() {
        return player;
    }

    public void stopUpdating() {
        if (task != null && !task.isCancelled()) {
            task.cancel();
        }
    }
}

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
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class PlayerScoreboard {

    private final Player player;
    private final Scoreboard scoreboard;
    private final GameTimer gameTimer;
    private final Objective objective;
    private final Map<String, Integer> scores;
    private BukkitTask task;

    public PlayerScoreboard(@NotNull Player player) {
        this.player = player;
        this.scoreboard = player.getScoreboard();
        this.gameTimer = SmashPlugin.getPlugin().getGameTimer();
        if (scoreboard.getObjective("abc_" + player.getName()) == null)
            this.objective = scoreboard.registerNewObjective("abc_" + player.getName(), Criteria.DUMMY, MiniMsg.mini("prefix").append(MiniMsg.plain("Overview", NamedTextColor.GREEN)));
        else
            this.objective = scoreboard.getObjective("abc_" + player.getName());
        this.scores = new HashMap<>();
    }

    public void set() {
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        task = Bukkit.getScheduler().runTaskTimer(SmashPlugin.getPlugin(), this::update, 0, 10);

        player.setScoreboard(scoreboard);
    }

    public void update() {

        this.scores.put(" ", 7);
        this.scores.put("Game time", 6);

        boolean gameTimerRunning = gameTimer.isRunning();
        int seconds = SmashPlugin.getPlugin().getGameTimer().getSeconds();
        this.scores.replace("=> " + (gameTimerRunning ? FormatUtil.formatSeconds(seconds) : "/"), 5);

        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            this.objective.getScore(entry.getKey()).setScore(entry.getValue());
        }
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

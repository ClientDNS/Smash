package de.clientdns.smash.api.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class FormatUtil {

    @Contract(pure = true)
    public static @NotNull String percent(double value, double max) {
        double percent = (value * 100D) / max;
        return (int) percent + "%";
    }

    public static @NotNull String formatSeconds(long secs) {
        if (secs <= 0) {
            return "0s";
        }

        long second = secs;
        long minute = second / 60;
        long hour = minute / 60;
        long day = hour / 24;
        second = second % 60;

        StringBuilder sb = new StringBuilder();
        if (day != 0) {
            sb.append(day).append("d ");
        }
        if (hour != 0) {
            sb.append(hour).append("h ");
        }
        if (minute != 0) {
            sb.append(minute).append("m ");
        }
        if (second != 0) {
            sb.append(second).append("s ");
        }
        return sb.toString().trim();
    }
}

package de.clientdns.smash.util;

import org.jetbrains.annotations.NotNull;

public class FormatUtil {

    public static @NotNull String percent(double value, double max) {
        double percent = (value * 100D) / max;
        return percent + "%";
    }

    /**
     * Formats the given seconds into a human-readable string.
     *
     * @param seconds The given seconds to format
     * @return The human-readable string with days, hours, minutes and seconds.
     */
    public static @NotNull String formatSeconds(long seconds) {
        if (seconds <= 0) {
            return "0s";
        }

        long second = seconds;
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

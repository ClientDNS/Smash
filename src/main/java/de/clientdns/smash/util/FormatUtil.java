package de.clientdns.smash.util;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class FormatUtil {

    private static final String[] SIZE_UNITS = {"bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};

    @Contract(pure = true)
    public static @NotNull String percent(double value, double max) {
        double percent = (value * 100D) / max;
        return (int) percent + "%";
    }

    public static @NotNull String formatBytes(long bytes) {
        if (bytes <= 0)
            return "0 bytes";
        int sizeIndex = (int) (Math.log(bytes) / Math.log(1024));
        return String.format("%.1f", bytes / Math.pow(1024, sizeIndex)) + " " + SIZE_UNITS[sizeIndex];
    }

    public static @NotNull String formatSeconds(long seconds) {
        if (seconds <= 0)
            return "0s";

        long milliSecond = seconds / 1000;
        long second = seconds;
        long minute = second / 60;
        long hour = minute / 60;
        long day = hour / 24;
        second = second % 60;

        StringBuilder sb = new StringBuilder();
        if (day != 0)
            sb.append(day).append("d ");
        if (hour != 0)
            sb.append(hour).append("h ");
        if (minute != 0)
            sb.append(minute).append("m ");
        if (second != 0)
            sb.append(second).append("s ");
        if (milliSecond != 0)
            sb.append(milliSecond).append("ms ");


        return sb.toString().trim();
    }
}

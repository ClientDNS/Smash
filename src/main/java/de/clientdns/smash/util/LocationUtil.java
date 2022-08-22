package de.clientdns.smash.util;

import de.clientdns.smash.SmashPlugin;
import org.bukkit.Location;

public class LocationUtil {

    public static void setLocation(String name, Location location) {
        SmashPlugin.getSmashConfig().setLocation(name, location);
    }
}

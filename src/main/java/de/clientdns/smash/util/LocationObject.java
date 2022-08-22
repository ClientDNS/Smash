package de.clientdns.smash.util;

import org.bukkit.Location;

public class LocationObject<K extends String> {


    private final K key;
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;


    public LocationObject(K key, double x, double y, double z, float yaw, float pitch) {
        this.key = key;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Location addToLocation(Location location) {
        Location newLocation = location.clone().add(getX(), getY(), getZ());
        newLocation.setYaw(getYaw());
        newLocation.setPitch(getPitch());
        return newLocation;
    }

    public K getKey() {
        return key;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    @Override
    public String toString() {
        return "Location{" +
                "key='" + key + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", yaw=" + yaw +
                ", pitch=" + pitch +
                '}';
    }

}

package de.clientdns.smash.util;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Bukkit;

import java.util.UUID;

public class ProfileUtil {

    private UUID uuid;
    private String name;

    public ProfileUtil(UUID uuid) {
        this.uuid = uuid;
    }

    public ProfileUtil(String name) {
        this.name = name;
    }

    public ProfileUtil(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public PlayerProfile getProfile() {
        return Bukkit.createProfile(uuid, name);
    }
}

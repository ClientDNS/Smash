package de.clientdns.smash.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SkinUtil {

    private Player player;

    public SkinUtil(UUID uuid) {
        this.player = Bukkit.getPlayer(uuid);
    }
}

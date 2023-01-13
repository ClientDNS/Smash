package de.clientdns.smash.api.util;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@ApiStatus.Experimental
public class SkinFetcher {

    private static final Map<String, String> fetches = new HashMap<>();

    @ApiStatus.Experimental
    public static @NotNull CompletableFuture<String> uuid(String playerName) {
        if (fetches.containsKey(playerName)) {
            return CompletableFuture.supplyAsync(() -> fetches.get(playerName));
        }
        try {
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + playerName);
            URLConnection connection = url.openConnection();
            connection.connect();
            try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
                return CompletableFuture.supplyAsync(() -> {
                    JsonElement element = JsonParser.parseReader(reader);
                    JsonObject object = element.getAsJsonObject();
                    String uuid = object.get("id").getAsString();
                    if (uuid != null) {
                        fetches.put(playerName, uuid);
                        return uuid;
                    } else {
                        return null;
                    }
                });
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not fetch uuid", e);
        }
    }

    @ApiStatus.Experimental
    public static @Nullable PlayerProfile profileFromUniqueId(String uuid) {
        Player player = Bukkit.getPlayer(UUID.fromString(uuid));
        return player == null ? null : player.getPlayerProfile();
    }

    @ApiStatus.Experimental
    public static @Nullable PlayerProfile profileFromName(String name) {
        Player player = Bukkit.getPlayer(name);
        return player == null ? null : player.getPlayerProfile();
    }

    public static Map<String, String> getFetches() {
        return fetches;
    }
}

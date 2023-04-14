package de.clientdns.smash.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.clientdns.smash.SmashPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UUIDUtil {

    private static final Map<String, String> savedIds = new HashMap<>();

    public static @NotNull Optional<String> uuid(String playerName) {
        if (savedIds.containsKey(playerName)) {
            return Optional.of(savedIds.get(playerName));
        }
        try {
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + playerName);
            URLConnection connection = url.openConnection();
            connection.setAllowUserInteraction(false);
            connection.setDoInput(false);
            connection.setConnectTimeout(1500);
            connection.setReadTimeout(2000);
            connection.connect();
            String uuid;
            try (InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream())) {
                JsonElement element = JsonParser.parseReader(inputStreamReader);
                JsonObject object = element.getAsJsonObject();
                uuid = object.get("id").getAsString();
                savedIds.put(playerName, uuid);
                SmashPlugin.getPlugin().getLogger().info("Cached uuid for player " + playerName + "(" + uuid + ")");
            }
            return Optional.of(uuid);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return Optional.empty();
    }
}

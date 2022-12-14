package de.clientdns.smash.api.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class SkinFetcher {

    private static final Map<String, String> fetches = new HashMap<>();

    @SneakyThrows
    public static @Nullable String uuidFromName(String name) {
        if (!fetches.containsKey(name)) {
            URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + name);
            URLConnection connection = url.openConnection();
            connection.setAllowUserInteraction(false);
            connection.setDefaultUseCaches(false);
            connection.connect();
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            JsonElement je = JsonParser.parseReader(reader); //from gson
            JsonObject jo = je.getAsJsonObject();
            String id = jo.get("id").getAsString();
            if (id != null)
                fetches.put(name, id);
        }
        return fetches.get(name);
    }
}

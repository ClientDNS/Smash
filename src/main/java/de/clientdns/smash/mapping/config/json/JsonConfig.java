package de.clientdns.smash.mapping.config.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.clientdns.smash.mapping.config.json.serializer.ItemStackSerializer;
import de.clientdns.smash.mapping.config.json.serializer.LocationSerializer;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.function.Consumer;

public class JsonConfig implements Config {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Location.class, new LocationSerializer()).registerTypeAdapter(ItemStack.class, new ItemStackSerializer()).create();

    private File file;
    private JsonObject jsonObject;

    public JsonConfig(final @NotNull File file) {
        this(file, null, true);
    }

    public JsonConfig(final @NotNull File file, final @Nullable Consumer<Config> initial) {
        this(file, initial, true);
    }

    public JsonConfig(final @NotNull File file, final @Nullable Consumer<Config> initial, final boolean load) {
        this.file = file;
        this.jsonObject = new JsonObject();
        try {
            file.getParentFile().mkdirs();
            if (!file.createNewFile()) {
                if (load) this.load();
            } else {
                if (initial != null) {
                    initial.accept(this);
                    this.save();
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void set(final @NotNull String key, final @NotNull Object object) {
        this.jsonObject.add(key, GSON.toJsonTree(object, object.getClass()));
    }

    @Override
    public <T> T get(@NotNull String key, @NotNull Class<T> clazz) {
        return GSON.fromJson(this.jsonObject.get(key), clazz);
    }

    @Override
    public <T> T get(final @NotNull String key, final @NotNull Type type) {
        return GSON.fromJson(this.jsonObject.get(key), type);
    }

    @Override
    public @NotNull Config put(final @NotNull Map<String, Object> objectMap) {
        objectMap.forEach(this::set);
        return this;
    }

    @Override
    public void save() {
        try (final var fileWriter = new FileWriter(this.file)) {
            fileWriter.write(GSON.toJson(this.jsonObject));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public @NotNull File getFile() {
        return this.file;
    }

    @Override
    public @NotNull Config setFile(final @NotNull File file) {
        this.file = file;
        return this;
    }

    @Override
    public void load() {
        try (final var fileReader = new FileReader(file)) {
            this.jsonObject = JsonParser.parseReader(fileReader).getAsJsonObject();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}

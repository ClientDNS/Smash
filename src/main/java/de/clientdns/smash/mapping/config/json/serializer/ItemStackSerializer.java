package de.clientdns.smash.mapping.config.json.serializer;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.Map;

public class ItemStackSerializer  implements JsonSerializer<ItemStack>, JsonDeserializer<ItemStack> {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Serializes a {@link ItemStack} to a {@link JsonElement}.
     *
     * @param itemStack The {@link ItemStack} to serialize.
     * @param typeOfSrc The type of the {@link ItemStack}.
     * @param context   The {@link JsonSerializationContext} to use.
     * @return The serialized {@link JsonElement}.
     */
    @Override
    public JsonElement serialize(@NotNull ItemStack itemStack, Type typeOfSrc, JsonSerializationContext context) {
        return this.gson.toJsonTree(itemStack.serialize());
    }

    /**
     * Deserializes a {@link ItemStack} from a {@link JsonElement}.
     *
     * @param json    The {@link JsonElement} to deserialize.
     * @param typeOfT The type of the {@link ItemStack}.
     * @param context The {@link JsonDeserializationContext} to use.
     * @return The deserialized {@link ItemStack}.
     * @throws JsonParseException If the {@link JsonElement} cannot be deserialized.
     */
    @Override
    public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return ItemStack.deserialize(this.gson.fromJson(json, TypeToken.getParameterized(Map.class, String.class, Object.class).getType()));
    }
}

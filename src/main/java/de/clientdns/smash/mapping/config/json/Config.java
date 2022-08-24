package de.clientdns.smash.mapping.config.json;


import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public interface Config {


    /**
     * Sets the object to the specified key
     *
     * @param key    the key
     * @param object the object
     */
    void set(final String key, final Object object);

    /**
     * Gets the object from the specified key
     *
     * @param key   the key
     * @param clazz the class of the object
     * @return the object from the key
     */
    <T> T get(final String key, final Class<T> clazz);

    /**
     * Gets the object from the specified key
     *
     * @param key  the key
     * @param type the type of the object
     * @return the object from the key
     */
    <T> T get(final String key, final Type type);

    /**
     * Gets the object from the specified key
     *
     * @param key the key of the object
     * @return the object of the key as String
     */
    default String getString(final String key) {
        return this.get(key, String.class);
    }

    /**
     * Gets the object from the specified key
     *
     * @param key the key of the object
     * @return the object of the key as Integer
     */
    default int getInt(final String key) {
        return this.get(key, Integer.class);
    }

    /**
     * Gets the object from the specified key
     *
     * @param key the key of the object
     * @return the object of the key as Boolean
     */
    default boolean getBoolean(final String key) {
        return this.get(key, Boolean.class);
    }

    /**
     * Gets the object from the specified key
     *
     * @param key the key of the object
     * @return the object of the key as Double
     */
    default double getDouble(final String key) {
        return this.get(key, Double.class);
    }

    /**
     * Gets the object from the specified key
     *
     * @param key the key of the object
     * @return the object of the key as Long
     */
    default long getLong(final String key) {
        return this.get(key, Long.class);
    }

    /**
     * Gets the object from the specified key
     *
     * @param key the key of the object
     * @return the object of the key as String List
     */
    default List<String> getStringList(final String key) {
        return this.get(key, TypeToken.getParameterized(List.class, String.class).getType());
    }

    /**
     * Puts all objects in the config
     *
     * @param objectMap the map to put
     * @return the instance of the Config
     */
    Config put(final Map<String, Object> objectMap);

    /**
     * Saves the config in the file
     */
    void save();

    /**
     * Gets the file object
     *
     * @return the file
     */
    File getFile();

    /**
     * Sets the file object
     *
     * @param file the file to set
     * @return the instance of the Config
     */
    Config setFile(final File file);

    /**
     * Load the Config from the file
     */
    void load();
}
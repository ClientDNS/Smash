package de.clientdns.smash.util;

import java.util.List;
import java.util.Map;

public class ExtendedMap<K, V> {

    private List<K> keys;
    private List<V> values;


    public ExtendedMap(List<K> keys, List<V> values) {
        this.keys = keys;
        this.values = values;
    }

    public Map toMap(int index) {
        return Map.of(keys.get(index), values.get(index));
    }

    //Gets a specific value
    public List<V> getSpecificValue(int index) {
        return List.of(values.get(index));
    }

    public List<K> getSpecificKey(int index) {
        return List.of(keys.get(index));
    }

    public List<K> getKeys() {
        return keys;
    }
    public List<V> getValues() {
        return values;
    }

    public void setKeys(List<K> keys) {
        this.keys = keys;
    }

    public void setValues(List<V> values) {
        this.values = values;
    }

    public void addKey(K key) {
        keys.add(key);
    }

    public void addValue(V value) {
        values.add(value);
    }
}

package de.clientdns.smash.mapping.config;

public class MapConfig {

    private final String builder;
    private final String name;
    private final String description;

    public MapConfig(String builder, String name, String description) {
        this.builder = builder;
        this.name = name;
        this.description = description;
    }

    public String getBuilder() {
        return builder;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}


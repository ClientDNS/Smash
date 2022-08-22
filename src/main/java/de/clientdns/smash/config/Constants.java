package de.clientdns.smash.config;

import de.clientdns.smash.SmashPlugin;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Constants {

    @Contract(" -> new")
    public static @NotNull Component prefix() {
        return Component.text(SmashPlugin.getSmashConfig().getString("config.messages.prefix").orElse("§8[§6Smash§8] §r"));
    }
}

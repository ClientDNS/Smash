package de.clientdns.smash.config;

import de.clientdns.smash.SmashPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Constants {

    @Contract(" -> new")
    public static @NotNull Component prefix() {
        return Component.text(SmashPlugin.getPlugin().getSmashConfig().getString("config.messages.prefix").orElse("§7[§6Smash§7] "));
    }

    @Contract(" -> new")
    public static @NotNull Component permissionRequired() {
        return Component.text(SmashPlugin.getPlugin().getSmashConfig().getString("config.messages.permission-required").orElse("Du hast keine Berechtigung, dies zu tun.")).color(NamedTextColor.RED);
    }

    @Contract(" -> new")
    public static @NotNull Component playerRequired() {
        return Component.text(SmashPlugin.getPlugin().getSmashConfig().getString("config.messages.player-required").orElse("Du musst ein Spieler sein, um dies zu tun.")).color(NamedTextColor.RED);
    }

    public static int minPlayers() {
        return SmashPlugin.getPlugin().getSmashConfig().getInt("config.min-players").orElse(2);
    }
}

package de.clientdns.smash.config;

import de.clientdns.smash.SmashPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Constants {

    private static final String prefix = SmashPlugin.getPlugin().getSmashConfig().getString("config.messages.prefix");
    private static final String permissionRequired = SmashPlugin.getPlugin().getSmashConfig().getString("config.messages.permission-required");
    private static final String playerRequired = SmashPlugin.getPlugin().getSmashConfig().getString("config.messages.player-required");
    private static final String playerNotFound = SmashPlugin.getPlugin().getSmashConfig().getString("config.messages.player-not-found");
    private static final String playerNotOnline = SmashPlugin.getPlugin().getSmashConfig().getString("config.messages.player-not-online");

    @Contract(" -> new")
    public static @NotNull Component prefix() {
        return MiniMessage.miniMessage().deserialize(prefix);
    }

    @Contract(" -> new")
    public static @NotNull Component permissionRequired() {
        return MiniMessage.miniMessage().deserialize(permissionRequired);
    }

    @Contract(" -> new")
    public static @NotNull Component playerRequired() {
        return MiniMessage.miniMessage().deserialize(playerRequired);
    }

    @Contract(" -> new")
    public static @NotNull Component playerNotFound() {
        return MiniMessage.miniMessage().deserialize(playerNotFound);
    }

    @Contract(" -> new")
    public static @NotNull Component playerNotOnline() {
        return MiniMessage.miniMessage().deserialize(playerNotOnline);
    }

    public static int minPlayers() {
        return SmashPlugin.getPlugin().getSmashConfig().getInt("config.min-players");
    }
}

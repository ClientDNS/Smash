package de.clientdns.smash.config;

import de.clientdns.smash.SmashPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

public class Value {

    private final KeyValue<String> prefix = new KeyValue<>("prefix", "<gold>Smash</gold> <dark_gray>|</dark_gray> ");
    private final KeyValue<String> unknown_command = new KeyValue<>("unknown-command", "<red>Unbekannter Befehl. ($command)</red>");
    private final KeyValue<String> permission_required = new KeyValue<>("permission-required", "<red>Du hast keine Berechtigung, dies zu tun.</red>");
    private final KeyValue<String> player_required = new KeyValue<>("player-required", "<red>Du musst ein Spieler sein, um dies zu tun.</red>");
    private final KeyValue<String> player_not_found = new KeyValue<>("player-not-found", "<red>Der Spieler wurde nicht gefunden.</red>");
    private final KeyValue<String> cannot_switch_gamemode = new KeyValue<>("cannot-switch-gamemode", "<red>Du kannst deinen Spielmodus nicht ändern, während du in einem Spiel bist.</red>");
    private final KeyValue<String> config_reloaded = new KeyValue<>("config-reloaded", "<green>Die Config wurde neu geladen.</green>");
    private final KeyValue<Integer> min_players = new KeyValue<>("min-players", 2);

    public static Component get(String path) {
        return MiniMessage.miniMessage().deserializeOrNull(get(path, "<red>Unknown string.</red>"));
    }

    public static @NotNull Component plain(String message, TextColor color) {
        return MiniMessage.miniMessage().deserializeOrNull(message).color(color);
    }

    @NotNull
    public static String get(String path, String def) {
        return SmashPlugin.plugin().configuration().get(path, def);
    }

    public static int get(String path, int def) {
        return SmashPlugin.plugin().configuration().get(path, def);
    }

    public static long get(String path, long def) {
        return SmashPlugin.plugin().configuration().get(path, def);
    }

    public static double get(String path, double def) {
        return SmashPlugin.plugin().configuration().get(path, def);
    }

    public static boolean get(String path, boolean def) {
        return SmashPlugin.plugin().configuration().get(path, def);
    }
}

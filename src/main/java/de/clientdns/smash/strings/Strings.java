package de.clientdns.smash.strings;

import de.clientdns.smash.config.MiniMsg;
import net.kyori.adventure.text.Component;

import static net.kyori.adventure.text.format.NamedTextColor.RED;

public class Strings {

    public static Component CHARACTERS_SELECTION_NAME = MiniMsg.plain("Characters", RED);
    public static Component PERMISSION_REQUIRED = MiniMsg.plain("You have no permission to do that.", RED);
    public static Component ONLY_PLAYERS = MiniMsg.plain("You have to be a player to do that.", RED);
    public static Component UNKNOWN_COMMAND = MiniMsg.plain("Unknown command. ($command)", RED);
}

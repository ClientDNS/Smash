package de.clientdns.smash.gui;

import de.clientdns.smash.util.ItemStackUtil;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class CharacterGui {

    public static void open(@NotNull Player player) {
        player.openInventory(CharacterGui.create());
    }

    private static @NotNull Inventory create() {
        Inventory inventory = Bukkit.createInventory(null, 27, MiniMessage.miniMessage().deserialize("<gold>Charaktere</gold>"));

        // Mario - Slot 1
        inventory.setItem(1, new ItemStackUtil().name("<gold>Mario</gold>").loreLines(" ", "<gray>Auswählen</gray>", " ").material(Material.PLAYER_HEAD).build());

        // Donkey Kong - Slot 11
        inventory.setItem(11, new ItemStackUtil().name("<gold>Donkey Kong</gold>").loreLines(" ", "<gray>Auswählen</gray>", " ").material(Material.PLAYER_HEAD).build());

        // Flash - Slot 3
        inventory.setItem(3, new ItemStackUtil().name("<gold>Flash</gold>").loreLines("", "<gray>Auswählen</gray>", " ").material(Material.PLAYER_HEAD).build());

        // Pikachu - Slot 5
        inventory.setItem(5, new ItemStackUtil().name("<gold>Pikachu</gold>").loreLines(" ", "<gray>Auswählen</gray>", " ").material(Material.PLAYER_HEAD).build());

        // Superman - Slot 15
        inventory.setItem(15, new ItemStackUtil().name("<gold>Superman</gold>").loreLines(" ", "<gray>Auswählen</gray>", " ").material(Material.PLAYER_HEAD).build());

        // Link - Slot 7
        inventory.setItem(7, new ItemStackUtil().name("<gold>Link</gold>").loreLines(" ", "<gray>Auswählen</gray>", " ").material(Material.PLAYER_HEAD).build());

        // Explanation - Slot 22
        inventory.setItem(22, new ItemStackUtil().loreLines("<gray>Jeder Character verfügt über andere Fähigkeiten, wähle bedacht.</gray>", " ").material(Material.OAK_SIGN).build());

        return inventory;
    }
}

package de.clientdns.smash.gui;

import de.clientdns.smash.util.ItemStackUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CharacterGui {

    public static final ItemStack MARIO = new ItemStackUtil().name(Component.text("Mario").color(NamedTextColor.GOLD)).material(Material.PLAYER_HEAD).build();
    public static final ItemStack DONKEY_KONG = new ItemStackUtil().name(Component.text("Donkey Kong").color(NamedTextColor.DARK_RED)).material(Material.PLAYER_HEAD).build();
    public static final ItemStack FLASH = new ItemStackUtil().name(Component.text("Flash", NamedTextColor.GOLD)).material(Material.PLAYER_HEAD).build();
    public static final ItemStack PIKACHU = new ItemStackUtil().name(Component.text("Pikachu", NamedTextColor.GOLD)).material(Material.PLAYER_HEAD).build();
    public static final ItemStack SUPERMAN = new ItemStackUtil().name(Component.text("Superman", NamedTextColor.GOLD)).material(Material.PLAYER_HEAD).build();
    public static final ItemStack LINK = new ItemStackUtil().name(Component.text("Link", NamedTextColor.DARK_GREEN)).material(Material.PLAYER_HEAD).build();
    private static final ItemStack EXPLANATION = new ItemStackUtil().name(Component.text("Jeder Character verfügt über andere Fähigkeiten, wähle bedacht.", NamedTextColor.GRAY)).material(Material.OAK_WALL_SIGN).build();

    public static void open(@NotNull Player player) {
        player.openInventory(CharacterGui.create());
    }

    private static @NotNull Inventory create() {
        Inventory inventory = Bukkit.createInventory(null, 27, Component.text("Charaktere", NamedTextColor.GOLD));

        // Mario - Slot 1
        inventory.setItem(1, MARIO);

        // Donkey Kong - Slot 11
        inventory.setItem(11, DONKEY_KONG);

        // Flash - Slot 3
        inventory.setItem(3, FLASH);

        // Pikachu - Slot 5
        inventory.setItem(5, PIKACHU);

        // Superman - Slot 15
        inventory.setItem(15, SUPERMAN);

        // Link - Slot 7
        inventory.setItem(7, LINK);

        // Explanation - Slot 22
        inventory.setItem(22, EXPLANATION);

        return inventory;
    }
}

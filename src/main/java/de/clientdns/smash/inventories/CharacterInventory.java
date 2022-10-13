package de.clientdns.smash.inventories;

import de.clientdns.smash.character.enums.Character;
import de.clientdns.smash.util.ItemStackUtil;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import static net.kyori.adventure.text.Component.empty;
import static net.kyori.adventure.text.Component.text;

public class CharacterInventory {

    public static final ItemStack MARIO = new ItemStackUtil(Material.PLAYER_HEAD, 1, Character.MARIO.getName()).build();
    public static final ItemStack DONKEY_KONG = new ItemStackUtil(Material.PLAYER_HEAD, 1, Character.DONKEY_KONG.getName()).build();
    public static final ItemStack FLASH = new ItemStackUtil(Material.PLAYER_HEAD, 1, Character.FLASH.getName()).build();
    public static final ItemStack PIKACHU = new ItemStackUtil(Material.PLAYER_HEAD, 1, Character.PIKACHU.getName()).build();
    public static final ItemStack SUPERMAN = new ItemStackUtil(Material.PLAYER_HEAD, 1, Character.SUPERMAN.getName()).build();
    public static final ItemStack LINK = new ItemStackUtil(Material.PLAYER_HEAD, 1, Character.LINK.getName()).build();
    private static final ItemStack EXPLANATION = new ItemStackUtil(Material.OAK_WALL_SIGN).loreLines(empty(), text("Jeder Charakter verfügt über andere Fähigkeiten, wähle bedacht.", NamedTextColor.GRAY), empty()).build();

    public static void open(@NotNull Player player) {
        player.openInventory(CharacterInventory.create());
    }

    private static @NotNull Inventory create() {
        Inventory inventory = Bukkit.createInventory(null, 27, text("Charaktere", NamedTextColor.GOLD));

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

package de.clientdns.smash.inventories;

import de.clientdns.smash.api.builder.ItemStackBuilder;
import de.clientdns.smash.api.character.Character;
import de.clientdns.smash.api.config.MiniMsg;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.kyori.adventure.text.Component.empty;
import static net.kyori.adventure.text.format.NamedTextColor.GOLD;
import static net.kyori.adventure.text.format.NamedTextColor.GRAY;

public class CharacterInventory {

    public static void open(@NotNull Player player) {
        player.openInventory(CharacterInventory.create());
    }

    private static @NotNull Inventory create() {
        Inventory inventory = Bukkit.createInventory(null, 27, MiniMsg.plain("Charaktere", GOLD));

        new ItemStackBuilder(Material.PLAYER_HEAD, 1, Character.MARIO.getName()).make(mario -> inventory.setItem(1, mario));

        new ItemStackBuilder(Material.PLAYER_HEAD, 1, Character.DONKEY_KONG.getName()).make(donkeyKong -> inventory.setItem(11, donkeyKong));

        new ItemStackBuilder(Material.PLAYER_HEAD, 1, Character.FLASH.getName()).make(flash -> inventory.setItem(3, flash));

        new ItemStackBuilder(Material.PLAYER_HEAD, 1, Character.PIKACHU.getName()).make(pikachu -> inventory.setItem(5, pikachu));

        new ItemStackBuilder(Material.PLAYER_HEAD, 1, Character.SUPERMAN.getName()).make(superman -> inventory.setItem(15, superman));

        new ItemStackBuilder(Material.PLAYER_HEAD, 1, Character.LINK.getName()).make(link -> inventory.setItem(7, link));

        new ItemStackBuilder(Material.OAK_WALL_SIGN, 1, empty(), List.of(empty(), MiniMsg.plain("Jeder Character verfügt über anderen Fähigkeiten, wähle bedacht!", GRAY))).make(explanation -> inventory.setItem(22, explanation));

        return inventory;
    }
}

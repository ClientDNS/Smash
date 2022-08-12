package de.clientdns.smash.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class ItemStackUtil {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemStackUtil() {
        itemStack = new ItemStack(Material.COBBLESTONE);

        itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.empty());
    }

    public ItemStackUtil name(@NotNull String name) {
        itemMeta.displayName(MiniMessage.miniMessage().deserialize(name));
        return this;
    }

    public ItemStackUtil name(String name, NamedTextColor color) {
        itemMeta.displayName(Component.text(name, color));
        return this;
    }

    public ItemStackUtil name(Component name) {
        itemMeta.displayName(name);
        return this;
    }

    public ItemStackUtil material(Material material) {
        itemStack.setType(material);
        return this;
    }

    public ItemStackUtil unbreakable() {
        itemMeta.setUnbreakable(true);
        return this;
    }

    public ItemStackUtil loreLines(@NotNull String @NotNull ... lines) {
        List<Component> lore = new ArrayList<>();
        Arrays.stream(lines).forEach(line -> lore.add(MiniMessage.miniMessage().deserialize(line)));
        itemMeta.lore(lore);
        return this;
    }

    public ItemStackUtil enchants(@NotNull Map<Enchantment, Integer> enchantments) {
        enchantments.forEach((enchantment, level) -> itemMeta.addEnchant(enchantment, level, true));
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}

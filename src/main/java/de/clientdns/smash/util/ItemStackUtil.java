package de.clientdns.smash.util;

import net.kyori.adventure.text.Component;
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
    }

    public ItemStackUtil(Material material) {
        if (material == null) {
            throw new IllegalArgumentException("Material must not be null.");
        }
        itemStack = new ItemStack(material);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemStackUtil(Material material, short amount) {
        if (amount < 1) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }
        itemStack = new ItemStack(material, amount);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemStackUtil amount(short amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        }
        itemStack.setAmount(amount);
        return this;
    }

    public ItemStackUtil name(@NotNull String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be empty.");
        }
        itemMeta.displayName(MiniMessage.miniMessage().deserialize(name));
        return this;
    }

    public ItemStackUtil name(@NotNull Component name) {
        if (name.equals(Component.empty())) {
            throw new IllegalArgumentException("Name must not be empty.");
        }
        itemMeta.displayName(name);
        return this;
    }

    public ItemStackUtil material(Material material) {
        if (material == null) {
            throw new IllegalArgumentException("Material must not be null.");
        }
        itemStack.setType(material);
        return this;
    }

    public ItemStackUtil unbreakable() {
        itemMeta.setUnbreakable(true);
        return this;
    }

    public ItemStackUtil loreLines(@NotNull String @NotNull ... lines) {
        if (lines.length == 0) {
            throw new IllegalArgumentException("Lore lines must not be empty.");
        }
        List<Component> lore = new ArrayList<>();
        Arrays.stream(lines).forEach(line -> lore.add(MiniMessage.miniMessage().deserialize(line)));
        itemMeta.lore(lore);
        return this;
    }

    public ItemStackUtil enchants(@NotNull Map<Enchantment, Integer> enchantments, boolean ignoreLevel) {
        if (enchantments.isEmpty()) {
            throw new IllegalArgumentException("Enchantments must not be empty.");
        }
        if (ignoreLevel) {
            enchantments.forEach((enchantment, level) -> itemMeta.addEnchant(enchantment, level, false));
        } else {
            enchantments.forEach((enchantment, level) -> itemMeta.addEnchant(enchantment, level, true));
        }
        return this;
    }

    public ItemStack build() {
        if (itemStack == null) {
            throw new IllegalArgumentException("ItemStack must not be null.");
        }
        if (itemMeta == null) {
            throw new IllegalArgumentException("ItemMeta must not be null.");
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}

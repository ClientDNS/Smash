package de.clientdns.smash.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
            throw new NullPointerException("Material must not be null.");
        }
        itemStack = new ItemStack(material);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemStackUtil(Material material, short amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must not be negative.");
        } else if (amount > 64) {
            throw new IllegalArgumentException("Amount must not be greater than 64.");
        }
        itemStack = new ItemStack(material, amount);
        itemMeta = itemStack.getItemMeta();
    }

    public ItemStackUtil amount(short amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount must be greater than 0.");
        } else if (amount > 64) {
            throw new IllegalArgumentException("Amount must be less than 64.");
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
            throw new NullPointerException("Material must not be null.");
        }
        itemStack.setType(material);
        return this;
    }

    public ItemStackUtil unbreakable(boolean unbreakable) {
        itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemStackUtil loreLines(@NotNull String @NotNull ... lines) {
        if (lines.length == 0) {
            throw new IllegalArgumentException("Lore lines must not be empty.");
        }
        List<Component> lore = Arrays.stream(lines).toList().stream().map(line -> MiniMessage.miniMessage().deserialize(line)).collect(Collectors.toList());
        itemMeta.lore(lore);
        return this;
    }

    public ItemStackUtil enchants(@NotNull Map<Enchantment, Integer> enchantments, boolean ignoreLevel) {
        if (enchantments.isEmpty()) {
            throw new IllegalArgumentException("Enchantments must not be empty.");
        }
        if (ignoreLevel) {
            enchantments.forEach((enchantment, level) -> itemMeta.addEnchant(enchantment, level, true));
        } else {
            enchantments.forEach((enchantment, level) -> itemMeta.addEnchant(enchantment, level, false));
        }
        return this;
    }

    public ItemStack build() {
        if (itemStack == null) {
            throw new NullPointerException("ItemStack must not be null.");
        }
        if (itemMeta == null) {
            throw new NullPointerException("ItemMeta must not be null.");
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}

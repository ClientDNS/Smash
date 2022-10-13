package de.clientdns.smash.util;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

import static net.kyori.adventure.text.Component.empty;

@SuppressWarnings("unused")
public class ItemStackUtil {

    private ItemStack itemStack;
    private ItemMeta itemMeta;

    private ItemStackUtil() {
    }

    /**
     * Creates a new ItemStackUtil instance with the given {@link Material} and default amount of 1.
     *
     * @param material The material of the item.
     */
    public ItemStackUtil(Material material) {
        this(material, 1);
    }

    /**
     * Creates a new ItemStackUtil with the given {@link Material} and amount.
     *
     * @param material The material of the item.
     * @param amount   The amount of the item.
     */
    public ItemStackUtil(Material material, int amount) {
        this(material, amount, empty());
    }

    public ItemStackUtil(Material material, int amount, Component displayName) {
        if (material == null) {
            throw new NullPointerException("Material must not be null.");
        }
        itemStack = new ItemStack(material, amount);
        itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(displayName);
    }

    /**
     * Sets the display name of the item.
     *
     * @param name The name of the item.
     * @return The ItemStackUtil instance.
     */
    public ItemStackUtil name(@NotNull Component name) {
        itemMeta.displayName(name);
        return this;
    }

    /**
     * Sets the unbreakable state of the item.
     *
     * @param unbreakable The unbreakable state.
     * @return The ItemStackUtil instance.
     */
    public ItemStackUtil unbreakable(boolean unbreakable) {
        itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Sets the lore of the item.
     *
     * @param lines The lines of the lore.
     * @return The ItemStackUtil instance.
     */
    public ItemStackUtil loreLines(@NotNull Component @NotNull ... lines) {
        if (lines.length == 0) {
            throw new IllegalArgumentException("Lore lines must not be empty.");
        }
        itemMeta.lore(List.of(lines));
        return this;
    }

    /**
     * Sets the enchantments of the item.
     *
     * @param enchantments The enchantments.
     * @return The ItemStackUtil instance.
     */
    public ItemStackUtil enchants(@NotNull Map<Enchantment, Integer> enchantments) {
        for (Enchantment enchantment : enchantments.keySet()) {
            itemMeta.addEnchant(enchantment, enchantments.get(enchantment), true); // Ignore level limit
        }
        return this;
    }

    /**
     * Builds the item.
     *
     * @return The item as {@link ItemStack}.
     */
    public ItemStack build() {
        if (itemStack == null) {
            return null;
        }
        if (itemMeta == null) {
            return null;
        }
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}

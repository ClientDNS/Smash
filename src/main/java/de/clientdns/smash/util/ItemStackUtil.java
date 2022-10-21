package de.clientdns.smash.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        this(material, amount, Component.empty());
    }

    public ItemStackUtil(Material material, int amount, Component displayName) {
        if (material == null) {
            throw new NullPointerException("Material must not be null.");
        } else {
            this.itemStack = new ItemStack(material, amount);
            this.itemMeta = this.itemStack.getItemMeta();
            this.itemMeta.displayName(displayName);
        }
    }

    /**
     * Sets the display name of the item.
     *
     * @param name The name of the item.
     * @return The ItemStackUtil instance.
     */
    public ItemStackUtil name(@NotNull Component name) {
        this.itemMeta.displayName(name);
        return this;
    }

    /**
     * Gets the display name of the item.
     *
     * @return The display name of the item.
     */
    public String name() {
        return LegacyComponentSerializer.legacyAmpersand().serialize(Objects.requireNonNull(itemMeta.displayName()));
    }

    public int amount() {
        return this.itemStack.getAmount();
    }

    public Material material() {
        return this.itemStack.getType();
    }

    /**
     * Sets the unbreakable state of the item.
     *
     * @param unbreakable The unbreakable state.
     * @return The ItemStackUtil instance.
     */
    public ItemStackUtil unbreakable(boolean unbreakable) {
        this.itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Gets the unbreakable state of the item.
     *
     * @return The unbreakable state of the item.
     */
    public boolean unbreakable() {
        return this.itemMeta.isUnbreakable();
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
        } else {
            this.itemMeta.lore(List.of(lines));
            return this;
        }
    }

    /**
     * Sets the enchantments of the item.
     *
     * @param enchantments The enchantments of the item.
     * @return The ItemStackUtil instance.
     */
    public ItemStackUtil enchants(@NotNull Map<Enchantment, Integer> enchantments) {
        for (Enchantment enchantment : enchantments.keySet()) {
            this.itemMeta.addEnchant(enchantment, enchantments.get(enchantment), true);
        }
        return this;
    }

    public Map<Enchantment, Integer> enchants() {
        return this.itemMeta.getEnchants();
    }

    /**
     * Builds the item and returns it.
     *
     * @return The item as {@link ItemStack}.
     */
    public ItemStack build() {
        if (this.itemStack == null) {
            return null;
        } else if (this.itemMeta == null) {
            return null;
        } else {
            this.itemStack.setItemMeta(this.itemMeta);
            return this.itemStack;
        }
    }
}

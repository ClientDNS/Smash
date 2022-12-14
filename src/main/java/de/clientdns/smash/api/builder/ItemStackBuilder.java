package de.clientdns.smash.api.builder;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * A builder for {@link ItemStack}s.
 * <p>
 * This class is a wrapper and provides a fluent API for creating {@link ItemStack} classes.
 * </p>
 */
public class ItemStackBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    @SuppressWarnings("unused")
    private ItemStackBuilder() {
        throw new UnsupportedOperationException("This class cannot be instantiated.");
    }

    /**
     * Creates a new ItemStackBuilder with the given {@link Material} and amount.
     *
     * @param material The material of the item.
     * @param amount   The amount of the item.
     */
    public ItemStackBuilder(Material material, int amount) {
        this(material, amount, null);
    }

    /**
     * Creates a new ItemStackBuilder with the given {@link Material}, amount and display name.
     *
     * @param material    The material of the item.
     * @param amount      The amount of the item.
     * @param displayName The display name of the item.
     */
    public ItemStackBuilder(Material material, int amount, Component displayName) {
        this(material, amount, displayName, null);
    }

    /**
     * Creates a new ItemStackBuilder with the given {@link Material}, amount, display name and lore.
     *
     * @param material    The material of the item.
     * @param amount      The amount of the item.
     * @param displayName The display name of the item.
     * @param lore        The lore of them item.
     */
    public ItemStackBuilder(Material material, int amount, Component displayName, List<Component> lore) {
        if (material == null) throw new NullPointerException("Material must not be null.");
        else {
            this.itemStack = new ItemStack(material, amount);
            this.itemMeta = this.itemStack.getItemMeta();
            if (displayName != null) this.itemMeta.displayName(displayName);
            if (lore != null) this.itemMeta.lore(lore);
        }
    }

    /**
     * Gets the display name of the item.
     *
     * @return The display name of the item.
     */
    public Component name() {
        return itemMeta.displayName();
    }

    /**
     * Sets the display name of the item.
     *
     * @param name The name of the item.
     * @return The ItemStackBuilder instance.
     */
    public ItemStackBuilder name(Component name) {
        itemMeta.displayName(name);
        return this;
    }

    /**
     * Gets the amount of the item.
     *
     * @return The amount of the item.
     */
    public int amount() {
        return itemStack.getAmount();
    }

    /**
     * Sets the amount of the item.
     *
     * @return The amount of the item.
     */
    public ItemStackBuilder amount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    /**
     * Gets the material of the item.
     *
     * @return The material of the item.
     */
    public Material material() {
        return itemStack.getType();
    }

    /**
     * Sets the material of the item.
     *
     * @param material The material of the item.
     * @return The material of the item.
     */
    public ItemStackBuilder material(Material material) {
        itemStack.setType(material);
        return this;
    }

    /**
     * Gets the unbreakable state of the item.
     *
     * @return The unbreakable state of the item.
     */
    public boolean unbreakable() {
        return itemMeta.isUnbreakable();
    }

    /**
     * Sets the unbreakable state of the item.
     *
     * @param unbreakable The unbreakable state.
     * @return The ItemStackBuilder instance.
     */
    public ItemStackBuilder unbreakable(boolean unbreakable) {
        itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Sets the lore of the item.
     *
     * @param lines The lines of the lore.
     * @return The ItemStackBuilder instance.
     */
    public ItemStackBuilder loreLines(Component... lines) {
        itemMeta.lore(List.of(lines));
        return this;
    }

    /**
     * Gets the enchantments of the item.
     *
     * @return The enchantments of the item.
     */
    public Map<Enchantment, Integer> enchants() {
        return itemMeta.getEnchants();
    }

    /**
     * Sets the enchantment of the item.
     *
     * @param enchantment The enchantment of the item.
     * @return The ItemStackBuilder instance.
     */
    public ItemStackBuilder enchant(Enchantment enchantment, int level) {
        itemMeta.addEnchant(enchantment, level, true);
        return this;
    }

    /**
     * Sets the enchantments of the item.
     *
     * @param enchantments The enchantments of the item.
     * @return The ItemStackBuilder instance.
     */
    public ItemStackBuilder enchants(@NotNull Map<Enchantment, Integer> enchantments) {
        for (Enchantment enchantment : enchantments.keySet())
            itemMeta.addEnchant(enchantment, enchantments.get(enchantment), true);
        return this;
    }

    /**
     * Gets the item flags of the item.
     *
     * @return The item flags of the item.
     */
    public Set<ItemFlag> itemFlags() {
        return itemMeta.getItemFlags();
    }

    /**
     * Sets the item flags of the item.
     *
     * @param itemFlags The item flags of the item.
     * @return The ItemStackBuilder instance.
     */
    public ItemStackBuilder itemFlags(ItemFlag... itemFlags) {
        itemMeta.addItemFlags(itemFlags);
        return this;
    }

    /**
     * Gets the meta of the item.
     *
     * @return The meta of the item.
     */
    public ItemMeta meta() {
        return itemMeta;
    }

    /**
     * Builds the item and returns it.
     *
     * @param consumer
     */
    public void make(@NotNull Consumer<ItemStack> consumer) {
        itemStack.setItemMeta(itemMeta);
        consumer.accept(itemStack);
    }
}

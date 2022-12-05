package de.clientdns.smash.builder;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
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
        return this.itemMeta.displayName();
    }

    /**
     * Sets the display name of the item.
     *
     * @param name The name of the item.
     * @return The ItemStackBuilder instance.
     */
    public ItemStackBuilder name(Component name) {
        this.itemMeta.displayName(name);
        return this;
    }

    /**
     * Gets the amount of the item.
     *
     * @return The amount of the item.
     */
    public int amount() {
        return this.itemStack.getAmount();
    }

    /**
     * Sets the amount of the item.
     *
     * @return The amount of the item.
     */
    public ItemStackBuilder amount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    /**
     * Gets the material of the item.
     *
     * @return The material of the item.
     */
    public Material material() {
        return this.itemStack.getType();
    }

    /**
     * Sets the material of the item.
     *
     * @param material The material of the item.
     * @return The material of the item.
     */
    public ItemStackBuilder material(Material material) {
        this.itemStack.setType(material);
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
     * Sets the unbreakable state of the item.
     *
     * @param unbreakable The unbreakable state.
     * @return The ItemStackBuilder instance.
     */
    public ItemStackBuilder unbreakable(boolean unbreakable) {
        this.itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    /**
     * Sets the lore of the item.
     *
     * @param lines The lines of the lore.
     * @return The ItemStackBuilder instance.
     */
    public ItemStackBuilder loreLines(Component... lines) {
        this.itemMeta.lore(List.of(lines));
        return this;
    }

    /**
     * Gets the enchantments of the item.
     *
     * @return The enchantments of the item.
     */
    public Map<Enchantment, Integer> enchants() {
        return this.itemMeta.getEnchants();
    }

    /**
     * Sets the enchantment of the item.
     *
     * @param enchantment The enchantment of the item.
     * @return The ItemStackBuilder instance.
     */
    public ItemStackBuilder enchant(Enchantment enchantment, int level) {
        this.itemMeta.addEnchant(enchantment, level, true);
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
            this.itemMeta.addEnchant(enchantment, enchantments.get(enchantment), true);
        return this;
    }

    /**
     * Gets the item flags of the item.
     *
     * @return The item flags of the item.
     */
    public ItemStackBuilder hide_attributes() {
        if (!this.itemMeta.getItemFlags().contains(ItemFlag.HIDE_ATTRIBUTES))
            this.itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        return this;
    }

    /**
     * Gets the item flags of the item.
     *
     * @return The item flags of the item.
     */
    public ItemStackBuilder hide_enchants() {
        if (!this.itemMeta.getItemFlags().contains(ItemFlag.HIDE_ENCHANTS))
            this.itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    /**
     * Gets the item flags of the item.
     *
     * @return The item flags of the item.
     */
    public ItemStackBuilder hide_unbreakable() {
        if (!this.itemMeta.getItemFlags().contains(ItemFlag.HIDE_UNBREAKABLE))
            this.itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        return this;
    }

    /**
     * Gets the item flags of the item.
     *
     * @return The item flags of the item.
     */
    public ItemStackBuilder hide_destroys() {
        if (!this.itemMeta.getItemFlags().contains(ItemFlag.HIDE_DESTROYS))
            this.itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        return this;
    }

    /**
     * Gets the item flags of the item.
     *
     * @return The item flags of the item.
     */
    public ItemStackBuilder hide_placed_on() {
        if (!this.itemMeta.getItemFlags().contains(ItemFlag.HIDE_PLACED_ON))
            this.itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        return this;
    }

    /**
     * Gets the item flags of the item.
     *
     * @return The item flags of the item.
     */
    public ItemStackBuilder hide_potion_effects() {
        if (!this.itemMeta.getItemFlags().contains(ItemFlag.HIDE_POTION_EFFECTS))
            this.itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        return this;
    }

    /**
     * Gets the item flags of the item.
     *
     * @return The item flags of the item.
     */
    public ItemStackBuilder hide_dye() {
        if (!this.itemMeta.getItemFlags().contains(ItemFlag.HIDE_DYE)) this.itemMeta.addItemFlags(ItemFlag.HIDE_DYE);
        return this;
    }

    /**
     * Gets the meta of the item.
     *
     * @return The meta of the item.
     */
    public ItemMeta meta() {
        return this.itemMeta;
    }

    public void item() {
        this.itemStack.setItemMeta(this.itemMeta);
    }

    /**
     * Builds the item and returns it.
     */
    public void make(@NotNull Consumer<ItemStack> consumer) {
        item();
        consumer.accept(this.itemStack);
    }
}

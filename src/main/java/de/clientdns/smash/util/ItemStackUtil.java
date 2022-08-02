package de.clientdns.smash.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemStackUtil {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;
    private final HashMap<Enchantment, Integer> itemEnchantments;

    public ItemStackUtil() {
        itemStack = new ItemStack(Material.STONE);

        itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(Component.empty());

        itemEnchantments = new HashMap<>();
    }

    public ItemStackUtil material(Material material) {
        itemStack.setType(material);
        return this;
    }

    public ItemStackUtil unbreakable() {
        itemMeta.setUnbreakable(true);
        return this;
    }

    public ItemStackUtil amount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemStackUtil name(String displayName) {
        itemMeta.displayName(MiniMessage.miniMessage().deserialize(displayName));
        return this;
    }

    public ItemStackUtil itemFlags(ItemFlag... flags) {
        itemMeta.addItemFlags(flags);
        return this;
    }

    public ItemStackUtil loreLines(@NotNull List<String> components) {
        List<Component> lore = new ArrayList<>();
        for (String component : components) {
            lore.add(MiniMessage.miniMessage().deserialize(component));
        }
        itemMeta.lore(lore);
        return this;
    }

    public ItemStackUtil loreLines(@NotNull String @NotNull ... lines) {
        List<Component> lore = new ArrayList<>();
        for (String component : lines) {
            lore.add(MiniMessage.miniMessage().deserialize(component));
        }
        itemMeta.lore(lore);
        return this;
    }

    public ItemStackUtil enchant(Enchantment enchantment, int level) {
        itemEnchantments.put(enchantment, level);
        return this;
    }

    public ItemStackUtil enchants(Map<Enchantment, Integer> enchantments) {
        itemEnchantments.putAll(enchantments);
        return this;
    }

    public ItemStack build() {
        itemEnchantments.forEach((enchant, level) -> itemMeta.addEnchant(enchant, level, true));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}

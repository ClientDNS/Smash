package de.clientdns.smash.inventory;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class InventoryCreator {

    private final Inventory inventory;
    private final Component name;
    private final InventoryEditor editor;
    private int slots = 9; // Here, 9 is a default value when not overriding

    public InventoryCreator(int factor, Component name) {
        // Factor 6 is the highest number to create an inventory.
        // This means, the inventory will have 9 * 6 (54 slots) -> 6 rows with every 9 slots.
        // If factor is more than 6, use 6 as factor. When less than 6, use the factor size provided.
        this.slots = factor > 6 ? slots * 6 : slots * factor;
        this.name = name;
        this.inventory = Bukkit.createInventory(null, this.slots, this.name);
        this.editor = new InventoryEditor(this);
    }

    public void accept(@NotNull Consumer<Inventory> consumer) {
        consumer.accept(inventory);
    }

    public InventoryCreator edit(@NotNull Consumer<InventoryEditor> consumer) {
        consumer.accept(editor);
        return this;
    }

    public Component getName() {
        return name;
    }

    public int getSlots() {
        return slots;
    }

    /**
     * @param creator The instance of the inventory creator ({@link InventoryCreator}) to manage items in it.
     */
    public record InventoryEditor(InventoryCreator creator) {

        public void add(ItemStack itemStack) {
            creator.accept(inventory -> inventory.addItem(itemStack));
        }

        public void add(ItemStack... itemStack) {
            creator.accept(inventory -> inventory.addItem(itemStack));
        }

        public void set(int index, ItemStack itemStack) {
            creator.accept(inventory -> inventory.setItem(index, itemStack));
        }

        public void contents(ItemStack... itemStacks) {
            creator.accept(inventory -> inventory.setContents(itemStacks));
        }

        public void clear() {
            creator.accept(Inventory::clear);
        }

        public void remove(Material material) {
            creator.accept(inventory -> inventory.remove(material));
        }

        public void remove(ItemStack itemStack) {
            creator.accept(inventory -> inventory.remove(itemStack));
        }
    }
}

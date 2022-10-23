package de.clientdns.smash.util;

import be.seeseemelk.mockbukkit.MockBukkit;
import de.clientdns.smash.SmashPlugin;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.kyori.adventure.text.Component.text;

class ItemStackTest {

    private static SmashPlugin plugin;

    @BeforeEach
    public void setUp() {
        MockBukkit.mock();
        plugin = MockBukkit.load(SmashPlugin.class);
    }

    @AfterEach
    public void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void test() {
        ItemStack stack = new ItemStackUtil(Material.BARRIER, 42).unbreakable(true).name(text("Test")).build();
        System.out.println(stack.toString());
    }
}
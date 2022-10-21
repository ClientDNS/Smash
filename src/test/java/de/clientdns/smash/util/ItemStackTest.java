package de.clientdns.smash.util;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import de.clientdns.smash.SmashPlugin;
import org.bukkit.Material;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static net.kyori.adventure.text.Component.text;

class ItemStackTest {

    private static ServerMock mock;
    private static SmashPlugin plugin;

    @BeforeAll
    public static void setUp() {
        mock = MockBukkit.mock();
        plugin = MockBukkit.load(SmashPlugin.class);
    }

    @AfterAll
    public static void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void test() {
        ItemStackUtil util = new ItemStackUtil(Material.BARRIER, 42).unbreakable(true).name(text("Test"));
        plugin.getLogger().info("Creation of ItemStack successful, printing information...");
        Assertions.assertEquals(Material.BARRIER, util.material());
        plugin.getLogger().info("> Material: " + util.material());
        Assertions.assertEquals(42, util.amount());
        plugin.getLogger().info("> Amount: " + util.amount());
        Assertions.assertTrue(util.unbreakable());
        plugin.getLogger().info("> Unbreakable: " + util.unbreakable());
    }
}
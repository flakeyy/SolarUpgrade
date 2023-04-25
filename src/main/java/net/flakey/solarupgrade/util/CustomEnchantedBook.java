package net.flakey.solarupgrade.util;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.List;
import java.util.Random;

public class CustomEnchantedBook {
    public static ItemStack createRandomEnchantedBook(List<Enchantment> enchantmentList, int level) {
        // Create an ItemStack with the Enchanted Book item
        ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);

        // Get a random enchantment from the list
        Enchantment randomEnchantment = getRandomEnchantment(enchantmentList);

        // Add the enchantment to the ItemStack using EnchantmentHelper
        EnchantmentHelper.setEnchantments(ImmutableMap.of(randomEnchantment, level), enchantedBook);

        return enchantedBook;
    }

    private static Enchantment getRandomEnchantment(List<Enchantment> enchantmentList) {
        Random random = new Random();
        return enchantmentList.get(random.nextInt(enchantmentList.size()));
    }
}

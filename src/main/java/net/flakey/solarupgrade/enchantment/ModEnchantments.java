package net.flakey.solarupgrade.enchantment;

import net.flakey.solarupgrade.SolarUpgrade;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, SolarUpgrade.MOD_ID);

    public static RegistryObject<LightningStrikeEnchantment> LIGHTNING_STRIKE =
            ENCHANTMENTS.register("lightning_strike",
                    () -> new LightningStrikeEnchantment(Enchantment.Rarity.UNCOMMON,
                            EnchantmentCategory.BOW, EquipmentSlot.MAINHAND));

    public static RegistryObject<BleedEnchantment> BLEED =
            ENCHANTMENTS.register("bleed",
                    () -> new BleedEnchantment(Enchantment.Rarity.UNCOMMON,
                            EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static RegistryObject<SelfHealingEnchantment> SELF_HEALING =
            ENCHANTMENTS.register("self_healing",
                    () -> new SelfHealingEnchantment(Enchantment.Rarity.UNCOMMON,
                            EnchantmentCategory.ARMOR_LEGS, new EquipmentSlot[]{EquipmentSlot.LEGS}));

    public static RegistryObject<SwiftStepEnchantment> SWIFT_STEP =
            ENCHANTMENTS.register("swift_step",
                    () -> new SwiftStepEnchantment(Enchantment.Rarity.UNCOMMON,
                            EnchantmentCategory.ARMOR_FEET, new EquipmentSlot[]{EquipmentSlot.FEET}));

    public static RegistryObject<RageEnchantment> RAGE =
            ENCHANTMENTS.register("rage",
                    () -> new RageEnchantment(Enchantment.Rarity.UNCOMMON,
                            EnchantmentCategory.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST}));

    public static RegistryObject<HollowedOutEnchantment> HOLLOWED_OUT =
            ENCHANTMENTS.register("hollowed_out",
                    () -> new HollowedOutEnchantment(Enchantment.Rarity.UNCOMMON,
                            EnchantmentCategory.DIGGER, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}

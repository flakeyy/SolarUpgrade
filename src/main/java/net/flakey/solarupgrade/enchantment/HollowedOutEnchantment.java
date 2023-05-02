package net.flakey.solarupgrade.enchantment;

import net.flakey.solarupgrade.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class HollowedOutEnchantment extends Enchantment{
    public HollowedOutEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pSlot) {
        super(pRarity, pCategory, pSlot);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }

    /* EVENTS */

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class HollowedOutEnchantmentHandler {

        @SubscribeEvent
        public static void giveEffect(TickEvent.PlayerTickEvent event) {
            if (event.phase == TickEvent.Phase.END || event.player.level.isClientSide()) return;

            int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.HOLLOWED_OUT.get(), event.player);

            if (level > 0) {
                event.player.addEffect(new MobEffectInstance(ModEffects.HOLLOWED_OUT.get(), 5, -1));
            }

        }
    }
}




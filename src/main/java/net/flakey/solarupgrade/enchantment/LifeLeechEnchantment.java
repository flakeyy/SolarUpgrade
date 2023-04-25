package net.flakey.solarupgrade.enchantment;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class LifeLeechEnchantment extends Enchantment{
    static boolean attackEvent;
    public LifeLeechEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pSlot) {
        super(pRarity, pCategory, pSlot);
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        if (pLevel > 0) {
            attackEvent = true;
        }



        super.doPostAttack(pAttacker, pTarget, pLevel);
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
    public static class LifeLeechEnchantmentHandler {
        @SubscribeEvent
        public static void tickPlayer(TickEvent.PlayerTickEvent event) {
            if (event.phase == TickEvent.Phase.END || event.player.level.isClientSide()) return;

            int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.LIFE_LEECH.get(), event.player);

            if (attackEvent) {
                event.player.heal(1.0f);
                attackEvent = false;
            }
        }
    }
}




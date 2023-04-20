package net.flakey.solarupgrade.enchantment;

import net.flakey.solarupgrade.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.fml.common.Mod;

public class SwiftStepEnchantment extends Enchantment {
    public SwiftStepEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public void doPostHurt(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        if (!pAttacker.level.isClientSide()) {

            MobEffectInstance hasSwiftStep = pAttacker.getEffect(ModEffects.SWIFT_STEP.get());

            if (hasSwiftStep == null) {
                pAttacker.addEffect(new MobEffectInstance(ModEffects.SWIFT_STEP.get(), 60, 0));
            } else {
                int swiftStepTime = hasSwiftStep.getDuration() + 60;
                int swiftStepLevel = hasSwiftStep.getAmplifier() + 1;

                if (swiftStepTime > 100) {
                    swiftStepTime = 100;
                }
                if (swiftStepLevel > 2) {
                    swiftStepLevel = 2;
                }

                pAttacker.addEffect(new MobEffectInstance(ModEffects.SWIFT_STEP.get(), swiftStepTime, swiftStepLevel));
            }

            super.doPostAttack(pAttacker, pTarget, pLevel);
        }
    }
    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isTreasureOnly() {
        return true;
    }
}

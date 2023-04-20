package net.flakey.solarupgrade.enchantment;

import net.flakey.solarupgrade.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class RageEnchantment extends Enchantment {
    int rageTime;
    int rageLevel;
    
    
    public RageEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public void doPostHurt(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        if (!pAttacker.level.isClientSide()) {

            MobEffectInstance hasRage = pAttacker.getEffect(ModEffects.RAGE.get());

            if (hasRage == null) {
                pAttacker.addEffect(new MobEffectInstance(ModEffects.RAGE.get(), 60, 0));
            } else {
                rageTime = hasRage.getDuration() + 60;
                rageLevel = hasRage.getAmplifier() + 1;

                if (rageTime > 140) {
                    rageTime = 140;
                }
                if (rageLevel > 2) {
                    rageLevel = 2;
                }

                pAttacker.addEffect(new MobEffectInstance(ModEffects.RAGE.get(), rageTime, rageLevel));
            }

            super.doPostAttack(pAttacker, pTarget, pLevel);
        }
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel) {

        MobEffectInstance hasRage = pAttacker.getEffect(ModEffects.RAGE.get());
        
        if (hasRage == null) {
        } else {
            rageTime = hasRage.getDuration() + 20;
            rageLevel = hasRage.getAmplifier();

            if (rageTime > 140) {
                rageTime = 140;
            }


            pAttacker.addEffect(new MobEffectInstance(ModEffects.RAGE.get(), rageTime, rageLevel));
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
}

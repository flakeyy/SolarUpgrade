package net.flakey.solarupgrade.enchantment;

import net.flakey.solarupgrade.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SelfHealingEnchantment extends Enchantment {
    public SelfHealingEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public void doPostHurt(LivingEntity pAttacker, Entity pTarget, int pLevel) {
        pAttacker.addEffect(new MobEffectInstance(ModEffects.SELF_HEALING.get(), 200, 0));
        super.doPostAttack(pAttacker, pTarget, pLevel);
    }

}

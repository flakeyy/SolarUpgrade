package net.flakey.solarupgrade.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SelfHealingEffect extends MobEffect {
    public SelfHealingEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level.isClientSide()) {
            if (pLivingEntity.getHealth() < pLivingEntity.getMaxHealth()) {
                pLivingEntity.heal(1.0F);
                addAttributeModifier(Attributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635",
                        1.00F, AttributeModifier.Operation.MULTIPLY_TOTAL);
            }
        }

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        int i = 40 >> pAmplifier;
        if (i > 0) {
            return pDuration % i == 0;
        } else {
            return true;
        }
    }
}

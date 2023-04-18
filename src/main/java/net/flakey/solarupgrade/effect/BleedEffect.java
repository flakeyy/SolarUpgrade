package net.flakey.solarupgrade.effect;

import net.minecraft.core.Direction;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;

public class BleedEffect extends MobEffect {
    public BleedEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level.isClientSide()) {
            if (pLivingEntity.getArmorValue() == 0.0f) {
                pLivingEntity.hurt(pLivingEntity.damageSources().magic(), 4.0f);
            }
            else if (pLivingEntity.getArmorValue() < 5.0f) {
                pLivingEntity.hurt(pLivingEntity.damageSources().magic(), 3.75f);
            }
            else if (pLivingEntity.getArmorValue() < 10.0f) {
                pLivingEntity.hurt(pLivingEntity.damageSources().magic(), 3.5f);
            }
            else if (pLivingEntity.getArmorValue() < 15.0f) {
                pLivingEntity.hurt(pLivingEntity.damageSources().magic(), 3.0f);
            }
            else if (pLivingEntity.getArmorValue() < 20.0f) {
                pLivingEntity.hurt(pLivingEntity.damageSources().magic(), 2.5f);
            }
            else {
                pLivingEntity.hurt(pLivingEntity.damageSources().magic(), 2.0f);
            }
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        int i = 200 >> pAmplifier;
        if (i > 0) {
            return pDuration % i == 0;
        } else {
            return true;
        }
    }
}

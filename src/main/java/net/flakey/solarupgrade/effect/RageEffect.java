package net.flakey.solarupgrade.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class RageEffect extends MobEffect {
        public RageEffect(MobEffectCategory mobEffectCategory, int color) {
                super(mobEffectCategory, color);
        }

        @Override
        public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
                if (!pLivingEntity.level.isClientSide()) {
                }

                super.applyEffectTick(pLivingEntity, pAmplifier);
        }
}

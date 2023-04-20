package net.flakey.solarupgrade.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

public class HollowedOutEffect extends MobEffect {
    public HollowedOutEffect(MobEffectCategory pCategory, int color) {
        super(pCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {

        super.applyEffectTick(pLivingEntity, pAmplifier);
    }
}

package net.flakey.solarupgrade.effect;

import net.flakey.solarupgrade.SolarUpgrade;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS
            = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SolarUpgrade.MOD_ID);

    public static final RegistryObject<MobEffect> BLEED = MOB_EFFECTS.register("bleed",
            () -> new BleedEffect(MobEffectCategory.HARMFUL, 9502720));

    public static final RegistryObject<MobEffect> SELF_HEALING = MOB_EFFECTS.register("self_healing",
            () -> new SelfHealingEffect(MobEffectCategory.BENEFICIAL, 13278876));

    public static final RegistryObject<MobEffect> SWIFT_STEP = MOB_EFFECTS.register("swift_step",
            () -> new SwiftStepEffect(MobEffectCategory.BENEFICIAL, 13278876).addAttributeModifier(Attributes.MOVEMENT_SPEED, "91AEAA56-376B-4498-935B-2F7F68070635",
                    0.15F, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static final RegistryObject<MobEffect> RAGE = MOB_EFFECTS.register("rage",
            () -> new RageEffect(MobEffectCategory.BENEFICIAL, 13278876).addAttributeModifier(Attributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9",
                    0.75D, AttributeModifier.Operation.ADDITION));

    public static final RegistryObject<MobEffect> HOLLOWED_OUT = MOB_EFFECTS.register("hollowed_out",
            () -> new HollowedOutEffect(MobEffectCategory.BENEFICIAL, 13278876).addAttributeModifier(Attributes.ATTACK_SPEED, "AF8B6E3F-3328-4C0A-AA36-5BA2BB9DBEF3",
                    0.1F, AttributeModifier.Operation.MULTIPLY_TOTAL));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}

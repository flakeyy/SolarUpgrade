package net.flakey.solarupgrade.block.entity;

import net.flakey.solarupgrade.SolarUpgrade;
import net.flakey.solarupgrade.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SolarUpgrade.MOD_ID);

    public static final RegistryObject<BlockEntityType<SolarChargerBlockEntity>> SOLAR_CHARGER =
            BLOCK_ENTITIES.register("solar_charger", () ->
                    BlockEntityType.Builder.of(SolarChargerBlockEntity::new,
                            ModBlocks.SOLAR_CHARGER.get()).build(null));

    public static final RegistryObject<BlockEntityType<EnhancementTableBlockEntity>> ENHANCEMENT_TABLE =
            BLOCK_ENTITIES.register("enhancement_table", () ->
                    BlockEntityType.Builder.of(EnhancementTableBlockEntity::new,
                            ModBlocks.ENHANCEMENT_TABLE.get()).build(null));

    public static final RegistryObject<BlockEntityType<SolarPanelBlockEntity>> SOLAR_PANEL =
            BLOCK_ENTITIES.register("solar_panel", () ->
                    BlockEntityType.Builder.of(SolarPanelBlockEntity::new,
                            ModBlocks.SOLAR_PANEL.get()).build(null));

    public static final RegistryObject<BlockEntityType<AdvancedSolarPanelBlockEntity>> ADVANCED_SOLAR_PANEL =
            BLOCK_ENTITIES.register("advanced_solar_panel", () ->
                    BlockEntityType.Builder.of(AdvancedSolarPanelBlockEntity::new,
                            ModBlocks.ADVANCED_SOLAR_PANEL.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}

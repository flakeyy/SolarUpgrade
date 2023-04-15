package net.flakey.solarupgrade.item;

import net.flakey.solarupgrade.SolarUpgrade;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SolarUpgrade.MOD_ID);

    public static final RegistryObject<Item> SILICON = ITEMS.register("silicon",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILICON_COMPOSITE = ITEMS.register("silicon_composite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_WIRE = ITEMS.register("copper_wire",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SOLAR_CELL = ITEMS.register("solar_cell",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GLASS_SHEET = ITEMS.register("glass_sheet",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RUBBER = ITEMS.register("rubber",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> METAL_FRAME = ITEMS.register("metal_frame",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FORGOTTEN_MATERIAL = ITEMS.register("forgotten_material",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ENHANCEMENT_CORE = ITEMS.register("raw_enhancement_core",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNCHARGED_ENHANCEMENT_CORE = ITEMS.register("uncharged_enhancement_core",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHARGED_ENHANCEMENT_CORE = ITEMS.register("charged_enhancement_core",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MODIFIED_ENHANCEMENT_CORE = ITEMS.register("modified_enhancement_core",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

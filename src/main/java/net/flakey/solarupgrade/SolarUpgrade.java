package net.flakey.solarupgrade;

import com.mojang.logging.LogUtils;
import net.flakey.solarupgrade.block.ModBlocks;
import net.flakey.solarupgrade.item.ModCreativeModeTabs;
import net.flakey.solarupgrade.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SolarUpgrade.MOD_ID)
public class SolarUpgrade
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "solarupgrade";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public SolarUpgrade() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.SILICON);
            event.accept(ModItems.SILICON_COMPOSITE);
            event.accept(ModItems.SOLAR_CELL);
            event.accept(ModItems.RAW_ENHANCEMENT_CORE);
            event.accept(ModItems.UNCHARGED_ENHANCEMENT_CORE);
            event.accept(ModItems.CHARGED_ENHANCEMENT_CORE);
            event.accept(ModItems.MODIFIED_ENHANCEMENT_CORE);
            event.accept(ModItems.COPPER_WIRE);
            event.accept(ModItems.GLASS_SHEET);
            event.accept(ModItems.RUBBER);
            event.accept(ModItems.METAL_FRAME);
            event.accept(ModItems.FORGOTTEN_MATERIAL);

        }
        if(event.getTab() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModBlocks.SOLAR_CHARGER);
            event.accept(ModBlocks.SOLAR_PANEL);
            event.accept(ModBlocks.SOLAR_ARRAY);
            event.accept(ModBlocks.ENHANCEMENT_TABLE);
            event.accept(ModBlocks.INSULATED_WIRE);
        }
        if(event.getTab() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.MATERIAL_ORE);
            event.accept(ModBlocks.RUBBER_LOG);
            event.accept(ModBlocks.RUBBER_WOOD);
            event.accept(ModBlocks.RUBBER_PLANKS);
            event.accept(ModBlocks.RUBBER_LEAVES);
            event.accept(ModBlocks.RUBBER_SAPLING);
            event.accept(ModBlocks.STRIPPED_RUBBER_LOG);
            event.accept(ModBlocks.STRIPPED_RUBBER_WOOD);
        }

        if(event.getTab() == ModCreativeModeTabs.SOLARUPGRADE_TAB) {
            event.accept(ModItems.SILICON);
            event.accept(ModItems.SILICON_COMPOSITE);
            event.accept(ModItems.SOLAR_CELL);
            event.accept(ModItems.COPPER_WIRE);
            event.accept(ModItems.GLASS_SHEET);
            event.accept(ModItems.RUBBER);
            event.accept(ModItems.METAL_FRAME);
            event.accept(ModItems.FORGOTTEN_MATERIAL);
            event.accept(ModItems.RAW_ENHANCEMENT_CORE);
            event.accept(ModItems.UNCHARGED_ENHANCEMENT_CORE);
            event.accept(ModItems.CHARGED_ENHANCEMENT_CORE);
            event.accept(ModItems.MODIFIED_ENHANCEMENT_CORE);

            event.accept(ModBlocks.SOLAR_CHARGER);
            event.accept(ModBlocks.SOLAR_PANEL);
            event.accept(ModBlocks.SOLAR_ARRAY);
            event.accept(ModBlocks.ENHANCEMENT_TABLE);
            event.accept(ModBlocks.MATERIAL_ORE);
            event.accept(ModBlocks.INSULATED_WIRE);

            event.accept(ModBlocks.RUBBER_LOG);
            event.accept(ModBlocks.RUBBER_WOOD);
            event.accept(ModBlocks.RUBBER_PLANKS);
            event.accept(ModBlocks.RUBBER_LEAVES);
            event.accept(ModBlocks.RUBBER_SAPLING);
            event.accept(ModBlocks.STRIPPED_RUBBER_LOG);
            event.accept(ModBlocks.STRIPPED_RUBBER_WOOD);

        }

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}

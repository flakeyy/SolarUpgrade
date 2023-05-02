package net.flakey.solarupgrade;

import com.mojang.logging.LogUtils;
import net.flakey.solarupgrade.block.ModBlocks;
import net.flakey.solarupgrade.block.entity.ModBlockEntities;
import net.flakey.solarupgrade.effect.ModEffects;
import net.flakey.solarupgrade.enchantment.ModEnchantments;
import net.flakey.solarupgrade.item.ModCreativeModeTabs;
import net.flakey.solarupgrade.item.ModItems;
import net.flakey.solarupgrade.networking.ModMessages;
import net.flakey.solarupgrade.screen.EnhancementTableScreen;
import net.flakey.solarupgrade.screen.ModMenuTypes;
import net.flakey.solarupgrade.screen.SolarChargerScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.function.Supplier;

@Mod(SolarUpgrade.MOD_ID)
public class SolarUpgrade
{
    public static final String MOD_ID = "solarupgrade";
    private static final Logger LOGGER = LogUtils.getLogger();

    public SolarUpgrade() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModEnchantments.register(modEventBus);
        ModEffects.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ModMessages.register();
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
            event.accept(ModItems.METAL_FRAME);
            event.accept(ModItems.FORGOTTEN_MATERIAL);

        }
        if(event.getTab() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModBlocks.SOLAR_CHARGER);
            event.accept(ModBlocks.SOLAR_PANEL);
            event.accept(ModBlocks.ADVANCED_SOLAR_PANEL);
            event.accept(ModBlocks.ENHANCEMENT_TABLE);
        }
        if(event.getTab() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.END_FORGOTTEN_ORE);
        }

        if(event.getTab() == ModCreativeModeTabs.SOLARUPGRADE_TAB) {
            event.accept(ModItems.SILICON);
            event.accept(ModItems.SILICON_COMPOSITE);
            event.accept(ModItems.SOLAR_CELL);
            event.accept(ModItems.COPPER_WIRE);
            event.accept(ModItems.GLASS_SHEET);
            event.accept(ModItems.METAL_FRAME);
            event.accept(ModItems.FORGOTTEN_MATERIAL);
            event.accept(ModItems.RAW_ENHANCEMENT_CORE);
            event.accept(ModItems.UNCHARGED_ENHANCEMENT_CORE);
            event.accept(ModItems.CHARGED_ENHANCEMENT_CORE);
            event.accept(ModItems.MODIFIED_ENHANCEMENT_CORE);

            event.accept(ModBlocks.SOLAR_CHARGER);
            event.accept(ModBlocks.SOLAR_PANEL);
            event.accept(ModBlocks.ADVANCED_SOLAR_PANEL);
            event.accept(ModBlocks.ENHANCEMENT_TABLE);
            event.accept(ModBlocks.END_FORGOTTEN_ORE);
        }
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.SOLAR_CHARGER_MENU.get(), SolarChargerScreen::new);
            MenuScreens.register(ModMenuTypes.ENHANCEMENT_TABLE_MENU.get(), EnhancementTableScreen::new);

        }
    }
}

package net.flakey.solarupgrade.item;

import net.flakey.solarupgrade.SolarUpgrade;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SolarUpgrade.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTabs {
    public static CreativeModeTab SOLARUPGRADE_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        SOLARUPGRADE_TAB = event.registerCreativeModeTab(new ResourceLocation(SolarUpgrade.MOD_ID, "solarupgrade_tab"),
                builder -> builder.icon(() -> new ItemStack(ModItems.SILICON.get()))
                        .title(Component.translatable("creativemodetab.solarupgrade_tab")));

    }
}

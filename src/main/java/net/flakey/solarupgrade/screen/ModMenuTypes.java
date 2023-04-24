package net.flakey.solarupgrade.screen;

import net.flakey.solarupgrade.SolarUpgrade;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, SolarUpgrade.MOD_ID);

    public static final RegistryObject<MenuType<SolarChargerMenu>> SOLAR_CHARGER_MENU =
            registerMenuType(SolarChargerMenu::new, "solar_charger_menu");

    public static final RegistryObject<MenuType<EnhancementTableMenu>> ENHANCEMENT_TABLE_MENU =
            registerMenuType(EnhancementTableMenu::new, "enhancement_table_menu");


    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }

}

package net.flakey.solarupgrade.datagen;

import net.flakey.solarupgrade.SolarUpgrade;
import net.flakey.solarupgrade.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output,  ExistingFileHelper existingFileHelper) {
        super(output, SolarUpgrade.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.SILICON);
        simpleItem(ModItems.SILICON_COMPOSITE);
        simpleItem(ModItems.SOLAR_CELL);
        simpleItem(ModItems.GLASS_SHEET);
        simpleItem(ModItems.FORGOTTEN_MATERIAL);
        simpleItem(ModItems.COPPER_WIRE);
        simpleItem(ModItems.METAL_FRAME);
        simpleItem(ModItems.RUBBER);
        simpleItem(ModItems.RAW_ENHANCEMENT_CORE);
        simpleItem(ModItems.UNCHARGED_ENHANCEMENT_CORE);
        simpleItem(ModItems.CHARGED_ENHANCEMENT_CORE);
        simpleItem(ModItems.MODIFIED_ENHANCEMENT_CORE);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(SolarUpgrade.MOD_ID,"item/" + item.getId().getPath()));
    }
    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(SolarUpgrade.MOD_ID,"item/" + item.getId().getPath()));
    }
}

package net.flakey.solarupgrade.datagen;

import net.flakey.solarupgrade.SolarUpgrade;
import net.flakey.solarupgrade.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SolarUpgrade.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.MATERIAL_ORE);
        blockWithItem(ModBlocks.SOLAR_ARRAY);
        blockWithItem(ModBlocks.SOLAR_PANEL);
        blockWithItem(ModBlocks.SOLAR_CHARGER);
        blockWithItem(ModBlocks.ENHANCEMENT_TABLE);
        blockWithItem(ModBlocks.INSULATED_WIRE);

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}

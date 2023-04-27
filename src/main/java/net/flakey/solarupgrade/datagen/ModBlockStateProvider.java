package net.flakey.solarupgrade.datagen;

import net.flakey.solarupgrade.SolarUpgrade;
import net.flakey.solarupgrade.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SolarUpgrade.MOD_ID, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.END_FORGOTTEN_ORE);
        blockWithItem(ModBlocks.ENHANCEMENT_TABLE);

        /* RUBBER TREE :(
        logBlock(((RotatedPillarBlock) ModBlocks.RUBBER_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.RUBBER_WOOD.get(), blockTexture(ModBlocks.RUBBER_LOG.get()), blockTexture(ModBlocks.RUBBER_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_RUBBER_LOG.get(), new ResourceLocation(SolarUpgrade.MOD_ID, "block/stripped_rubber_log"),
                new ResourceLocation(SolarUpgrade.MOD_ID, "block/stripped_rubber_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_RUBBER_WOOD.get(), new ResourceLocation(SolarUpgrade.MOD_ID, "block/stripped_rubber_log"),
                new ResourceLocation(SolarUpgrade.MOD_ID, "block/stripped_rubber_log_top"));
        blockWithItem(ModBlocks.RUBBER_PLANKS);
        blockWithItem(ModBlocks.RUBBER_LEAVES);
        saplingBlock(ModBlocks.RUBBER_SAPLING);

        simpleBlockItem(ModBlocks.RUBBER_LOG.get(), models().withExistingParent("solarupgrade:rubber_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.RUBBER_WOOD.get(), models().withExistingParent("solarupgrade:rubber_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_RUBBER_LOG.get(), models().withExistingParent("solarupgrade:stripped_rubber_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_RUBBER_WOOD.get(), models().withExistingParent("solarupgrade:stripped_rubber_wood", "minecraft:block/cube_column"));

        RUBBER TREE :( */


    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    /* RUBBER TREE :(
    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    RUBBER TREE :(*/
}

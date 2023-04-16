package net.flakey.solarupgrade.datagen;

import net.flakey.solarupgrade.block.ModBlocks;
import net.flakey.solarupgrade.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    protected ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.SOLAR_PANEL.get());
        dropSelf(ModBlocks.SOLAR_ARRAY.get());
        dropSelf(ModBlocks.SOLAR_CHARGER.get());
        dropSelf(ModBlocks.ENHANCEMENT_TABLE.get());
        dropSelf(ModBlocks.INSULATED_WIRE.get());

        add(ModBlocks.END_FORGOTTEN_ORE.get(),
                (block -> createOreDrop(ModBlocks.END_FORGOTTEN_ORE.get(), ModItems.FORGOTTEN_MATERIAL.get())));

        this.dropSelf(ModBlocks.RUBBER_LOG.get());
        this.dropSelf(ModBlocks.RUBBER_WOOD.get());
        this.dropSelf(ModBlocks.RUBBER_PLANKS.get());
        this.dropSelf(ModBlocks.STRIPPED_RUBBER_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_RUBBER_WOOD.get());
        this.dropSelf(ModBlocks.RUBBER_SAPLING.get());

        this.add(ModBlocks.RUBBER_LEAVES.get(), (block ->
                createLeavesDrops(block, ModBlocks.RUBBER_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)));

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}

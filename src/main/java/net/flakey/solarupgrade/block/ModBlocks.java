package net.flakey.solarupgrade.block;

import com.mojang.blaze3d.shaders.Uniform;
import net.flakey.solarupgrade.SolarUpgrade;
import net.flakey.solarupgrade.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.rmi.registry.Registry;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SolarUpgrade.MOD_ID);

    public static final RegistryObject<Block> SOLAR_CHARGER = registerBlock("solar_charger",
        () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                .strength(5f).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final RegistryObject<Block> SOLAR_PANEL = registerBlock("solar_panel",
        () -> new Block(BlockBehaviour.Properties.of(Material.GLASS)
                .strength(4f).requiresCorrectToolForDrops().sound(SoundType.GLASS)));

    public static final RegistryObject<Block> SOLAR_ARRAY = registerBlock("solar_array",
        () -> new Block(BlockBehaviour.Properties.of(Material.GLASS)
                .strength(4f).requiresCorrectToolForDrops().sound(SoundType.GLASS)));

    public static final RegistryObject<Block> MATERIAL_ORE = registerBlock("material_ore",
        () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                .strength(10f).requiresCorrectToolForDrops().sound(SoundType.STONE), UniformInt.of(4, 8)));

    public static final RegistryObject<Block> INSULATED_WIRE = registerBlock("insulated_wire",
        () -> new Block(BlockBehaviour.Properties.of(Material.WOOL)
                .strength(0.3f).sound(SoundType.WOOL)));

    public static final RegistryObject<Block> ENHANCEMENT_TABLE = registerBlock("enhancement_table",
        () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                .strength(5f).requiresCorrectToolForDrops().sound(SoundType.STONE)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }


    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}

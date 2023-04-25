package net.flakey.solarupgrade.datagen;

import net.flakey.solarupgrade.SolarUpgrade;
import net.flakey.solarupgrade.block.ModBlocks;
import net.flakey.solarupgrade.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        oreSmelting(consumer, List.of(ModItems.SILICON_COMPOSITE.get()), RecipeCategory.MISC,
                ModItems.SILICON.get(), 0.7f, 400, "silicon");
        oreBlasting(consumer, List.of(ModItems.SILICON_COMPOSITE.get()), RecipeCategory.MISC,
                ModItems.SILICON.get(), 0.7f, 200, "silicon");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SILICON_COMPOSITE.get(), 8)
                .requires(Blocks.SAND, 4)
                .requires(Items.QUARTZ, 4)
                .unlockedBy("has_sand", has(Blocks.SAND))
                .unlockedBy("has_quartz", has(Items.QUARTZ))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SOLAR_PANEL.get(), 1)
                .define('#', ModItems.SOLAR_CELL.get())
                .define('$', ModItems.METAL_FRAME.get())
                .define('&', ModItems.COPPER_WIRE.get())
                .pattern("###")
                .pattern("$&$")
                .pattern("$$$")
                .unlockedBy("has_solar_cell", has(ModItems.SOLAR_CELL.get()))
                .unlockedBy("has_metal_frame", has(ModItems.METAL_FRAME.get()))
                .unlockedBy("has_copper_wire", has(ModItems.COPPER_WIRE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SOLAR_CELL.get(), 1)
                .define('#', ModItems.GLASS_SHEET.get())
                .define('$', ModItems.SILICON.get())
                .define('&', ModItems.COPPER_WIRE.get())
                .pattern("###")
                .pattern("$&$")
                .pattern("&$&")
                .unlockedBy("has_glass_sheet", has(ModItems.GLASS_SHEET.get()))
                .unlockedBy("has_silicon", has(ModItems.SILICON.get()))
                .unlockedBy("has_copper_wire", has(ModItems.COPPER_WIRE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.METAL_FRAME.get(), 1)
                .define('#', Items.IRON_INGOT)
                .define('$', Items.IRON_BARS)
                .define('&', Items.IRON_BLOCK)
                .pattern("#&#")
                .pattern("$#$")
                .pattern("#$#")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_iron_bars", has(Items.IRON_BARS))
                .unlockedBy("has_iron_block", has(Items.IRON_BLOCK))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ADVANCED_SOLAR_PANEL.get(), 1)
                .define('#', ModBlocks.SOLAR_PANEL.get())
                .define('$', ModItems.FORGOTTEN_MATERIAL.get())
                .pattern("###")
                .pattern("#$#")
                .pattern("###")
                .unlockedBy("has_solar_panel", has(ModBlocks.SOLAR_PANEL.get()))
                .unlockedBy("has_forgotten_material", has(ModItems.FORGOTTEN_MATERIAL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SOLAR_CHARGER.get(), 1)
                .define('#', ModItems.SILICON.get())
                .define('$', ModItems.METAL_FRAME.get())
                .define('&', ModItems.SOLAR_CELL.get())
                .define('@', ModItems.COPPER_WIRE.get())
                .pattern("$#$")
                .pattern("@&@")
                .pattern("$@$")
                .unlockedBy("has_silicon", has(ModItems.SILICON.get()))
                .unlockedBy("has_metal_frame", has(ModItems.METAL_FRAME.get()))
                .unlockedBy("has_copper_wire", has(ModItems.COPPER_WIRE.get()))
                .unlockedBy("has_solar_cell", has(ModItems.SOLAR_CELL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ENHANCEMENT_TABLE.get(), 1)
                .define('#', ModItems.SILICON.get())
                .define('$', ModItems.METAL_FRAME.get())
                .define('&', ModItems.CHARGED_ENHANCEMENT_CORE.get())
                .define('@', ModItems.COPPER_WIRE.get())
                .define('!', Blocks.ENCHANTING_TABLE)
                .pattern("$#$")
                .pattern("@&@")
                .pattern("$!$")
                .unlockedBy("has_silicon", has(ModItems.SILICON.get()))
                .unlockedBy("has_metal_frame", has(ModItems.METAL_FRAME.get()))
                .unlockedBy("has_copper_wire", has(ModItems.COPPER_WIRE.get()))
                .unlockedBy("has_charged_enchantment_core", has(ModItems.CHARGED_ENHANCEMENT_CORE.get()))
                .unlockedBy("has_enchanting_table", has(Blocks.ENCHANTING_TABLE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.RAW_ENHANCEMENT_CORE.get(), 1)
                .define('#', ModItems.FORGOTTEN_MATERIAL.get())
                .define('$', Items.DIAMOND)
                .define('&', Items.IRON_INGOT)
                .define('@', Blocks.STONE)
                .pattern("@$@")
                .pattern("&#&")
                .pattern("@&@")
                .unlockedBy("has_forgotten_material", has(ModItems.FORGOTTEN_MATERIAL.get()))
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_stone", has(Blocks.STONE))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GLASS_SHEET.get(), 6)
                .define('#', Blocks.GLASS)
                .pattern("###")
                .unlockedBy("has_glass", has(Blocks.GLASS))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.COPPER_WIRE.get(), 6)
                .define('#', Items.COPPER_INGOT)
                .pattern("###")
                .unlockedBy("has_glass", has(Items.COPPER_INGOT))
                .save(consumer);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> p_250654_, List<ItemLike> p_250172_, RecipeCategory p_250588_, ItemLike p_251868_, float p_250789_, int p_252144_, String p_251687_) {
        oreCooking(p_250654_, RecipeSerializer.SMELTING_RECIPE, p_250172_, p_250588_, p_251868_, p_250789_, p_252144_, p_251687_, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> p_248775_, List<ItemLike> p_251504_, RecipeCategory p_248846_, ItemLike p_249735_, float p_248783_, int p_250303_, String p_251984_) {
        oreCooking(p_248775_, RecipeSerializer.BLASTING_RECIPE, p_251504_, p_248846_, p_249735_, p_248783_, p_250303_, p_251984_, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> p_250791_, RecipeSerializer<? extends AbstractCookingRecipe> p_251817_, List<ItemLike> p_249619_, RecipeCategory p_251154_, ItemLike p_250066_, float p_251871_, int p_251316_, String p_251450_, String p_249236_) {
        for(ItemLike itemlike : p_249619_) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), p_251154_, p_250066_, p_251871_, p_251316_, p_251817_).group(p_251450_)
                    .unlockedBy(getHasName(itemlike), has(itemlike)).save(p_250791_, new ResourceLocation(SolarUpgrade.MOD_ID, getItemName(p_250066_)) + p_249236_ + "_" + getItemName(itemlike));
        }

    }

    //WHEN ADDING MORE OF THESE ^^ ADD "new ResourceLocation(SolarUpgrade.MOD_ID)"
}

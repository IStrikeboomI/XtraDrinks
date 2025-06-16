package Strikeboom.xtradrinks.recipes.liquid_dehydrator;

import Strikeboom.xtradrinks.init.XtraDrinksRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.List;

public final class LiquidDehydratorRecipe implements Recipe<LiquidDehydratorRecipeInput> {
    public final FluidStack INPUT;
    public final ItemStack OUTPUT;

    LiquidDehydratorRecipe(FluidStack input, ItemStack output) {
        this.INPUT = input;
        this.OUTPUT = output;
    }

    @Override
    public boolean matches(LiquidDehydratorRecipeInput input, Level level) {
        return !level.isClientSide && input.input().is(INPUT.getFluid());
    }

    @Override
    public ItemStack assemble(LiquidDehydratorRecipeInput input, HolderLookup.Provider registries) {
        return OUTPUT.copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<LiquidDehydratorRecipeInput>> getSerializer() {
        return XtraDrinksRecipes.LIQUID_DEHYDRATOR.get();
    }

    @Override
    public RecipeType<? extends Recipe<LiquidDehydratorRecipeInput>> getType() {
        return XtraDrinksRecipes.LIQUID_DEHYDRATOR_TYPE.get();
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return XtraDrinksRecipes.LIQUID_DEHYDRATOR_CATEGORY.get();
    }
    @Override
    public List<RecipeDisplay> display() {
        return Recipe.super.display();
    }

}

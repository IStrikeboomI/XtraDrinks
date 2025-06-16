package Strikeboom.xtradrinks.recipes.dehydrator;

import Strikeboom.xtradrinks.init.XtraDrinksBlocks;
import Strikeboom.xtradrinks.init.XtraDrinksRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;

import java.util.List;

public final class DehydratorRecipe implements Recipe<SingleRecipeInput> {
    public final Ingredient INPUT;
    public final ItemStack OUTPUT;

    public DehydratorRecipe(Ingredient input, ItemStack output) {
        this.INPUT = input;
        this.OUTPUT = output;
    }

    @Override
    public boolean matches(SingleRecipeInput pInv, Level pLevel) {
        return !pLevel.isClientSide && INPUT.test(pInv.getItem(0));
    }

    @Override
    public ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider registries) {
        return OUTPUT.copy();
    }


    @Override
    public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return XtraDrinksRecipes.DEHYDRATOR.get();
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return XtraDrinksRecipes.DEHYDRATOR_TYPE.get();
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.create(INPUT);
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return XtraDrinksRecipes.DEHYDRATOR_CATEGORY.get();
    }


    @Override
    public List<RecipeDisplay> display() {
        return List.of(new DehydratorRecipeDisplay(INPUT.display(),new SlotDisplay.ItemStackSlotDisplay(OUTPUT)));
    }
}

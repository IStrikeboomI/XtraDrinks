package Strikeboom.xtradrinks.recipes.liquid_dehydrator;

import Strikeboom.xtradrinks.init.XtraDrinksRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;

import java.util.List;

public record LiquidDehydratorRecipeInput(FluidStack input) implements RecipeInput {

    @Override
    public boolean isEmpty() {
        return input().isEmpty();
    }

    @Override
    public ItemStack getItem(int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public int size() {
        return 0;
    }
}

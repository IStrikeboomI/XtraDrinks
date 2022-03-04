package Strikeboom.xtradrinks.recipes.liquid_dehydrator;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class LiquidDehydratorRecipeHandler {
    private static final List<LiquidDehydratorRecipe> RECIPES = new ArrayList<>();

    public static void addRecipe(FluidStack input,ItemStack result) {
        if (result.isEmpty()) {
            return;
        }
        if (result.getCount() < 1 || result.getCount() > 64) {
            return;
        }
        if (doesFluidStackHaveRecipe(input)) {
            return;
        }
        RECIPES.add(new LiquidDehydratorRecipe(input,result));
    }

    public static boolean doesFluidStackHaveRecipe(FluidStack fluid) {
        for (LiquidDehydratorRecipe recipe : RECIPES) {
            if (recipe.getInput().getFluid() == fluid.getFluid()) {
                return true;
            }
        }
        return false;
    }
    public static ItemStack getItemStackFromFluidStack(FluidStack fluidStack) {
        for (LiquidDehydratorRecipe recipe : RECIPES) {
            if (recipe.getInput().getFluid() == fluidStack.getFluid()) {
                return recipe.getOutput();
            }
        }
        return ItemStack.EMPTY;
    }
    public static FluidStack getFluidStackFromItemStack(ItemStack itemStack) {
        for (LiquidDehydratorRecipe recipe : RECIPES) {
            if (recipe.getOutput().sameItem(itemStack)) {
                return recipe.getInput();
            }
        }
        return FluidStack.EMPTY;
    }
    public static FluidStack getFluidStackFromFluidStackInput(FluidStack fluidStack) {
        for (LiquidDehydratorRecipe recipe : RECIPES) {
            if (recipe.getInput().isFluidEqual(fluidStack)) {
                return recipe.getInput();
            }
        }
        return FluidStack.EMPTY;
    }
    public static List<LiquidDehydratorRecipe> getRecipes() {
        return RECIPES;
    }
}

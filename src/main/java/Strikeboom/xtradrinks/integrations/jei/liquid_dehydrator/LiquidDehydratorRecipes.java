package Strikeboom.xtradrinks.integrations.jei.liquid_dehydrator;

import Strikeboom.xtradrinks.blocks.LiquidDehydrator;
import Strikeboom.xtradrinks.guis.tileentity.recipes.dehydrator.DehydratorRecipe;
import Strikeboom.xtradrinks.guis.tileentity.recipes.dehydrator.DehydratorRecipeHandler;
import Strikeboom.xtradrinks.guis.tileentity.recipes.liquid_dehydrator.LiquidDehydratorHandler;
import Strikeboom.xtradrinks.guis.tileentity.recipes.liquid_dehydrator.LiquidDehydratorRecipe;
import Strikeboom.xtradrinks.integrations.jei.dehydrator.DehydratorRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class LiquidDehydratorRecipes {
    public static List<LiquidDehydratorRecipeWrapper> recipes() {
        List<LiquidDehydratorRecipeWrapper> recipeWrappers = new ArrayList<>();
        for (LiquidDehydratorRecipe recipe: LiquidDehydratorHandler.recipes()) {
            recipeWrappers.add(new LiquidDehydratorRecipeWrapper(new ItemStack(recipe.getItem(),recipe.getCount(),recipe.getMetadata()),new FluidStack(recipe.getFluid(),recipe.getFluidCount())));
        }
        return recipeWrappers;
    }
}

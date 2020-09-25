package Strikeboom.xtradrinks.integrations.jei.liquid_dehydrator;

import Strikeboom.xtradrinks.guis.tileentity.recipes.liquid_dehydrator.LiquidDehydratorHandler;
import Strikeboom.xtradrinks.guis.tileentity.recipes.liquid_dehydrator.LiquidDehydratorRecipe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class LiquidDehydratorRecipes {
    public static List<LiquidDehydratorRecipeWrapper> recipes() {
        List<LiquidDehydratorRecipeWrapper> recipeWrappers = new ArrayList<>();

        LiquidDehydratorHandler.recipes().forEach(recipe -> recipeWrappers.add(new LiquidDehydratorRecipeWrapper(new ItemStack(recipe.getItem(),recipe.getCount(),recipe.getMetadata()),new FluidStack(recipe.getFluid(),recipe.getFluidCount()))));
        return recipeWrappers;
    }
}

package Strikeboom.xtradrinks.integrations.jei.dehydrator;

import Strikeboom.xtradrinks.guis.tileentity.recipes.dehydrator.DehydratorRecipe;
import Strikeboom.xtradrinks.guis.tileentity.recipes.dehydrator.DehydratorRecipeHandler;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DehydratorRecipes {
    public static List<DehydratorRecipeWrapper> recipes() {
        List<DehydratorRecipeWrapper> recipeWrappers = new ArrayList<>();
        for (DehydratorRecipe recipe: DehydratorRecipeHandler.getRecipes()) {
            recipeWrappers.add(new DehydratorRecipeWrapper(new ItemStack(recipe.getItem()),new ItemStack(recipe.getStackItem(),recipe.getStackCount())));
        }
        return recipeWrappers;
    }
}

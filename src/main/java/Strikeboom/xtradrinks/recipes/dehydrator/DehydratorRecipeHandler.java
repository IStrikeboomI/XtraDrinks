package Strikeboom.xtradrinks.recipes.dehydrator;

import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class DehydratorRecipeHandler {
    private static final List<DehydratorRecipe> RECIPES = new ArrayList<>();

    /**
     * Adds a dehydrator recipe
     * @param input item output input, should only be size of 1
     * @param result item output output, can be any size
     */
    public static void addRecipe(@Nonnull ItemStack input, @Nonnull ItemStack result) {
        if (doesItemHaveRecipe(input)) {
            return;
        }
        if (result.isEmpty()) {
            return;
        }
        if (result.getCount() < 1) {
            result.setCount(1);
        }
        RECIPES.add(new DehydratorRecipe(input,result));
    }


    public static boolean doesItemHaveRecipe(ItemStack item) {
        for (DehydratorRecipe recipe:RECIPES) {
            if (recipe.getInput().sameItem(item)) {
                return true;
            }
        }
        return false;
    }
    public static ItemStack getItemStackOutputFromInput(ItemStack item) {
        for (DehydratorRecipe recipe:RECIPES) {
            if (recipe.getInput().sameItem(item)) {
                return recipe.getOutput();
            }
        }
        return ItemStack.EMPTY;
    }

    public static List<DehydratorRecipe> getRecipes() {
        return RECIPES;
    }
}
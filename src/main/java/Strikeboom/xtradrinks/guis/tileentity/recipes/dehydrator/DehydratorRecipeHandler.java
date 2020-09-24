package Strikeboom.xtradrinks.guis.tileentity.recipes.dehydrator;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class DehydratorRecipeHandler {
    private static final List<DehydratorRecipe> RECIPES = new ArrayList<>();

    public static void addRecipe(@Nonnull Item input, @Nonnull ItemStack result) {
        if (doesItemHaveRecipe(input)) {
            XtraDrinks.logger.warn("There is already a recipe for: " + input.getRegistryName() + ", IGNORING!");
            return;
        }
        if (result.getMaxStackSize() > 64) {
            XtraDrinks.logger.warn("Cannot have an ItemStack result with a size over 64, IGNORING!");
            return;
        }
        if (result.getMetadata() > 0) {
            XtraDrinks.logger.warn("Cannot have a recipe with metadata, IGNORING");
            return;
        }
        if (result.isEmpty()) {
            XtraDrinks.logger.warn("Cannot have a recipe with an empty ItemStack, IGNORING");
            return;
        }
        if (result.getCount() <1) {
            XtraDrinks.logger.warn("Cannot have an ItemStack result with a size under 1, setting to one!");
            result.setCount(1);
        }
        RECIPES.add(new DehydratorRecipe(input,result));
    }

    public static void addRecipe(Block block, ItemStack stack) {
        addRecipe(Item.getItemFromBlock(block), stack);
    }

    public static boolean doesItemHaveRecipe(Item item) {
        for (DehydratorRecipe recipe:RECIPES) {
            if (recipe.getItem() == item) {
                return true;
            }
        }
        return false;
    }
    public static ItemStack getItemStackFromItem(Item item) {
        DehydratorRecipe rec = null;
        for (DehydratorRecipe recipe:RECIPES) {
            if (recipe.getItem() == item) {
                rec = recipe;
            }
        }
        if (rec == null) {
            return ItemStack.EMPTY;
        }
        return new ItemStack(rec.getStackItem(),rec.getStackCount());
    }
    public static Item getItemFromRecipe(Item item){
        return getItemStackFromItem(item).getItem();
    }
    public static void removeRecipeFromItem(Item item){
        RECIPES.removeIf(recipe -> recipe.getItem() == item);
    }
    public static int getCountFromItem(Item item) {
        for (DehydratorRecipe recipe:RECIPES) {
            if (recipe.getItem() == item) {
                return recipe.getStackCount();
            }
        }

        return 0;
    }
    public static List<DehydratorRecipe> getRecipes() {
        return RECIPES;
    }
}

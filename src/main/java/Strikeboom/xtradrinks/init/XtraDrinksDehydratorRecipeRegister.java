package Strikeboom.XtraDrinks.init;

import Strikeboom.XtraDrinks.recipes.dehydrator.DehydratorRecipeHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class XtraDrinksDehydratorRecipeRegister {
    public static void init() {
        DehydratorRecipeHandler.addRecipe(new ItemStack(XtraDrinksItems.GRAPE.get()),new ItemStack(XtraDrinksItems.DEHYDRATED_GRAPE.get()));
        DehydratorRecipeHandler.addRecipe(new ItemStack(XtraDrinksItems.PINEAPPLE.get()),new ItemStack(XtraDrinksItems.DEHYDRATED_PINEAPPLE.get()));
        DehydratorRecipeHandler.addRecipe(new ItemStack(XtraDrinksItems.LEMON.get()),new ItemStack(XtraDrinksItems.DEHYDRATED_LEMON.get()));
        DehydratorRecipeHandler.addRecipe(new ItemStack(XtraDrinksItems.LIME.get()),new ItemStack(XtraDrinksItems.DEHYDRATED_LIME.get()));
        DehydratorRecipeHandler.addRecipe(new ItemStack(XtraDrinksItems.COCONUT.get()),new ItemStack(XtraDrinksItems.DEHYDRATED_COCONUT.get()));
        DehydratorRecipeHandler.addRecipe(new ItemStack(Items.APPLE),new ItemStack(XtraDrinksItems.DEHYDRATED_APPLE.get()));
        DehydratorRecipeHandler.addRecipe(new ItemStack(XtraDrinksItems.ORANGE.get()),new ItemStack(XtraDrinksItems.DEHYDRATED_ORANGE.get()));
        DehydratorRecipeHandler.addRecipe(new ItemStack(XtraDrinksItems.CRANBERRY.get()),new ItemStack(XtraDrinksItems.DEHYDRATED_CRANBERRY.get(), 2));
        DehydratorRecipeHandler.addRecipe(new ItemStack(XtraDrinksItems.BLACKBERRY.get()),new ItemStack(XtraDrinksItems.DEHYDRATED_BLACKBERRY.get(), 2));
        DehydratorRecipeHandler.addRecipe(new ItemStack(XtraDrinksItems.BLUEBERRY.get()),new ItemStack(XtraDrinksItems.DEHYDRATED_BLUEBERRY.get(), 2));
        DehydratorRecipeHandler.addRecipe(new ItemStack(Items.BEETROOT),new ItemStack(XtraDrinksItems.DEHYDRATED_BEETROOT.get()));
        DehydratorRecipeHandler.addRecipe(new ItemStack(Items.POTATO),new ItemStack(XtraDrinksItems.DEHYDRATED_POTATO.get()));
        DehydratorRecipeHandler.addRecipe(new ItemStack(Items.CARROT),new ItemStack(XtraDrinksItems.DEHYDRATED_CARROT.get()));
        DehydratorRecipeHandler.addRecipe(new ItemStack(XtraDrinksItems.POMEGRANATE.get()),new ItemStack(XtraDrinksItems.DEHYDRATED_POMEGRANATE.get()));
    }
}

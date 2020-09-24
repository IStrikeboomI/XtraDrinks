package Strikeboom.xtradrinks.guis.tileentity.recipes.dehydrator;

import Strikeboom.xtradrinks.init.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class DehydratorRecipeRegister {
    public static void init() {
        DehydratorRecipeHandler.addRecipe(ModItems.grape,new ItemStack(ModItems.dehydrated_grape));
        DehydratorRecipeHandler.addRecipe(ModItems.pineapple,new ItemStack(ModItems.dehydrated_pineapple));
        DehydratorRecipeHandler.addRecipe(ModItems.lemon,new ItemStack(ModItems.dehydrated_lemon));
        DehydratorRecipeHandler.addRecipe(ModItems.lime,new ItemStack(ModItems.dehydrated_lime));
        DehydratorRecipeHandler.addRecipe(ModItems.coconut,new ItemStack(ModItems.dehydrated_coconut));
        DehydratorRecipeHandler.addRecipe(Items.APPLE,new ItemStack(ModItems.dehydrated_apple));
        DehydratorRecipeHandler.addRecipe(ModItems.orange,new ItemStack(ModItems.dehydrated_orange));
        DehydratorRecipeHandler.addRecipe(ModItems.cranberry,new ItemStack(ModItems.dehydrated_cranberry, 2));
        DehydratorRecipeHandler.addRecipe(ModItems.blackberry,new ItemStack(ModItems.dehydrated_blackberry, 2));
        DehydratorRecipeHandler.addRecipe(ModItems.blueberry,new ItemStack(ModItems.dehydrated_blueberry, 2));
        DehydratorRecipeHandler.addRecipe(Items.BEETROOT,new ItemStack(ModItems.dehydrated_beetroot));
        DehydratorRecipeHandler.addRecipe(Items.POTATO,new ItemStack(ModItems.dehydrated_potato));
        DehydratorRecipeHandler.addRecipe(Items.CARROT,new ItemStack(ModItems.dehydrated_carrot));
        DehydratorRecipeHandler.addRecipe(ModItems.pomegranate,new ItemStack(ModItems.dehydrated_pomegranate));
    }
}

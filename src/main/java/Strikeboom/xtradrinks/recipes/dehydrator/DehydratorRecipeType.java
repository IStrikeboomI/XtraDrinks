package Strikeboom.xtradrinks.recipes.dehydrator;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;

public class DehydratorRecipeType implements RecipeType<DehydratorRecipe> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID,"dehydrator");
}
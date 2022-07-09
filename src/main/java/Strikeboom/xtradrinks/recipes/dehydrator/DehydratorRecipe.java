package Strikeboom.xtradrinks.recipes.dehydrator;

import Strikeboom.xtradrinks.init.XtraDrinksBlocks;
import Strikeboom.xtradrinks.init.XtraDrinksRecipes;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public final class DehydratorRecipe implements Recipe<Container> {
    private final ResourceLocation ID;
    private final Ingredient INPUT;
    private final ItemStack OUTPUT;

    DehydratorRecipe(ResourceLocation id, Ingredient input, ItemStack output) {
        this.ID = id;
        this.INPUT = input;
        this.OUTPUT = output;
    }

    @Override
    public boolean matches(Container pInv, Level pLevel) {
        return INPUT.test(pInv.getItem(0));
    }

    @Override
    public ItemStack assemble(Container pInv) {
        return OUTPUT.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth == 1 && pHeight == 1;
    }

    @Override
    public ItemStack getResultItem() {
        return OUTPUT.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(INPUT,INPUT);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return XtraDrinksRecipes.DEHYDRATOR.get();
    }

    @Override
    public RecipeType<?> getType() {
        return XtraDrinksRecipes.DEHYDRATOR_TYPE.get();
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(XtraDrinksBlocks.DEHYDRATOR.get());
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}

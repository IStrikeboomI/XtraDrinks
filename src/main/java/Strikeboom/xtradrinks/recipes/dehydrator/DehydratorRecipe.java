package Strikeboom.XtraDrinks.recipes.dehydrator;

import Strikeboom.XtraDrinks.init.XtraDrinksBlocks;
import Strikeboom.XtraDrinks.init.XtraDrinksRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public final class DehydratorRecipe implements IRecipe<IInventory> {
    private final ResourceLocation ID;
    private final Ingredient INPUT;
    private final ItemStack OUTPUT;

    DehydratorRecipe(ResourceLocation id, Ingredient input, ItemStack output) {
        this.ID = id;
        this.INPUT = input;
        this.OUTPUT = output;
    }

    @Override
    public boolean matches(IInventory pInv, World pLevel) {
        return INPUT.test(pInv.getItem(0));
    }

    @Override
    public ItemStack assemble(IInventory pInv) {
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
    public IRecipeSerializer<?> getSerializer() {
        return XtraDrinksRecipes.DEHYDRATOR.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return XtraDrinksRecipes.DEHYDRATOR_TYPE;
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

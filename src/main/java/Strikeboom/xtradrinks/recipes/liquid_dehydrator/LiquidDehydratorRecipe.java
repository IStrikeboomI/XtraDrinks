package Strikeboom.XtraDrinks.recipes.liquid_dehydrator;

import Strikeboom.XtraDrinks.init.XtraDrinksBlocks;
import Strikeboom.XtraDrinks.init.XtraDrinksRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public final class LiquidDehydratorRecipe implements IRecipe<IInventory> {
    private final ResourceLocation ID;
    private final FluidStack INPUT;
    private final ItemStack OUTPUT;

    LiquidDehydratorRecipe(ResourceLocation id, FluidStack input, ItemStack output) {
        this.ID = id;
        this.INPUT = input;
        this.OUTPUT = output;
    }

    public FluidStack getInput() {
        return INPUT;
    }

    @Override
    public boolean matches(IInventory pInv, World pLevel) {
        return true;
    }

    @Override
    public ItemStack assemble(IInventory pInv) {
        return OUTPUT;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return OUTPUT.copy();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(XtraDrinksBlocks.LIQUID_DEHYDRATOR.get());
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return XtraDrinksRecipes.LIQUID_DEHYDRATOR.get();
    }

    @Override
    public IRecipeType<?> getType() {
        return XtraDrinksRecipes.LIQUID_DEHYDRATOR_TYPE;
    }
}

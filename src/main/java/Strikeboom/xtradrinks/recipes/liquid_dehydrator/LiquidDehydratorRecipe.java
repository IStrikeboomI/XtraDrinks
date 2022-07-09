package Strikeboom.xtradrinks.recipes.liquid_dehydrator;

import Strikeboom.xtradrinks.init.XtraDrinksBlocks;
import Strikeboom.xtradrinks.init.XtraDrinksRecipes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;

public final class LiquidDehydratorRecipe implements Recipe<Container> {
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
    public boolean matches(Container pInv, Level pLevel) {
        return true;
    }

    @Override
    public ItemStack assemble(Container pInv) {
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
    public RecipeSerializer<?> getSerializer() {
        return XtraDrinksRecipes.LIQUID_DEHYDRATOR.get();
    }

    @Override
    public RecipeType<?> getType() {
        return XtraDrinksRecipes.LIQUID_DEHYDRATOR_TYPE.get();
    }
}

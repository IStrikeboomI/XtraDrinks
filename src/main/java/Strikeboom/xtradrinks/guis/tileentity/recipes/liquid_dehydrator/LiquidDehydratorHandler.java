package Strikeboom.xtradrinks.guis.tileentity.recipes.liquid_dehydrator;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class LiquidDehydratorHandler {
    private static final List<LiquidDehydratorRecipe> RECIPES = new ArrayList<>();

    public static void addRecipe(ItemStack result, FluidStack input) {
        if (result.isEmpty()) {
            XtraDrinks.logger.warn("Cannot have a recipe with an empty ItemStack, IGNORING");
            return;
        }
        if (result.getCount() < 1 || result.getCount() > 64) {
            XtraDrinks.logger.warn("ItemStack amount is invalid, IGNORING");
            return;
        }
        if (doesFluidHaveRecipe(input.getFluid())) {
            XtraDrinks.logger.warn("Already a recipe for fluid: "+ input.getUnlocalizedName()+",IGNORING");
            return;
        }
        RECIPES.add(new LiquidDehydratorRecipe(result, input));
    }
    public static void addRecipe(Item item,FluidStack fluidStack) {
        addRecipe(new ItemStack(item),fluidStack);
    }
    public static void addRecipe(Block block, FluidStack fluidStack) {
        addRecipe(new ItemStack(block),fluidStack);
    }
    public static void addRecipe(Item item,Fluid fluid, int amount) {
        addRecipe(new ItemStack(item),new FluidStack(fluid,amount));
    }
    public static void addRecipe(Block block,Fluid fluid, int amount) {
        addRecipe(new ItemStack(block),new FluidStack(fluid,amount));
    }
    public static void addRecipe(ItemStack stack,Fluid fluid, int amount) {
        addRecipe(stack,new FluidStack(fluid,amount));
    }
    public static boolean doesFluidHaveRecipe(Fluid fluid) {
        for (LiquidDehydratorRecipe recipe : RECIPES) {
            if (recipe.getFluid() == fluid) {
                return true;
            }
        }
        return false;
    }
    public static ItemStack getItemStackFromFluidStack(FluidStack fluidStack) {
        for (LiquidDehydratorRecipe recipe : RECIPES) {

            if (recipe.getFluid() == fluidStack.getFluid() && recipe.getFluidCount() <= fluidStack.amount) {
                return new ItemStack(recipe.getItem(),recipe.getCount(),recipe.getMetadata());
            }
        }
        return ItemStack.EMPTY;
    }
    public static int getItemStackAmountFromFluidStack(FluidStack fluidStack) {
        return getItemStackFromFluidStack(fluidStack).getCount();
    }
    public static Item getItemFromFluidStack(FluidStack fluidStack) {
        return getItemStackFromFluidStack(fluidStack).getItem();
    }
    public static void removeRecipeFromFluidStack(FluidStack fluidStack) {
        RECIPES.removeIf(recipe -> recipe.getFluid() == fluidStack.getFluid()
                && recipe.getFluidCount() >= fluidStack.amount);
    }
    public static FluidStack getFluidStackFromItem(ItemStack itemStack) {
        for (LiquidDehydratorRecipe recipe : RECIPES) {
            if (recipe.getItem() == itemStack.getItem() && recipe.getMetadata() == itemStack.getMetadata()) {
                return new FluidStack(recipe.getFluid(),recipe.getFluidCount());
            }
        }
        return null;
    }
    public static int getFluidStackAmountFromItem(ItemStack item) {
        FluidStack fs = getFluidStackFromItem(item);
        return fs == null ? 0: fs.amount;
    }
    public static int getFluidStackRecipeAmountFromFluid(Fluid fluid) {
        for (LiquidDehydratorRecipe recipe:RECIPES) {
            if (recipe.getFluid() == fluid) {

                return recipe.getFluidCount();
            }
        }
        return 0;
    }
    public static List<LiquidDehydratorRecipe> recipes() {
        return RECIPES;
    }
}


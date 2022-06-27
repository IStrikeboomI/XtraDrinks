package Strikeboom.XtraDrinks.init;

import Strikeboom.XtraDrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipeHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class XtraDrinksLiquidDehydratorRecipeRegister {
    public static void init() {
        LiquidDehydratorRecipeHandler.addRecipe(new FluidStack(XtraDrinksFluids.MOLTEN_FIZZIUM.get(),100),new ItemStack(XtraDrinksItems.FIZZIUM_SHARD.get()));
        LiquidDehydratorRecipeHandler.addRecipe(new FluidStack(XtraDrinksFluids.MOLTEN_LIQUADIUM.get(),100),new ItemStack(XtraDrinksItems.LIQUADIUM_SHARD.get()));
    }
}

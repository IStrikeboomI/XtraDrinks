package Strikeboom.xtradrinks.recipes.liquid_dehydrator;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public record LiquidDehydratorRecipe(FluidStack input, ItemStack output) {

    public FluidStack getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }
}

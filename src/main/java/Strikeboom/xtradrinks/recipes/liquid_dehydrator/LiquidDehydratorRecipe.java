package Strikeboom.XtraDrinks.recipes.liquid_dehydrator;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.Objects;

public final class LiquidDehydratorRecipe {
    private final FluidStack input;
    private final ItemStack output;

    LiquidDehydratorRecipe(FluidStack input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    public FluidStack input() {
        return input;
    }

    public ItemStack output() {
        return output;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        LiquidDehydratorRecipe that = (LiquidDehydratorRecipe) obj;
        return Objects.equals(this.input, that.input) &&
                Objects.equals(this.output, that.output);
    }

    @Override
    public int hashCode() {
        return Objects.hash(input, output);
    }

    @Override
    public String toString() {
        return "LiquidDehydratorRecipe[" +
                "input=" + input + ", " +
                "output=" + output + ']';
    }
}

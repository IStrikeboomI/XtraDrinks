package Strikeboom.xtradrinks.guis.tileentity.recipes.liquid_dehydrator;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class LiquidDehydratorRecipe {
    private final Item item;
    private final int count;
    private final int metadata;
    private final Fluid fluid;
    private final int fluidCount;

    public LiquidDehydratorRecipe(ItemStack stack, FluidStack fluidStack) {
        this.item = stack.getItem();
        this.count = stack.getCount();
        this.metadata = stack.getMetadata();
        this.fluid = fluidStack.getFluid();
        this.fluidCount = fluidStack.amount;
    }

    public Fluid getFluid() {
        return fluid;
    }

    public int getCount() {
        return count;
    }

    public int getFluidCount() {
        return fluidCount;
    }

    public int getMetadata() {
        return metadata;
    }

    public Item getItem() {
        return item;
    }
}

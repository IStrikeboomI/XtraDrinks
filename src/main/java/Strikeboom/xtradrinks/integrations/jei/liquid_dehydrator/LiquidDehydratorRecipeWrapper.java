package Strikeboom.xtradrinks.integrations.jei.liquid_dehydrator;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.Arrays;

public class LiquidDehydratorRecipeWrapper implements IRecipeWrapper {
    ItemStack output;
    FluidStack input;
    public LiquidDehydratorRecipeWrapper(ItemStack output, FluidStack input) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.FLUID, input);
        ingredients.setOutput(VanillaTypes.ITEM,output);
    }
}

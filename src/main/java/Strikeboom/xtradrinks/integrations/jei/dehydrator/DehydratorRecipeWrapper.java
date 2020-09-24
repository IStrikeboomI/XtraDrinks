package Strikeboom.xtradrinks.integrations.jei.dehydrator;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class DehydratorRecipeWrapper implements IRecipeWrapper {

    ItemStack input;
    ItemStack output;

    public DehydratorRecipeWrapper(ItemStack input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(VanillaTypes.ITEM, input);
        ingredients.setOutput(VanillaTypes.ITEM,output);
    }

}

package Strikeboom.xtradrinks.recipes.dehydrator;

import net.minecraft.world.item.ItemStack;

public record DehydratorRecipe(ItemStack input, ItemStack output) {

    public ItemStack getInput() {
        return this.input;
    }

    public ItemStack getOutput() {
        return output;
    }
}

package Strikeboom.XtraDrinks.guis.tileentities.itemhandlers;

import Strikeboom.XtraDrinks.recipes.dehydrator.DehydratorRecipeHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class DehydratorItemHandler extends ItemStackHandler {
    public DehydratorItemHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        if (slot == 1) {
            return false;
        }
        if (slot == 0) {
            return DehydratorRecipeHandler.doesItemHaveRecipe(stack);
        }
        return false;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot == 1) {
            return stack;
        }
        if (slot == 0) {
            if (DehydratorRecipeHandler.doesItemHaveRecipe(stack)) {
                return super.insertItem(slot, stack, simulate);
            } else {
                return stack;
            }
        }
        return super.insertItem(slot, stack, simulate);
    }
}

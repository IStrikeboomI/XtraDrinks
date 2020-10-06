package Strikeboom.xtradrinks.guis.tileentity.customs;

import Strikeboom.xtradrinks.guis.tileentity.recipes.dehydrator.DehydratorRecipeHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class DehydratorItemHandler extends ItemStackHandler {

    public DehydratorItemHandler(int size) {
        super(size);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot == 1) {
            return stack;
        }
        if (slot == 0) {
            if (DehydratorRecipeHandler.doesItemHaveRecipe(stack.getItem())) {
                return super.insertItem(slot, stack, simulate);
            } else {
                return stack;
            }
        }
        return super.insertItem(slot, stack, simulate);
    }

}

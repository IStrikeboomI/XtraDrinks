package Strikeboom.XtraDrinks.guis.tileentities.itemhandlers;

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
        return slot == 0;
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (slot == 1) {
            return stack;
        }
        return super.insertItem(slot, stack, simulate);
    }
}

package Strikeboom.xtradrinks.guis.blockentities.itemhandlers;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class DehydratorItemHandler extends ItemStackHandler {
    public DehydratorItemHandler(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
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

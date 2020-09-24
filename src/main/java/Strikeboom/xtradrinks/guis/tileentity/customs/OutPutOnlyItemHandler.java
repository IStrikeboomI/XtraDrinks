package Strikeboom.xtradrinks.guis.tileentity.customs;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

public class OutPutOnlyItemHandler extends ItemStackHandler {
    public OutPutOnlyItemHandler(int size) {
        super(size);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        return stack;
    }
}

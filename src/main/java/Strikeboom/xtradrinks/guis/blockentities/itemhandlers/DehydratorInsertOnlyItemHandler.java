package Strikeboom.xtradrinks.guis.blockentities.itemhandlers;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class DehydratorInsertOnlyItemHandler extends ItemStackHandler {
    ItemStackHandler handler;
    public DehydratorInsertOnlyItemHandler(ItemStackHandler handler) {
        super(handler.getSlots());
        this.handler = handler;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return handler.insertItem(slot, stack, simulate);
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if (slot == 1) {
            return handler.extractItem(1, amount, simulate);
        }
        return ItemStack.EMPTY;
    }
}

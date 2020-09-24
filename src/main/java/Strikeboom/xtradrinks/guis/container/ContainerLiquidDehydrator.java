package Strikeboom.xtradrinks.guis.container;

import Strikeboom.xtradrinks.guis.tileentity.TileEntityLiquidDehydrator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerLiquidDehydrator extends Container {
    IItemHandler handler;
    TileEntityLiquidDehydrator te;
    public ContainerLiquidDehydrator(IInventory playerInv, TileEntityLiquidDehydrator te) {
        this.handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,null);
        this.te = te;
        this.addSlotToContainer(new SlotItemHandler(handler,0,86,33));

        int xPos = 8;
        int yPos = 84;

        //draws hotbar
        for (int x = 0; x < 9; x++) {
            this.addSlotToContainer(new Slot(playerInv, x, xPos + x * 18, yPos + 58));
        }

        //draws the 27 main slots
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
            }
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(fromSlot);

        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();
            if (fromSlot < this.handler.getSlots()) {

                if (!this.mergeItemStack(current, handler.getSlots(), handler.getSlots() + 36, true))
                    return ItemStack.EMPTY;
            } else {

                if (!this.mergeItemStack(current, 0, 0, false))
                    return ItemStack.EMPTY;
            }

            if (current.getCount() == 0)
                slot.putStack(ItemStack.EMPTY);
            else
                slot.onSlotChanged();

            if (current.getCount() == previous.getCount())
                return null;
            slot.onTake(playerIn, current);
        }
        return previous;
    }
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.te.isUsableByPlayer(playerIn);
    }
    public void drain() {
        ((FluidTank)this.te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,null)).setFluid(null);
        this.te.getWorld().playSound(null,te.getPos(), SoundEvents.ITEM_BUCKET_EMPTY_LAVA, SoundCategory.AMBIENT,10f,1f);
    }

}

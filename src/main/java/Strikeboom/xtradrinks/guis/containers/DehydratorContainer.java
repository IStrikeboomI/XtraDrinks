package Strikeboom.XtraDrinks.guis.containers;

import Strikeboom.XtraDrinks.guis.tileentities.DehydratorTileEntity;
import Strikeboom.XtraDrinks.guis.tileentities.itemhandlers.DehydratorItemHandler;
import Strikeboom.XtraDrinks.init.XtraDrinksBlocks;
import Strikeboom.XtraDrinks.init.XtraDrinksContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class DehydratorContainer extends Container {
    public final DehydratorTileEntity blockEntity;
    private DehydratorItemHandler blockInventory;
    private final IWorldPosCallable ACCESS;

    public DehydratorContainer(int windowId, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, IWorldPosCallable access) {
        super(XtraDrinksContainers.DEHYDRATOR_CONTAINER.get(), windowId);
        this.ACCESS = access;
        blockEntity = (DehydratorTileEntity)player.getCommandSenderWorld().getBlockEntity(pos);
        if (blockEntity != null) {
            blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                blockInventory = (DehydratorItemHandler)h;
                addSlot(new SlotItemHandler(blockInventory,0,55,35));
                addSlot(new SlotItemHandler(blockInventory,1,116,35));
            });
        }

        int xPos = 8;
        int yPos = 84;

        //draws hotbar
        for (int x = 0; x < 9; x++) {
            addSlot(new Slot(playerInventory, x, xPos + x * 18, yPos + 58));
        }

        //draws the 27 main slots
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 9; x++) {
                addSlot(new Slot(playerInventory, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
            }
        }
    }
    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack previous = ItemStack.EMPTY;
        Slot slot = slots.get(index);

        if (slot.hasItem()) {
            ItemStack current = slot.getItem();
            previous = current.copy();
            if (index < this.blockInventory.getSlots()) {
                if (!this.moveItemStackTo(current, blockInventory.getSlots(), blockInventory.getSlots() + 36, true))
                    return ItemStack.EMPTY;
            } else {
                if (!this.moveItemStackTo(current, 0, blockInventory.getSlots(), false))
                    return ItemStack.EMPTY;
            }

            if (current.getCount() == 0)
                slot.set(ItemStack.EMPTY);
            else
                slot.setChanged();

            if (current.getCount() == previous.getCount())
                return ItemStack.EMPTY;
            slot.onTake(playerIn, current);
        }
        return previous;
    }
    @Override
    public boolean stillValid(PlayerEntity pPlayer) {
        return stillValid(ACCESS, pPlayer, XtraDrinksBlocks.DEHYDRATOR.get());
    }

}

package Strikeboom.XtraDrinks.guis.containers;

import Strikeboom.XtraDrinks.entity.GreenmanEntity;
import Strikeboom.XtraDrinks.init.XtraDrinksContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class GreenmanContainer extends Container {
    private ItemStackHandler entityInventory;
    private GreenmanEntity entity;
    public GreenmanContainer(int pContainerId, GreenmanEntity greenman, PlayerInventory playerInventory) {
        super(XtraDrinksContainers.GREENMAN_CONTAINER.get(), pContainerId);
        if (greenman != null) {
            entity = greenman;
            greenman.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                entityInventory = (ItemStackHandler) h;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        addSlot(new SlotItemHandler(
                                entityInventory
                                , i * 3 + j
                                , 62 + i * 18
                                , 17 + j * 18));
                    }
                }
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
            if (index < this.entityInventory.getSlots()) {
                if (!this.moveItemStackTo(current, entityInventory.getSlots(), entityInventory.getSlots() + 36, true))
                    return ItemStack.EMPTY;
            } else {
                if (!this.moveItemStackTo(current, 0, entityInventory.getSlots(), false))
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
        Vector3d pos = pPlayer.position();
        return entity == null || entity.mayInteract(new BlockPos(pos.x,pos.y,pos.z));
    }
}

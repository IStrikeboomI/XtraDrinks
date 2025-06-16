package Strikeboom.xtradrinks.menus;

import Strikeboom.xtradrinks.entity.GreenmanEntity;
import Strikeboom.xtradrinks.init.XtraDrinksMenus;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;


public class GreenmanMenu extends AbstractContainerMenu {
    private ItemStackHandler entityInventory;
    private GreenmanEntity entity;
    public GreenmanMenu(int pContainerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(pContainerId,playerInventory, (GreenmanEntity) playerInventory.player.getCommandSenderWorld().getEntity(extraData.readUUID()));
    }
    public GreenmanMenu(int pContainerId, Inventory playerInventory,GreenmanEntity greenman) {
        super(XtraDrinksMenus.GREENMAN_MENU.get(), pContainerId);
        if (greenman != null) {
            entity = greenman;
            entityInventory = greenman.HANDLER;
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
    public ItemStack quickMoveStack(Player playerIn, int index) {
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
    public boolean stillValid(Player pPlayer) {
        return entity == null || entity.mayInteract((ServerLevel) pPlayer.level(),pPlayer.getOnPos());
    }
}

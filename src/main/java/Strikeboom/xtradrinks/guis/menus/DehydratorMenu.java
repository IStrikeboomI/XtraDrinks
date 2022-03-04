package Strikeboom.xtradrinks.guis.menus;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.guis.blockentities.DehydratorBlockEntity;
import Strikeboom.xtradrinks.guis.blockentities.itemhandlers.DehydratorItemHandler;
import Strikeboom.xtradrinks.init.XtraDrinksBlocks;
import Strikeboom.xtradrinks.init.XtraDrinksMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class DehydratorMenu extends AbstractContainerMenu {
    public final DehydratorBlockEntity blockEntity;
    private DehydratorItemHandler blockInventory;

    public DehydratorMenu(int windowId, BlockPos pos, Inventory playerInventory, Player player) {
        super(XtraDrinksMenus.DEHYDRATOR_MENU.get(), windowId);
        blockEntity = (DehydratorBlockEntity)player.getCommandSenderWorld().getBlockEntity(pos);
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
    public ItemStack quickMoveStack(Player playerIn, int index) {
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
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), pPlayer, XtraDrinksBlocks.DEHYDRATOR.get());
    }
}

package Strikeboom.XtraDrinks.guis.tileentities;

import Strikeboom.XtraDrinks.guis.tileentities.itemhandlers.DehydratorInsertOnlyItemHandler;
import Strikeboom.XtraDrinks.guis.tileentities.itemhandlers.DehydratorItemHandler;
import Strikeboom.XtraDrinks.init.XtraDrinksConfig;
import Strikeboom.XtraDrinks.init.XtraDrinksTileEntities;
import Strikeboom.XtraDrinks.recipes.dehydrator.DehydratorRecipeHandler;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Objects;

public class DehydratorTileEntity extends TileEntity implements ITickableTileEntity {
    private final DehydratorItemHandler ITEM_STACK_HANDLER;
    private final LazyOptional<IItemHandler> ITEM_STACK_HANDLER_LAZY_OPTIONAL;
    private final DehydratorInsertOnlyItemHandler ITEM_STACK_MACHINE_HANDLER;
    private final LazyOptional<IItemHandler> ITEM_STACK_MACHINE_HANDLER_LAZY_OPTIONAL;
    private int cooldown = 0;
    private int delay;
    public DehydratorTileEntity() {
        super(XtraDrinksTileEntities.DEHYDRATOR_TILE_ENTITY.get());
        ITEM_STACK_HANDLER = new DehydratorItemHandler(2) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                level.sendBlockUpdated(worldPosition,getBlockState(),getBlockState(), 3);
            }
        };
        ITEM_STACK_HANDLER_LAZY_OPTIONAL = LazyOptional.of(() -> ITEM_STACK_HANDLER);
        ITEM_STACK_MACHINE_HANDLER  = new DehydratorInsertOnlyItemHandler(ITEM_STACK_HANDLER);
        ITEM_STACK_MACHINE_HANDLER_LAZY_OPTIONAL = LazyOptional.of(() -> ITEM_STACK_MACHINE_HANDLER);
        delay = XtraDrinksConfig.DEHYDRATOR_DELAY.get();
    }
    @Override
    public void setRemoved() {
        super.setRemoved();
        ITEM_STACK_HANDLER_LAZY_OPTIONAL.invalidate();
        ITEM_STACK_MACHINE_HANDLER_LAZY_OPTIONAL.invalidate();
    }

    @Override
    public CompoundNBT save(CompoundNBT pTag) {
        pTag.put("ItemStackHandler",ITEM_STACK_HANDLER.serializeNBT());
        CompoundNBT infoTag = new CompoundNBT();
        infoTag.putInt("Cooldown", cooldown);
        infoTag.putInt("Delay", delay);
        pTag.put("Info", infoTag);
        return super.save(pTag);
    }

    @Override
    public void load(BlockState p_230337_1_,CompoundNBT pTag) {
        if (pTag.contains("ItemStackHandler")) {
            ITEM_STACK_HANDLER.deserializeNBT(pTag.getCompound("ItemStackHandler"));
        }
        if (pTag.contains("Info")) {
            cooldown = pTag.getCompound("Info").getInt("Cooldown");
            delay = pTag.getCompound("Info").getInt("Delay");
        }
        super.load(p_230337_1_,pTag);
    }
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, final Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side != null) {
                return ITEM_STACK_MACHINE_HANDLER_LAZY_OPTIONAL.cast();
            }
            return ITEM_STACK_HANDLER_LAZY_OPTIONAL.cast();
        }
        return super.getCapability(cap,side);
    }
    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT tag = super.getUpdateTag();
        save(tag);
        return tag;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        if (tag != null) {
            load(state,tag);
        }
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(worldPosition,3,getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        // This is called client side: remember the current state of the values that we're interested in
        int oldcooldown = cooldown;
        int oldDelay = delay;
        ItemStackHandler oldItemStackHandler = ITEM_STACK_HANDLER;
        ItemStackHandler oldItemStackMachineHandler = ITEM_STACK_MACHINE_HANDLER;

        CompoundNBT tag = pkt.getTag();
        // This will call loadClientData()
        handleUpdateTag(null,tag);

        // If any of the values was changed we request a refresh of our model data and send a block update
        if (oldcooldown != cooldown || oldDelay != delay ||
                !Objects.equals(oldItemStackMachineHandler, ITEM_STACK_MACHINE_HANDLER) ||
                !Objects.equals(oldItemStackHandler, ITEM_STACK_HANDLER)) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getDelay() {
        return delay;
    }

    @Override
    public void tick() {
        delay = XtraDrinksConfig.DEHYDRATOR_DELAY.get();
        boolean shouldUpdate = false;
        if (
                !ITEM_STACK_HANDLER.getStackInSlot(0).isEmpty() &&
                        DehydratorRecipeHandler.doesItemHaveRecipe(ITEM_STACK_HANDLER.getStackInSlot(0))
                        && ITEM_STACK_HANDLER.getStackInSlot(1).getCount() + DehydratorRecipeHandler.getItemStackOutputFromInput(ITEM_STACK_HANDLER.getStackInSlot(0)).getCount() <= ITEM_STACK_HANDLER.getStackInSlot(1).getMaxStackSize()
                        && (ITEM_STACK_HANDLER.getStackInSlot(1).isEmpty()
                        || ITEM_STACK_HANDLER.getStackInSlot(1).sameItem(DehydratorRecipeHandler.getItemStackOutputFromInput(ITEM_STACK_HANDLER.getStackInSlot(0))))) {
            cooldown++;
            shouldUpdate = true;
        } else {
            if (cooldown != 0) {
                cooldown = 0;
                shouldUpdate = true;
            }
        }
        if (cooldown % this.delay == 0 && cooldown != 0) {
            cooldown = 0;
            if (ITEM_STACK_HANDLER.getStackInSlot(1).isEmpty()) {
                ITEM_STACK_HANDLER.setStackInSlot(1,DehydratorRecipeHandler.getItemStackOutputFromInput(ITEM_STACK_HANDLER.getStackInSlot(0)).copy());
            } else {
                ITEM_STACK_HANDLER.getStackInSlot(1).grow(DehydratorRecipeHandler.getItemStackOutputFromInput(ITEM_STACK_HANDLER.getStackInSlot(0)).getCount());
            }
            ITEM_STACK_HANDLER.getStackInSlot(0).shrink(1);
        }
        if (shouldUpdate) {
            setChanged();
            this.level.sendBlockUpdated(worldPosition,getBlockState(),getBlockState(),3);
        }
    }

}

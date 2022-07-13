package Strikeboom.xtradrinks.guis.blockentities;

import Strikeboom.xtradrinks.guis.blockentities.itemhandlers.DehydratorInsertOnlyItemHandler;
import Strikeboom.xtradrinks.guis.blockentities.itemhandlers.DehydratorItemHandler;
import Strikeboom.xtradrinks.init.XtraDrinksBlockEntities;
import Strikeboom.xtradrinks.init.XtraDrinksConfig;
import Strikeboom.xtradrinks.recipes.dehydrator.DehydratorRecipeSerializer;
import Strikeboom.xtradrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipeSerializer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Objects;

public class DehydratorBlockEntity extends BlockEntity {
    private final DehydratorItemHandler ITEM_STACK_HANDLER;
    private final LazyOptional<IItemHandler> ITEM_STACK_HANDLER_LAZY_OPTIONAL;
    private final DehydratorInsertOnlyItemHandler ITEM_STACK_MACHINE_HANDLER;
    private final LazyOptional<IItemHandler> ITEM_STACK_MACHINE_HANDLER_LAZY_OPTIONAL;
    private int cooldown = 0;
    private int delay;
    public DehydratorBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(XtraDrinksBlockEntities.DEHYDRATOR_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        ITEM_STACK_HANDLER = new DehydratorItemHandler(2) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                level.sendBlockUpdated(worldPosition,getBlockState(),getBlockState(), Block.UPDATE_ALL);
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
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("ItemStackHandler",ITEM_STACK_HANDLER.serializeNBT());
        CompoundTag infoTag = new CompoundTag();
        infoTag.putInt("Cooldown", cooldown);
        infoTag.putInt("Delay", delay);
        pTag.put("Info", infoTag);
    }
    @Override
    public void load(CompoundTag pTag) {
        if (pTag.contains("ItemStackHandler")) {
            ITEM_STACK_HANDLER.deserializeNBT(pTag.getCompound("ItemStackHandler"));
        }
        if (pTag.contains("Info")) {
            cooldown = pTag.getCompound("Info").getInt("Cooldown");
            delay = pTag.getCompound("Info").getInt("Delay");
        }
        super.load(pTag);
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
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        if (tag != null) {
            load(tag);
        }
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        // This is called client side: remember the current state of the values that we're interested in
        int oldcooldown = cooldown;
        int oldDelay = delay;
        ItemStackHandler oldItemStackHandler = ITEM_STACK_HANDLER;
        ItemStackHandler oldItemStackMachineHandler = ITEM_STACK_MACHINE_HANDLER;

        CompoundTag tag = pkt.getTag();
        // This will call loadClientData()
        handleUpdateTag(tag);

        // If any of the values was changed we request a refresh of our model data and send a block update
        if (oldcooldown != cooldown || oldDelay != delay ||
                !Objects.equals(oldItemStackMachineHandler, ITEM_STACK_MACHINE_HANDLER) ||
                !Objects.equals(oldItemStackHandler, ITEM_STACK_HANDLER)) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getDelay() {
        return delay;
    }
    public void tickServer() {
        delay = XtraDrinksConfig.DEHYDRATOR_DELAY.get();
        boolean shouldUpdate = false;
        if (
                level != null &&
                !ITEM_STACK_HANDLER.getStackInSlot(0).isEmpty() &&
                        DehydratorRecipeSerializer.doesItemHaveRecipe(ITEM_STACK_HANDLER.getStackInSlot(0),level)
                        && ITEM_STACK_HANDLER.getStackInSlot(1).getCount() + DehydratorRecipeSerializer.getItemStackOutputFromInput(ITEM_STACK_HANDLER.getStackInSlot(0),level).getCount() <= ITEM_STACK_HANDLER.getStackInSlot(1).getMaxStackSize()
                        && (ITEM_STACK_HANDLER.getStackInSlot(1).isEmpty()
                        || ITEM_STACK_HANDLER.getStackInSlot(1).sameItem(DehydratorRecipeSerializer.getItemStackOutputFromInput(ITEM_STACK_HANDLER.getStackInSlot(0),level)))) {
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
                ITEM_STACK_HANDLER.setStackInSlot(1,DehydratorRecipeSerializer.getItemStackOutputFromInput(ITEM_STACK_HANDLER.getStackInSlot(0),level).copy());
            } else {
                ITEM_STACK_HANDLER.getStackInSlot(1).grow(DehydratorRecipeSerializer.getItemStackOutputFromInput(ITEM_STACK_HANDLER.getStackInSlot(0),level).getCount());
            }
            ITEM_STACK_HANDLER.getStackInSlot(0).shrink(1);
        }
        if (shouldUpdate) {
            setChanged();
            this.level.sendBlockUpdated(worldPosition,getBlockState(),getBlockState(),Block.UPDATE_ALL);
        }
    }

}

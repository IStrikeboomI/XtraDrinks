package Strikeboom.xtradrinks.guis.blockentities;

import Strikeboom.xtradrinks.guis.blockentities.itemhandlers.OutputOnlyItemHandler;
import Strikeboom.xtradrinks.init.XtraDrinksBlockEntities;
import Strikeboom.xtradrinks.init.XtraDrinksConfig;
import Strikeboom.xtradrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipeHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Objects;

public class LiquidDehydratorBlockEntity extends BlockEntity {
    private final ItemStackHandler ITEM_STACK_HANDLER;
    private final LazyOptional<IItemHandler> ITEM_STACK_HANDLER_LAZY_OPTIONAL;
    private final FluidTank FLUID_TANK;
    private final LazyOptional<IFluidHandler> FLUID_TANK_LAZY_OPTIONAL;
    int cooldown = 0;
    int delay;
    public LiquidDehydratorBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(XtraDrinksBlockEntities.LIQUID_DEHYDRATOR_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        ITEM_STACK_HANDLER = new OutputOnlyItemHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                level.sendBlockUpdated(worldPosition,getBlockState(),getBlockState(), Block.UPDATE_ALL);
            }
        };
        ITEM_STACK_HANDLER_LAZY_OPTIONAL = LazyOptional.of(() -> ITEM_STACK_HANDLER);
        FLUID_TANK = new FluidTank(FluidAttributes.BUCKET_VOLUME * 10) {
            @Override
            protected void onContentsChanged() {
                setChanged();
                level.sendBlockUpdated(worldPosition,getBlockState(),getBlockState(),Block.UPDATE_ALL);
            }

            @Override
            public boolean isFluidValid(FluidStack stack) {
                return LiquidDehydratorRecipeHandler.doesFluidStackHaveRecipe(stack);
            }
        };
        FLUID_TANK_LAZY_OPTIONAL = LazyOptional.of(() -> FLUID_TANK);
        delay = XtraDrinksConfig.LIQUID_DEHYDRATOR_DELAY.get();
    }
    @Override
    public void setRemoved() {
        super.setRemoved();
        ITEM_STACK_HANDLER_LAZY_OPTIONAL.invalidate();
        FLUID_TANK_LAZY_OPTIONAL.invalidate();
    }
    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("ItemStackHandler",ITEM_STACK_HANDLER.serializeNBT());
        FLUID_TANK.writeToNBT(pTag);
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
        FLUID_TANK.readFromNBT(pTag);
        if (pTag.contains("Info")) {
            cooldown = pTag.getCompound("Info").getInt("Cooldown");
            delay = pTag.getCompound("Info").getInt("Delay");
        }
        super.load(pTag);
    }
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, final Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return ITEM_STACK_HANDLER_LAZY_OPTIONAL.cast();
        }
        if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return FLUID_TANK_LAZY_OPTIONAL.cast();
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
        FluidTank oldFluidTank = FLUID_TANK;

        CompoundTag tag = pkt.getTag();
        // This will call loadClientData()
        handleUpdateTag(tag);

        // If any of the values was changed we request a refresh of our model data and send a block update
        if (oldcooldown != cooldown || oldDelay != delay ||
                !Objects.equals(oldFluidTank, FLUID_TANK) ||
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
    public void drain() {
        FLUID_TANK.setFluid(FluidStack.EMPTY);
        level.playSound(null,getBlockPos(), SoundEvents.BUCKET_EMPTY_LAVA, SoundSource.AMBIENT,10f,1f);
    }
    public void tickServer() {
        delay = XtraDrinksConfig.LIQUID_DEHYDRATOR_DELAY.get();
        boolean shouldUpdate = false;
        if (
                LiquidDehydratorRecipeHandler.doesFluidStackHaveRecipe(FLUID_TANK.getFluid())
                        && !FLUID_TANK.isEmpty()
                        && FLUID_TANK.getFluid().getAmount() >= LiquidDehydratorRecipeHandler.getFluidStackFromFluidStackInput(FLUID_TANK.getFluid()).getAmount()
                        && ITEM_STACK_HANDLER.getStackInSlot(0).getCount() + LiquidDehydratorRecipeHandler.getItemStackFromFluidStack(FLUID_TANK.getFluid()).getCount() <= ITEM_STACK_HANDLER.getStackInSlot(0).getMaxStackSize()
                        && (ITEM_STACK_HANDLER.getStackInSlot(0).isEmpty()
                        || ITEM_STACK_HANDLER.getStackInSlot(0).sameItem(LiquidDehydratorRecipeHandler.getItemStackFromFluidStack(FLUID_TANK.getFluid())))) {
            cooldown++;
            shouldUpdate = true;
        } else {
            if (cooldown != 0) {
                cooldown = 0;
                shouldUpdate = true;
            }
        }
        if (cooldown % delay == 0 && cooldown != 0) {
            cooldown = 0;
            if (ITEM_STACK_HANDLER.getStackInSlot(0).isEmpty()) {
                ITEM_STACK_HANDLER.setStackInSlot(0, LiquidDehydratorRecipeHandler.getItemStackFromFluidStack(FLUID_TANK.getFluid()).copy());
            } else {
                ITEM_STACK_HANDLER.getStackInSlot(0).grow(LiquidDehydratorRecipeHandler.getItemStackFromFluidStack(FLUID_TANK.getFluid()).getCount());
            }
            FLUID_TANK.getFluid().shrink(LiquidDehydratorRecipeHandler.getFluidStackFromItemStack(ITEM_STACK_HANDLER.getStackInSlot(0)).getAmount());
        }
        if (shouldUpdate) {
            setChanged();
            this.level.sendBlockUpdated(worldPosition,getBlockState(),getBlockState(),Block.UPDATE_ALL);
        }
    }
}

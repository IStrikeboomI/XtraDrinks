package Strikeboom.XtraDrinks.guis.tileentities;

import Strikeboom.XtraDrinks.guis.tileentities.itemhandlers.OutputOnlyItemHandler;
import Strikeboom.XtraDrinks.init.XtraDrinksConfig;
import Strikeboom.XtraDrinks.init.XtraDrinksTileEntities;
import Strikeboom.XtraDrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipeHandler;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
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

public class LiquidDehydratorTileEntity extends TileEntity implements ITickableTileEntity {
    private final ItemStackHandler ITEM_STACK_HANDLER;
    private final LazyOptional<IItemHandler> ITEM_STACK_HANDLER_LAZY_OPTIONAL;
    private final FluidTank FLUID_TANK;
    private final LazyOptional<IFluidHandler> FLUID_TANK_LAZY_OPTIONAL;
    int cooldown = 0;
    int delay;
    public LiquidDehydratorTileEntity() {
        super(XtraDrinksTileEntities.LIQUID_DEHYDRATOR_TILE_ENTITY.get());
        ITEM_STACK_HANDLER = new OutputOnlyItemHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                level.sendBlockUpdated(worldPosition,getBlockState(),getBlockState(), 3);
            }
        };
        ITEM_STACK_HANDLER_LAZY_OPTIONAL = LazyOptional.of(() -> ITEM_STACK_HANDLER);
        FLUID_TANK = new FluidTank(FluidAttributes.BUCKET_VOLUME * 10) {
            @Override
            protected void onContentsChanged() {
                setChanged();
                level.sendBlockUpdated(worldPosition,getBlockState(),getBlockState(),3);
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
    public CompoundNBT save(CompoundNBT pTag) {
        pTag.put("ItemStackHandler",ITEM_STACK_HANDLER.serializeNBT());
        FLUID_TANK.writeToNBT(pTag);
        CompoundNBT infoTag = new CompoundNBT();
        infoTag.putInt("Cooldown", cooldown);
        infoTag.putInt("Delay", delay);
        pTag.put("Info", infoTag);
        return super.save(pTag);
    }
    @Override
    public void load(BlockState state, CompoundNBT pTag) {
        if (pTag.contains("ItemStackHandler")) {
            ITEM_STACK_HANDLER.deserializeNBT(pTag.getCompound("ItemStackHandler"));
        }
        FLUID_TANK.readFromNBT(pTag);
        if (pTag.contains("Info")) {
            cooldown = pTag.getCompound("Info").getInt("Cooldown");
            delay = pTag.getCompound("Info").getInt("Delay");
        }
        super.load(state, pTag);
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
    public CompoundNBT getUpdateTag() {
        CompoundNBT tag = super.getUpdateTag();
        save(tag);
        return tag;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        if (tag != null) {
            load(state, tag);
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
        FluidTank oldFluidTank = FLUID_TANK;

        CompoundNBT tag = pkt.getTag();
        // This will call loadClientData()
        handleUpdateTag(null, tag);

        // If any of the values was changed we request a refresh of our model data and send a block update
        if (oldcooldown != cooldown || oldDelay != delay ||
                !Objects.equals(oldFluidTank, FLUID_TANK) ||
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
    public void drain() {
        FLUID_TANK.setFluid(FluidStack.EMPTY);
        level.playSound(null,getBlockPos(), SoundEvents.BUCKET_EMPTY_LAVA, SoundCategory.AMBIENT,10f,1f);
    }
    @Override
    public void tick() {
        if (!level.isClientSide) {
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
                this.level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
            }
        }
    }
}

package Strikeboom.xtradrinks.guis.tileentity;

import Strikeboom.xtradrinks.guis.tileentity.customs.OutPutOnlyItemHandler;
import Strikeboom.xtradrinks.guis.tileentity.recipes.liquid_dehydrator.LiquidDehydratorHandler;
import Strikeboom.xtradrinks.handlers.ConfigHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileEntityLiquidDehydrator extends TileEntity implements ITickable {
    private final ItemStackHandler handler;
    private final FluidTank tank;
    private int coolDown;
    private int delay;

    public TileEntityLiquidDehydrator() {
        handler = new OutPutOnlyItemHandler(1);
        coolDown = 0;
        delay = ConfigHandler.BLOCKS.liquid_dehydrator_delay_time;
        tank = new FluidTank(Fluid.BUCKET_VOLUME * 10);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.handler.deserializeNBT(nbt.getCompoundTag("ItemStackHandler"));
        this.coolDown = nbt.getInteger("cooldown");
        this.delay = nbt.getInteger("delay");
        this.tank.readFromNBT(nbt);
        super.readFromNBT(nbt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setTag("ItemStackHandler", this.handler.serializeNBT());
        nbt.setInteger("cooldown", this.coolDown);
        nbt.setInteger("delay",this.delay);
        this.tank.writeToNBT(nbt);
        return super.writeToNBT(nbt);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) this.handler;
        }
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return (T) this.tank;
        }

        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);

    }

    @Override
    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos, 3, this.getUpdateTag());
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(pkt.getNbtCompound());
    }

    public boolean isUsableByPlayer(EntityPlayer player) {
        return this.world.getTileEntity(this.getPos()) == this && player.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
    }


    @Nullable
    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentTranslation("tileentity.xtradrinks.liquid_dehydrator.name");
    }

    @Override
    public void update() {
        if (!world.isRemote) {
            delay = ConfigHandler.BLOCKS.liquid_dehydrator_delay_time;
            if (
                    handler.getStackInSlot(0).getCount() <= handler.getStackInSlot(0).getMaxStackSize()
                    && tank.getFluid() != null
                    && LiquidDehydratorHandler.doesFluidHaveRecipe(tank.getFluid().getFluid())
                    && tank.getFluid().amount >= LiquidDehydratorHandler.getFluidStackRecipeAmountFromFluid(tank.getFluid().getFluid())
                    && handler.getStackInSlot(0).getCount() + LiquidDehydratorHandler.getItemStackAmountFromFluidStack(tank.getFluid()) <= handler.getStackInSlot(0).getMaxStackSize()
                    && (handler.getStackInSlot(0).isEmpty()
                        || handler.getStackInSlot(0).getItem() == LiquidDehydratorHandler.getItemFromFluidStack(tank.getFluid()))) {
                coolDown++;
                sendUpdates();
            } else {
                coolDown = 0;
                sendUpdates();
            }
            if (coolDown % delay == 0 && coolDown != 0) {
                coolDown = 0;
                if (handler.getStackInSlot(0).isEmpty()) {
                    handler.setStackInSlot(0, LiquidDehydratorHandler.getItemStackFromFluidStack(tank.getFluid()));
                } else {
                    handler.getStackInSlot(0).grow(LiquidDehydratorHandler.getItemStackAmountFromFluidStack(tank.getFluid()));
                }
                tank.getFluid().amount -= LiquidDehydratorHandler.getFluidStackAmountFromItem(handler.getStackInSlot(0));
                if (tank.getFluid().amount == 0) {
                    tank.setFluid(null);
                }
                sendUpdates();
            }
        }
    }

    private void sendUpdates() {
        this.markDirty();
        this.world.markBlockRangeForRenderUpdate(pos, pos);
        this.world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 3);
        this.world.scheduleBlockUpdate(pos, this.getBlockType(), 0, 0);
    }

    public int getCoolDown() {
        return this.coolDown;
    }
    public int getDelay () {
        return this.delay;
    }
}

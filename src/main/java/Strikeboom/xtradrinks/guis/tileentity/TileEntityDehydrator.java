package Strikeboom.xtradrinks.guis.tileentity;

import Strikeboom.xtradrinks.guis.tileentity.customs.DehydratorInsertOnlyItemHandler;
import Strikeboom.xtradrinks.guis.tileentity.customs.DehydratorItemHandler;
import Strikeboom.xtradrinks.guis.tileentity.recipes.dehydrator.DehydratorRecipeHandler;
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
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileEntityDehydrator extends TileEntity implements ITickable {
    private final ItemStackHandler handler;
    private final ItemStackHandler machineHandler;
    private int coolDown;
    private int delay  = ConfigHandler.BLOCKS.dehydrator_delay_time;

    public TileEntityDehydrator() {
        handler = new DehydratorItemHandler(2);
        machineHandler = new DehydratorInsertOnlyItemHandler(handler);
        coolDown = 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        this.handler.deserializeNBT(nbt.getCompoundTag("ItemStackHandler"));
        this.coolDown = nbt.getInteger("cooldown");
        this.delay = nbt.getInteger("delay");
        super.readFromNBT(nbt);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt.setTag("ItemStackHandler", this.handler.serializeNBT());
        nbt.setInteger("cooldown", this.coolDown);
        nbt.setInteger("delay",this.delay);
        return super.writeToNBT(nbt);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (facing != null) {
                return (T)machineHandler;
            }
            return (T) this.handler;
        }

        return super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
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
        return new TextComponentTranslation("tileentity.xtradrinks.dehydrator.name");
    }

    @Override
    public void update() {
        if (!this.world.isRemote) {
            delay = ConfigHandler.BLOCKS.dehydrator_delay_time;
            if (
                    !handler.getStackInSlot(0).isEmpty()
                    && handler.getStackInSlot(1).getCount() <= handler.getStackInSlot(1).getMaxStackSize()
                    && handler.getStackInSlot(0).getCount() <= handler.getStackInSlot(0).getMaxStackSize()
                    && handler.getStackInSlot(1).getCount() + DehydratorRecipeHandler.getCountFromItem(handler.getStackInSlot(1).getItem()) < handler.getStackInSlot(1).getMaxStackSize()
                    && (handler.getStackInSlot(1).isEmpty()
                    || handler.getStackInSlot(1).getItem() == (DehydratorRecipeHandler.getItemFromRecipe(handler.getStackInSlot(0).getItem())))) {
                coolDown++;
            } else {
                coolDown = 0;
            }
            if (coolDown % this.delay == 0 && coolDown != 0) {
                coolDown = 0;
                if (!handler.getStackInSlot(0).isEmpty()) {
                    if (handler.getStackInSlot(1).isEmpty()) {
                        handler.setStackInSlot(1, DehydratorRecipeHandler.getItemStackFromItem(handler.getStackInSlot(0).getItem()));
                    } else {
                        handler.getStackInSlot(1).grow(DehydratorRecipeHandler.getCountFromItem(handler.getStackInSlot(0).getItem()));
                    }
                    handler.getStackInSlot(0).shrink(1);
                }
            }
            sendUpdates();
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
    public  int getDelay () {
        return delay;
    }


}
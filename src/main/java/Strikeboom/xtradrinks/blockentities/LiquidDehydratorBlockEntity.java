package Strikeboom.xtradrinks.blockentities;

import Strikeboom.xtradrinks.blockentities.itemhandlers.OutputOnlyItemHandler;
import Strikeboom.xtradrinks.init.XtraDrinksBlockEntities;
import Strikeboom.xtradrinks.init.XtraDrinksConfig;
import Strikeboom.xtradrinks.init.XtraDrinksRecipes;
import Strikeboom.xtradrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipe;
import Strikeboom.xtradrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipeInput;
import Strikeboom.xtradrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipeSerializer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.ItemStackHandler;

import java.util.Objects;
import java.util.Optional;

public class LiquidDehydratorBlockEntity extends BlockEntity {
    public ItemStackHandler itemHandler;
    public FluidTank fluidTank;
    int cooldown = 0;
    int delay;
    public LiquidDehydratorBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(XtraDrinksBlockEntities.LIQUID_DEHYDRATOR_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        itemHandler = new OutputOnlyItemHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                level.sendBlockUpdated(worldPosition,getBlockState(),getBlockState(), Block.UPDATE_ALL);
            }
        };
        fluidTank = new FluidTank( 10000) {
            @Override
            protected void onContentsChanged() {
                setChanged();
                level.sendBlockUpdated(worldPosition,getBlockState(),getBlockState(),Block.UPDATE_ALL);
            }
        };
        delay = XtraDrinksConfig.LIQUID_DEHYDRATOR_DELAY.get();
    }
    @Override
    public void setRemoved() {
        super.setRemoved();
        level.invalidateCapabilities(getBlockPos());
        invalidateCapabilities();
    }
    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider registries) {
        pTag.put("ItemStackHandler", itemHandler.serializeNBT(registries));
        fluidTank.writeToNBT(registries,pTag);
        CompoundTag infoTag = new CompoundTag();
        infoTag.putInt("Cooldown", cooldown);
        infoTag.putInt("Delay", delay);
        pTag.put("Info", infoTag);
    }
    @Override
    public void loadAdditional(CompoundTag pTag, HolderLookup.Provider registries) {
        if (pTag.contains("ItemStackHandler")) {
            itemHandler.deserializeNBT(registries,pTag.getCompound("ItemStackHandler").orElseThrow());
        }
        fluidTank.readFromNBT(registries,pTag);
        if (pTag.contains("Info")) {
            cooldown = pTag.getCompound("Info").orElseThrow().getInt("Cooldown").orElseThrow();
            delay = pTag.getCompound("Info").orElseThrow().getInt("Delay").orElseThrow();
        }
        super.loadAdditional(pTag,registries);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        saveAdditional(tag,registries);
        return tag;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag,HolderLookup.Provider registries) {
        super.handleUpdateTag(tag, registries);
        loadAdditional(tag, registries);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookupProvider) {
        // This is called client side: remember the current state of the values that we're interested in
        int oldcooldown = cooldown;
        int oldDelay = delay;
        ItemStackHandler oldItemStackHandler = itemHandler;
        FluidTank oldFluidTank = fluidTank;

        CompoundTag tag = pkt.getTag();
        // This will call loadClientData()
        handleUpdateTag(tag, lookupProvider);

        // If any of the values was changed we request a refresh of our model data and send a block update
        if (oldcooldown != cooldown || oldDelay != delay ||
                !Objects.equals(oldFluidTank, fluidTank) ||
                !Objects.equals(oldItemStackHandler, itemHandler)) {
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
        fluidTank.setFluid(FluidStack.EMPTY);
        level.playSound(null,getBlockPos(), SoundEvents.BUCKET_EMPTY_LAVA, SoundSource.AMBIENT,10f,1f);
    }
    public void tickServer() {
        delay = XtraDrinksConfig.LIQUID_DEHYDRATOR_DELAY.get();
        boolean shouldUpdate = false;
        ServerLevel world = (ServerLevel) level;
        Optional<RecipeHolder<LiquidDehydratorRecipe>> r = world.recipeAccess().getRecipeFor(XtraDrinksRecipes.LIQUID_DEHYDRATOR_TYPE.get(),new LiquidDehydratorRecipeInput(fluidTank.getFluid()),world);
        if (r.isPresent()) {
            LiquidDehydratorRecipe recipe = r.orElseThrow().value();
            ItemStack result = recipe.assemble(new LiquidDehydratorRecipeInput(fluidTank.getFluid()),level.registryAccess());
            if (!fluidTank.isEmpty()
                    && fluidTank.getFluid().getAmount() >= recipe.INPUT.getAmount()
                    && itemHandler.getStackInSlot(0).getCount() + result.getCount() <= itemHandler.getStackInSlot(0).getMaxStackSize()
                    && (itemHandler.getStackInSlot(0).isEmpty()
                    || itemHandler.getStackInSlot(0).getItem() == result.getItem()))
            {
                cooldown++;
                shouldUpdate = true;
            }
            if (cooldown % delay == 0 && cooldown != 0) {
                cooldown = 0;
                if (itemHandler.getStackInSlot(0).isEmpty()) {
                    itemHandler.setStackInSlot(0, result);
                } else {
                    itemHandler.getStackInSlot(0).grow(result.getCount());
                }
                fluidTank.getFluid().shrink(recipe.INPUT.getAmount());
            }
        } else {
            if (cooldown != 0) {
                cooldown = 0;
                shouldUpdate = true;
            }
        }
        if (shouldUpdate) {
            setChanged();
            this.level.sendBlockUpdated(worldPosition,getBlockState(),getBlockState(),Block.UPDATE_ALL);
        }
    }
}

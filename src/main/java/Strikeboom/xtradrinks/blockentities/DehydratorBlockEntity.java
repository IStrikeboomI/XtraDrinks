package Strikeboom.xtradrinks.blockentities;

import Strikeboom.xtradrinks.blockentities.itemhandlers.DehydratorInsertOnlyItemHandler;
import Strikeboom.xtradrinks.blockentities.itemhandlers.DehydratorItemHandler;
import Strikeboom.xtradrinks.init.XtraDrinksBlockEntities;
import Strikeboom.xtradrinks.init.XtraDrinksConfig;
import Strikeboom.xtradrinks.init.XtraDrinksRecipes;
import Strikeboom.xtradrinks.recipes.dehydrator.DehydratorRecipe;
import Strikeboom.xtradrinks.recipes.dehydrator.DehydratorRecipeSerializer;
import Strikeboom.xtradrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipe;
import Strikeboom.xtradrinks.recipes.liquid_dehydrator.LiquidDehydratorRecipeInput;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;


import java.util.Objects;
import java.util.Optional;

public class DehydratorBlockEntity extends BlockEntity {
    public DehydratorItemHandler itemHandler;
    public DehydratorInsertOnlyItemHandler itemMachineHandler;
    private int cooldown = 0;
    private int delay;
    public DehydratorBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(XtraDrinksBlockEntities.DEHYDRATOR_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);
        itemHandler = new DehydratorItemHandler(2) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                level.sendBlockUpdated(worldPosition,getBlockState(),getBlockState(), Block.UPDATE_ALL);
            }
        };
        itemMachineHandler = new DehydratorInsertOnlyItemHandler(itemHandler);
        delay = XtraDrinksConfig.DEHYDRATOR_DELAY.get();
    }
    @Override
    public void setRemoved() {
        super.setRemoved();
        level.invalidateCapabilities(getBlockPos());
        invalidateCapabilities();
    }


    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider registries) {
        pTag.put("ItemStackHandler",itemHandler.serializeNBT(registries));
        CompoundTag infoTag = new CompoundTag();
        infoTag.putInt("Cooldown", cooldown);
        infoTag.putInt("Delay", delay);
        pTag.put("Info", infoTag);
    }
    @Override
    public void loadAdditional(CompoundTag pTag, HolderLookup.Provider registries) {
        if (pTag.contains("ItemStackHandler")) {
            itemHandler.deserializeNBT(registries, pTag.getCompound("ItemStackHandler").orElseThrow());
        }
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
        ItemStackHandler oldItemStackMachineHandler = itemMachineHandler;

        CompoundTag tag = pkt.getTag();
        // This will call loadClientData()
        handleUpdateTag(tag,lookupProvider);

        // If any of the values was changed we request a refresh of our model data and send a block update
        if (oldcooldown != cooldown || oldDelay != delay ||
                !Objects.equals(oldItemStackMachineHandler, itemMachineHandler) ||
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
    public void tickServer() {
        delay = XtraDrinksConfig.DEHYDRATOR_DELAY.get();
        boolean shouldUpdate = false;
        ServerLevel world = (ServerLevel) level;
        Optional<RecipeHolder<DehydratorRecipe>> r = world.recipeAccess().getRecipeFor(XtraDrinksRecipes.DEHYDRATOR_TYPE.get(),new SingleRecipeInput(itemHandler.getStackInSlot(0)),world);
        if (r.isPresent()) {
            DehydratorRecipe recipe = r.orElseThrow().value();
            ItemStack result = recipe.assemble(new SingleRecipeInput(itemHandler.getStackInSlot(0)),level.registryAccess());
            if (
                            !itemHandler.getStackInSlot(0).isEmpty()
                            && itemHandler.getStackInSlot(1).getCount() + result.getCount() <= itemHandler.getStackInSlot(1).getMaxStackSize()
                            && (itemHandler.getStackInSlot(1).isEmpty()
                            || itemHandler.getStackInSlot(1).getItem() == result.getItem())) {
                cooldown++;
                shouldUpdate = true;
            }
            if (cooldown % this.delay == 0 && cooldown != 0) {
                cooldown = 0;
                if (itemHandler.getStackInSlot(1).isEmpty()) {
                    itemHandler.setStackInSlot(1, result);
                } else {
                    itemHandler.getStackInSlot(1).grow(result.getCount());
                }
                itemHandler.getStackInSlot(0).shrink(1);
            }

        } else {
            if (cooldown != 0) {
                cooldown = 0;
                shouldUpdate = true;
            }
        }
        if (shouldUpdate) {
            setChanged();
            this.level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), Block.UPDATE_ALL);
        }
    }

}

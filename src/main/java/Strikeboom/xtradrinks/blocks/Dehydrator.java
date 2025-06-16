package Strikeboom.xtradrinks.blocks;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.blockentities.DehydratorBlockEntity;
import Strikeboom.xtradrinks.menus.DehydratorMenu;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class Dehydrator extends Block implements EntityBlock, TooltipProvider {
    public Dehydrator(Properties p_49795_) {
        super(p_49795_);
    }
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new DehydratorBlockEntity(pPos,pState);
    }


    //these 2 functions below help save the data into the item stack when breaking block
    @Override
    public void playerDestroy(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState, @Nullable BlockEntity pBlockEntity, ItemStack pTool) {
        if (!pLevel.isClientSide) {
            ItemStack stack = new ItemStack(this);

            if (pBlockEntity != null) {
                stack.set(DataComponents.BLOCK_ENTITY_DATA,CustomData.of(pBlockEntity.saveCustomAndMetadata(pLevel.registryAccess())));
                pBlockEntity.invalidateCapabilities();
            }

            ItemEntity itementity = new ItemEntity(pLevel, (double)pPos.getX() + 0.5D, (double)pPos.getY() + 0.5D, (double)pPos.getZ() + 0.5D, stack);
            itementity.setDefaultPickUpDelay();
            pLevel.addFreshEntity(itementity);
        }
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        if (!pLevel.isClientSide) {
            CustomData data = pStack.getOrDefault(DataComponents.BLOCK_ENTITY_DATA, CustomData.EMPTY);
            if (!data.isEmpty()) {
                CompoundTag tag = data.copyTag();
                tag.putInt("x",pPos.getX());
                tag.putInt("y",pPos.getY());
                tag.putInt("z",pPos.getZ());
                pLevel.getBlockEntity(pPos).loadWithComponents(tag,pLevel.registryAccess());
            }
        }
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide) {
            return null;
        }

        return (pLevel1, pPos, pState1, pBlockEntity) -> {
            if (pBlockEntity instanceof DehydratorBlockEntity blockEntity) {
                blockEntity.tickServer();
            }
        };
    }

    @Nullable
    @Override
    protected MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        return new MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.translatable("item."+ XtraDrinks.MOD_ID+".dehydrator");
            }

            @Override
            public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
                return new DehydratorMenu(windowId, pos, playerInventory);
            }
        };
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide) {
            if (level.getBlockEntity(pos) instanceof DehydratorBlockEntity) {
                player.openMenu(state.getMenuProvider(level,pos),pos);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag flag, DataComponentGetter componentGetter) {
        if (!componentGetter.getOrDefault(DataComponents.BLOCK_ENTITY_DATA, CustomData.EMPTY).isEmpty()) {
            tooltipAdder.accept(Component.translatable("block." + XtraDrinks.MOD_ID + ".tooltip.saved").withStyle(ChatFormatting.GREEN));
        }
    }
}

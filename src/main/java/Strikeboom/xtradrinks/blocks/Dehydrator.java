package Strikeboom.XtraDrinks.blocks;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.guis.containers.DehydratorContainer;
import Strikeboom.XtraDrinks.guis.tileentities.DehydratorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;

public class Dehydrator extends Block {
    public Dehydrator(Properties p_49795_) {
        super(p_49795_);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new DehydratorTileEntity();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable IBlockReader pLevel, List<ITextComponent> pTooltip, ITooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        if (pStack.hasTag()) {
            if (pStack.getTag().contains("BlockEntityTag")) {
                pTooltip.add(new TranslationTextComponent("block." + XtraDrinks.MOD_ID + ".tooltip.saved").withStyle(TextFormatting.GREEN));
            }
        }
    }

    //these 2 functions below help save the data into the item output when breaking block
    @Override
    public void playerDestroy(World pLevel, PlayerEntity pPlayer, BlockPos pPos, BlockState pState, TileEntity pBlockEntity, ItemStack pTool) {
        if (!pLevel.isClientSide) {
            ItemStack stack = new ItemStack(this);

            if (pBlockEntity != null) {
                pBlockEntity.save(stack.getOrCreateTag());
            }

            ItemEntity itementity = new ItemEntity(pLevel, (double)pPos.getX() + 0.5D, (double)pPos.getY() + 0.5D, (double)pPos.getZ() + 0.5D, stack);
            itementity.setDefaultPickUpDelay();
            pLevel.addFreshEntity(itementity);
        }
    }

    @Override
    public void setPlacedBy(World pWorld, BlockPos pPos, BlockState pState, LivingEntity pPlacer, ItemStack pStack) {
        if (!pWorld.isClientSide) {
            if (pStack.hasTag()) {
                if (pStack.getTag().contains("BlockEntityTag")) {
                    pStack.getTag().putInt("x",pPos.getX());
                    pStack.getTag().putInt("y",pPos.getY());
                    pStack.getTag().putInt("z",pPos.getZ());
                    pWorld.getBlockEntity(pPos).load(pState,pStack.getTag());
                }
            }
        }
    }

    @Override
    public ActionResultType use(BlockState pState, World pWorld, BlockPos pPos, PlayerEntity pPlayer, Hand pHand, BlockRayTraceResult pHit) {
        if (!pWorld.isClientSide) {
            if (pWorld.getBlockEntity(pPos) instanceof DehydratorTileEntity) {
                INamedContainerProvider containerProvider = new INamedContainerProvider() {
                    @Override
                    public ITextComponent getDisplayName() {
                        return new TranslationTextComponent("block."+ XtraDrinks.MOD_ID+".dehydrator");
                    }

                    @Override
                    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                        return new DehydratorContainer(windowId, pPos, playerInventory, playerEntity, IWorldPosCallable.create(pWorld,pPos));
                    }
                };
                NetworkHooks.openGui((ServerPlayerEntity) pPlayer, containerProvider, pPos);
            }
        }
        return ActionResultType.SUCCESS;
    }
}

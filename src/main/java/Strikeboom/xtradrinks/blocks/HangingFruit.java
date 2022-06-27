package Strikeboom.XtraDrinks.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class HangingFruit extends Block {
    public HangingFruit() {
        super(Properties.of(Material.PLANT).sound(SoundType.GRASS).instabreak().noCollission());
    }

    @Override
    public VoxelShape getShape(BlockState pState, IBlockReader pLevel, BlockPos pPos, ISelectionContext pContext) {
        Vector3d vec3 = pState.getOffset(pLevel, pPos);
        return Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D).move(vec3.x,vec3.y,vec3.z);
    }

    @Override
    public boolean canSurvive(BlockState pState, IWorldReader pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.above()).is(BlockTags.LEAVES);
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader world, BlockPos pos, BlockState state) {
        return new ItemStack(this);
    }

    @Override
    public void neighborChanged(BlockState pState, World pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        super.neighborChanged(pState, pLevel, pPos, pBlock, pFromPos, pIsMoving);
        if (!pLevel.isClientSide()) {
            if (!canSurvive(pState, pLevel, pPos)) {
                pLevel.destroyBlock(pPos,true);
            }
        }
    }
}

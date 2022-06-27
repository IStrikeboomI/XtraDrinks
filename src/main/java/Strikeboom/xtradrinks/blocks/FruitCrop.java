package Strikeboom.XtraDrinks.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

import java.util.List;

public class FruitCrop extends CropsBlock {
    public FruitCrop() {
        super(Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP));
    }

    @Override
    public boolean canSurvive(BlockState pState, IWorldReader pLevel, BlockPos pPos) {
        return List.of(Blocks.GRASS_BLOCK,Blocks.DIRT,Blocks.COARSE_DIRT,Blocks.FARMLAND).contains(pLevel.getBlockState(pPos.below()).getBlock());
    }

    @Override
    public ItemStack getCloneItemStack(IBlockReader world, BlockPos pos, BlockState state) {
        return new ItemStack(this);
    }
}
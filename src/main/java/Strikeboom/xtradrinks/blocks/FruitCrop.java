package Strikeboom.xtradrinks.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class FruitCrop extends CropBlock {
    public FruitCrop(ResourceLocation loc) {
        super(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS).noCollission().randomTicks().instabreak().sound(SoundType.CROP).setId(ResourceKey.create(BuiltInRegistries.BLOCK.key(),loc)));
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {

        return List.of(Blocks.GRASS_BLOCK,Blocks.DIRT,Blocks.COARSE_DIRT,Blocks.FARMLAND).contains(pLevel.getBlockState(pPos.below()).getBlock());
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader p_304482_, BlockPos p_52255_, BlockState p_52256_, boolean p_387989_) {
        return new ItemStack(this);
    }

}
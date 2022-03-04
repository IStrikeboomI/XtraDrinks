package Strikeboom.xtradrinks.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HangingFruit extends Block {
    public HangingFruit() {
        super(Properties.of(Material.PLANT).sound(SoundType.GRASS).noCollission());
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Vec3 vec3 = pState.getOffset(pLevel, pPos);
        return Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D).move(vec3.x,vec3.y,vec3.z);
    }
}

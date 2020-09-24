package Strikeboom.xtradrinks.worldgen.generators;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class HangingCropGenerator extends WorldGenerator {
    Block crop;
    List<IBlockState> hangingOn = new ArrayList<>();

    public HangingCropGenerator(Block crop,IBlockState... hangingOn) {
        this.crop = crop;
        this.hangingOn.addAll(Arrays.asList(hangingOn));
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for (IBlockState states : hangingOn) {
            if (worldIn.isAirBlock(position) && worldIn.getBlockState(position.up()) == states) {
                worldIn.setBlockState(position, this.crop.getDefaultState(), 1);
            }
        }

        return true;
    }
}

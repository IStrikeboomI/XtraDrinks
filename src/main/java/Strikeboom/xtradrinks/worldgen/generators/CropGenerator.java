package Strikeboom.xtradrinks.worldgen.generators;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class CropGenerator extends WorldGenerator {
    Block crop;

    public CropGenerator(Block crop) {
        this.crop = crop;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if (worldIn.isAirBlock(position) && worldIn.getBlockState(position.down()) == Blocks.GRASS.getDefaultState()) {
            worldIn.setBlockState(position, this.crop.getStateFromMeta(7), 1);
        }
        return true;
    }
}

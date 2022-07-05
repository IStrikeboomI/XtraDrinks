package Strikeboom.XtraDrinks.worldgen.hangingcrops;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;

import java.util.List;
import java.util.Random;

public class HangingCropFeature extends Feature<HangingCropConfig> {
    public HangingCropFeature() {
        super(HangingCropConfig.CODEC);
    }

    @Override
    public boolean place(ISeedReader pWorld, ChunkGenerator pChunkGenerator, Random pRandom, BlockPos pPos, HangingCropConfig pConfig) {
        BlockPos pos = pPos;
        //keep going down until the pos is air and above is a leaf in the pConfig.toPlaceUnder list
        do {
            pos = pos.below();
            //if can't find a leaf then just end
            if (pos.getY() <= 0) {
                return false;
            }
            //above is an immutable function even for mutable block pos
        } while (!doesBlockStateListContainBlock(pWorld.getBlockState(pos.above()), pConfig.toPlaceUnder) || !pWorld.getBlockState(pos).is(Blocks.AIR));
        BlockPos checkingPos = pos;
        //check if there are any of the same crop above in a 16 block line
        for (int i = 0;i < 16;i++) {
            checkingPos = checkingPos.above();
            if (pWorld.getBlockState(checkingPos).is(pConfig.toPlace.getBlock())) {
                return false;
            }
        }
        pWorld.setBlock(pos, pConfig.toPlace, 2);
        return true;
    }
    private static boolean doesBlockStateListContainBlock(BlockState state, List<BlockState> blockStateList) {
        for (BlockState s : blockStateList) {
            if (s.is(state.getBlock())) {
                return true;
            }
        }
        return false;
    }
}

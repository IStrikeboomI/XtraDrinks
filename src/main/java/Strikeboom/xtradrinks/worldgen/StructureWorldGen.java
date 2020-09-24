package Strikeboom.xtradrinks.worldgen;

import Strikeboom.xtradrinks.worldgen.generators.StructureGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StructureWorldGen implements IWorldGenerator {

    public static final List<StructureGenerator> generators = new ArrayList<>();

    public StructureWorldGen() {

    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == 0) {
            for (StructureGenerator generator: generators) {
                rungenerator(generator,chunkX * 16 + random.nextInt(16),chunkZ * 16 + random.nextInt(16),world,25,random);
            }
        }
    }

    private void rungenerator(StructureGenerator generator, int BlockX, int BlockZ, World world, int Chance,Random random) {
        if ((int) (Math.random() * Chance) == 0) {
            generator.generate(world, random, new BlockPos(BlockX, StructureGenerator.getGroundFromAbove(world, BlockX, BlockZ), BlockZ));
        }
    }
}

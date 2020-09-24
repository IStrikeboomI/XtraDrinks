package Strikeboom.xtradrinks.worldgen;

import Strikeboom.xtradrinks.init.ModBlocks;
import Strikeboom.xtradrinks.worldgen.generators.HangingCropGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class FruitOnTreeWorldGen implements IWorldGenerator {

    WorldGenerator oranges;
    WorldGenerator coconuts;
    WorldGenerator pines;
    public static final int MIN_HEIGHT = 60;
    public static final int MAX_HEIGHT = 140;
    public FruitOnTreeWorldGen() {
        //Oak leaves, birch leaves, and dark oak
        oranges = new HangingCropGenerator(ModBlocks.oranges, Blocks.LEAVES.getStateFromMeta(0), Blocks.LEAVES.getStateFromMeta(2), Blocks.LEAVES2.getStateFromMeta(1));
        //Jungle leaves
        coconuts = new HangingCropGenerator(ModBlocks.coconut,Blocks.LEAVES.getStateFromMeta(3));
        //spruce leaves
        pines = new HangingCropGenerator(ModBlocks.pines,Blocks.LEAVES.getStateFromMeta(1));
    }
    private void runGenerator(WorldGenerator generator, Random random, int chunkX, int chunkZ, World world,int chancesToSpawn) {
        for (int i = 0;i < chancesToSpawn;i++) {
            generator.generate(world, random, new BlockPos(
                    chunkX * 16 + 8 + random.nextInt(16),
                    MIN_HEIGHT + random.nextInt(MAX_HEIGHT - MIN_HEIGHT + 1),
                    chunkZ * 16 + 8 + random.nextInt(16)));

        }
    }
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
            if (world.provider.getDimension() == 0) {
                runGenerator(oranges,random,chunkX,chunkZ,world,256);
                runGenerator(pines,random,chunkX,chunkZ,world,300);
                runGenerator(coconuts,random,chunkX,chunkZ,world,256);
            }
    }
}

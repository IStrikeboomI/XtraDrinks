package Strikeboom.xtradrinks.worldgen;

import Strikeboom.xtradrinks.init.ModBlocks;
import Strikeboom.xtradrinks.worldgen.generators.CropGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CropOnGrassWorldGen implements IWorldGenerator {

    public static final List<WorldGenerator> GENERATORS = new ArrayList<>();

    WorldGenerator pomegranate;
    WorldGenerator grape;
    WorldGenerator pineapple;
    WorldGenerator lemon;
    WorldGenerator lime;
    WorldGenerator blackberry;
    WorldGenerator cranberry;
    WorldGenerator blueberry;

    public static final int MIN_HEIGHT = 50;
    public static final int MAX_HEIGHT = 140;

    public CropOnGrassWorldGen() {
        pomegranate = new CropGenerator(ModBlocks.pomegranate);
        grape= new CropGenerator(ModBlocks.grape);
        pineapple= new CropGenerator(ModBlocks.pineapple);
        lemon= new CropGenerator(ModBlocks.lemon);
        lime= new CropGenerator(ModBlocks.lime);
        blackberry= new CropGenerator(ModBlocks.blackberry);
        cranberry= new CropGenerator(ModBlocks.cranberry);
        blueberry= new CropGenerator(ModBlocks.blueberry);

        GENERATORS.add(pomegranate);
        GENERATORS.add(grape);
        GENERATORS.add(pineapple);
        GENERATORS.add(lemon);
        GENERATORS.add(lime);
        GENERATORS.add(blackberry);
        GENERATORS.add(cranberry);
        GENERATORS.add(blueberry);
    }
    private void runGenerator(WorldGenerator generator, Random random, int chunkX, int chunkZ, World world, int chancesToSpawn) {
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
            for (WorldGenerator generators: GENERATORS) {
                runGenerator(generators,random,chunkX,chunkZ,world,12);
            }
        }
    }
}

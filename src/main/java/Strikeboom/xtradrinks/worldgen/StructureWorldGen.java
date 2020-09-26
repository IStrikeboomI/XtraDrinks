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

    private final StructureGenerator coconut_farm;
    private final StructureGenerator orange_farm;
    private final StructureGenerator crop_farm;
    private final StructureGenerator pines_farm;
    private final StructureGenerator fizzium_well;
    private final StructureGenerator large_fizzium_well;
    private final StructureGenerator liquadium_well;
    private final StructureGenerator large_liquadium_well;


    public StructureWorldGen() {
        coconut_farm = new StructureGenerator("coconut_farm",true);
        orange_farm = new StructureGenerator("orange_farm",true);
        crop_farm = new StructureGenerator("crop_farm",true);
        pines_farm = new StructureGenerator("pines_farm",true);
        fizzium_well = new StructureGenerator("fizzium_well");
        large_fizzium_well = new StructureGenerator("large_fizzium_well");
        liquadium_well = new StructureGenerator("liquadium_well");
        large_liquadium_well = new StructureGenerator("large_liquadium_well");
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == 0) {
            rungenerator( fizzium_well, chunkX * 16 + random.nextInt(16), chunkZ * 16 + random.nextInt(16), world, 60, random);
            rungenerator( large_fizzium_well, chunkX * 16 + random.nextInt(16), chunkZ * 16 + random.nextInt(16), world, 160, random);
            rungenerator( liquadium_well, chunkX * 16 + random.nextInt(16), chunkZ * 16 + random.nextInt(16), world, 60, random);
            rungenerator( large_liquadium_well, chunkX * 16 + random.nextInt(16), chunkZ * 16 + random.nextInt(16), world, 160, random);
            rungenerator( coconut_farm,chunkX,chunkZ,world,random);
            rungenerator( orange_farm,chunkX,chunkZ,world,random);
            rungenerator( crop_farm,chunkX,chunkZ,world,random);
            rungenerator( pines_farm,chunkX,chunkZ,world,random);
        }
    }

    private void rungenerator(StructureGenerator generator, int BlockX, int BlockZ, World world, int Chance,Random random) {
        if ((int) (Math.random() * Chance) == 0) {
            generator.generate(world, random, new BlockPos(BlockX, StructureGenerator.getGroundFromAbove(world, BlockX, BlockZ), BlockZ));
        }
    }
    private void rungenerator(StructureGenerator generator, int BlockX, int BlockZ, World world,Random random) {
        if ((int) (Math.random() * 400) == 0) {
            generator.generate(world, random, new BlockPos(BlockX * 16 + random.nextInt(16), 100, BlockZ * 16 + random.nextInt(16)));
        }
    }
}

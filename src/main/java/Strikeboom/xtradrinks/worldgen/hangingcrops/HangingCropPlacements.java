package Strikeboom.xtradrinks.worldgen.hangingcrops;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;

public class HangingCropPlacements {
    public static final Holder<PlacedFeature> ORANGE = register("orange", HangingCropFeatures.ORANGE,Blocks.OAK_LEAVES,Blocks.BIRCH_LEAVES,Blocks.DARK_OAK_LEAVES);
    public static final Holder<PlacedFeature> COCONUT = register("coconut", HangingCropFeatures.COCONUT,Blocks.JUNGLE_LEAVES);
    public static final Holder<PlacedFeature> PINES = register("pines", HangingCropFeatures.PINES,Blocks.SPRUCE_LEAVES);
    
    private static Holder<PlacedFeature> register(String name, Holder<? extends ConfiguredFeature<?, ?>> feature, Block... validLeaves) {
        return PlacementUtils.register(XtraDrinks.MOD_ID + name,feature, RarityFilter.onAverageOnceEvery(6), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlock(Blocks.AIR, BlockPos.ZERO),BlockPredicate.matchesBlocks(List.of(validLeaves),new BlockPos(0,1,0)))));
    }
}

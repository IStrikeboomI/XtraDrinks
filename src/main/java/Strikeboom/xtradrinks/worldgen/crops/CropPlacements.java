package Strikeboom.xtradrinks.worldgen.crops;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.  worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import java.util.List;

public class CropPlacements {
    public final static Holder<PlacedFeature> POMEGRANATE = register("pomegranate", CropFeatures.POMEGRANATE);
    public final static Holder<PlacedFeature> GRAPE = register("grape",CropFeatures.GRAPE);
    public final static Holder<PlacedFeature> PINEAPPLE = register("pineapple",CropFeatures.PINEAPPLE);
    public final static Holder<PlacedFeature> LEMON = register("lemon",CropFeatures.LEMON);
    public final static Holder<PlacedFeature> LIME = register("lime",CropFeatures.LIME);
    public final static Holder<PlacedFeature> BLACKBERRY = register("blackberry",CropFeatures.BLACKBERRY);
    public final static Holder<PlacedFeature> CRANBERRY = register("cranberry",CropFeatures.CRANBERRY);
    public final static Holder<PlacedFeature> BLUEBERRY = register("blueberry",CropFeatures.BLUEBERRY);
    
    private static  Holder<PlacedFeature> register(String name,  Holder<? extends ConfiguredFeature<?, ?>> feature) {
        return PlacementUtils.register(XtraDrinks.MOD_ID + name,feature, RarityFilter.onAverageOnceEvery(10), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(),BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlock(Blocks.AIR, BlockPos.ZERO),BlockPredicate.matchesBlocks(List.of(Blocks.GRASS_BLOCK,Blocks.DIRT,Blocks.COARSE_DIRT),new BlockPos(0,-1,0)))));
    }
}

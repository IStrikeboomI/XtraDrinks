package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class XtraDrinksPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, XtraDrinks.MOD_ID);
    
    public static final RegistryObject<PlacedFeature> ORANGE = PLACED_FEATURES.register("orange", () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>) (Holder<? extends ConfiguredFeature<?,?>>) Holder.direct(XtraDrinksConfiguredFeatures.ORANGE.get()), List.of(CountPlacement.of(256), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(50), VerticalAnchor.absolute(200)), BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlocks(BlockPos.ZERO,Blocks.AIR),BlockPredicate.matchesTag(new BlockPos(0,1,0),XtraDrinksTags.ORANGE_CAN_SPAWN_ON))))));
    public static final RegistryObject<PlacedFeature> COCONUT = PLACED_FEATURES.register("coconut", () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>) (Holder<? extends ConfiguredFeature<?,?>>) Holder.direct(XtraDrinksConfiguredFeatures.COCONUT.get()),List.of(CountPlacement.of(256), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(50), VerticalAnchor.absolute(200)), BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlocks(BlockPos.ZERO,Blocks.AIR),BlockPredicate.matchesTag(new BlockPos(0,1,0),XtraDrinksTags.COCONUT_CAN_SPAWN_ON))))));
    public static final RegistryObject<PlacedFeature> PINES = PLACED_FEATURES.register("pines", () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>) (Holder<? extends ConfiguredFeature<?,?>>) Holder.direct(XtraDrinksConfiguredFeatures.PINES.get()),List.of(CountPlacement.of(256), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(50), VerticalAnchor.absolute(200)), BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlocks(BlockPos.ZERO,Blocks.AIR),BlockPredicate.matchesTag(new BlockPos(0,1,0),XtraDrinksTags.PINES_CAN_SPAWN_ON))))));

    public final static RegistryObject<PlacedFeature> POMEGRANATE = PLACED_FEATURES.register("pomegranate", () -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>) (Holder<? extends ConfiguredFeature<?,?>>) Holder.direct(XtraDrinksConfiguredFeatures.POMEGRANATE.get()),List.of(RarityFilter.onAverageOnceEvery(10), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(),BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlocks(BlockPos.ZERO, Blocks.AIR),BlockPredicate.matchesBlocks(new BlockPos(0,-1,0),List.of(Blocks.GRASS_BLOCK,Blocks.DIRT,Blocks.COARSE_DIRT)))))));
    public final static RegistryObject<PlacedFeature> GRAPE = PLACED_FEATURES.register("grape",() -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>) (Holder<? extends ConfiguredFeature<?,?>>) Holder.direct(XtraDrinksConfiguredFeatures.GRAPE.get()),List.of(RarityFilter.onAverageOnceEvery(10), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(),BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlocks(BlockPos.ZERO, Blocks.AIR),BlockPredicate.matchesBlocks(new BlockPos(0,-1,0),List.of(Blocks.GRASS_BLOCK,Blocks.DIRT,Blocks.COARSE_DIRT)))))));
    public final static RegistryObject<PlacedFeature> PINEAPPLE = PLACED_FEATURES.register("pineapple",() -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>) (Holder<? extends ConfiguredFeature<?,?>>) Holder.direct(XtraDrinksConfiguredFeatures.PINEAPPLE.get()),List.of(RarityFilter.onAverageOnceEvery(10), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(),BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlocks(BlockPos.ZERO, Blocks.AIR),BlockPredicate.matchesBlocks(new BlockPos(0,-1,0),List.of(Blocks.GRASS_BLOCK,Blocks.DIRT,Blocks.COARSE_DIRT)))))));
    public final static RegistryObject<PlacedFeature> LEMON = PLACED_FEATURES.register("lemon",() -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>) (Holder<? extends ConfiguredFeature<?,?>>) Holder.direct(XtraDrinksConfiguredFeatures.LEMON.get()),List.of(RarityFilter.onAverageOnceEvery(10), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(),BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlocks(BlockPos.ZERO, Blocks.AIR),BlockPredicate.matchesBlocks(new BlockPos(0,-1,0),List.of(Blocks.GRASS_BLOCK,Blocks.DIRT,Blocks.COARSE_DIRT)))))));
    public final static RegistryObject<PlacedFeature> LIME = PLACED_FEATURES.register("lime",() -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>) (Holder<? extends ConfiguredFeature<?,?>>) Holder.direct(XtraDrinksConfiguredFeatures.LIME.get()),List.of(RarityFilter.onAverageOnceEvery(10), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(),BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlocks(BlockPos.ZERO, Blocks.AIR),BlockPredicate.matchesBlocks(new BlockPos(0,-1,0),List.of(Blocks.GRASS_BLOCK,Blocks.DIRT,Blocks.COARSE_DIRT)))))));
    public final static RegistryObject<PlacedFeature> BLACKBERRY = PLACED_FEATURES.register("blackberry",() -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>) (Holder<? extends ConfiguredFeature<?,?>>) Holder.direct(XtraDrinksConfiguredFeatures.BLACKBERRY.get()),List.of(RarityFilter.onAverageOnceEvery(10), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(),BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlocks(BlockPos.ZERO, Blocks.AIR),BlockPredicate.matchesBlocks(new BlockPos(0,-1,0),List.of(Blocks.GRASS_BLOCK,Blocks.DIRT,Blocks.COARSE_DIRT)))))));
    public final static RegistryObject<PlacedFeature> CRANBERRY = PLACED_FEATURES.register("cranberry",() -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>) (Holder<? extends ConfiguredFeature<?,?>>) Holder.direct(XtraDrinksConfiguredFeatures.CRANBERRY.get()),List.of(RarityFilter.onAverageOnceEvery(10), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(),BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlocks(BlockPos.ZERO, Blocks.AIR),BlockPredicate.matchesBlocks(new BlockPos(0,-1,0),List.of(Blocks.GRASS_BLOCK,Blocks.DIRT,Blocks.COARSE_DIRT)))))));
    public final static RegistryObject<PlacedFeature> BLUEBERRY = PLACED_FEATURES.register("blueberry",() -> new PlacedFeature((Holder<ConfiguredFeature<?,?>>) (Holder<? extends ConfiguredFeature<?,?>>) Holder.direct(XtraDrinksConfiguredFeatures.BLUEBERRY.get()),List.of(RarityFilter.onAverageOnceEvery(10), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome(),BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlocks(BlockPos.ZERO, Blocks.AIR),BlockPredicate.matchesBlocks(new BlockPos(0,-1,0),List.of(Blocks.GRASS_BLOCK,Blocks.DIRT,Blocks.COARSE_DIRT)))))));
}

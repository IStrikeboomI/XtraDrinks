package Strikeboom.XtraDrinks.worldgen.crops;

import Strikeboom.XtraDrinks.init.XtraDrinksBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.BlockWithContextConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;

import java.util.Arrays;

public class CropFeatures {
    public static final ConfiguredFeature<?, ?> POMEGRANATE = register(XtraDrinksBlocks.POMEGRANATE.get());
    public static final ConfiguredFeature<?, ?> GRAPE = register(XtraDrinksBlocks.GRAPE.get());
    public static final ConfiguredFeature<?, ?> PINEAPPLE = register(XtraDrinksBlocks.PINEAPPLE.get());
    public static final ConfiguredFeature<?, ?> LEMON = register(XtraDrinksBlocks.LEMON.get());
    public static final ConfiguredFeature<?, ?> LIME = register(XtraDrinksBlocks.LIME.get());
    public static final ConfiguredFeature<?, ?> BLACKBERRY = register(XtraDrinksBlocks.BLACKBERRY.get());
    public static final ConfiguredFeature<?, ?> CRANBERRY = register(XtraDrinksBlocks.CRANBERRY.get());
    public static final ConfiguredFeature<?, ?> BLUEBERRY = register(XtraDrinksBlocks.BLUEBERRY.get());

    private static BlockState getMaxAgeState(Block block) {
        if (block instanceof CropsBlock) {
            CropsBlock cropBlock = (CropsBlock) block;
            return cropBlock.getStateForAge(cropBlock.getMaxAge());
        }
        return block.defaultBlockState();
    }
    private static ConfiguredFeature<?, ?> register(Block crop) {
        ConfiguredFeature<?, ?> f = Feature.SIMPLE_BLOCK.configured(new BlockWithContextConfig(getMaxAgeState(crop), Arrays.asList(Blocks.GRASS_BLOCK.defaultBlockState(),Blocks.DIRT.defaultBlockState(),Blocks.COARSE_DIRT.defaultBlockState()), ImmutableList.of(Blocks.AIR.defaultBlockState()), ImmutableList.of(Blocks.AIR.defaultBlockState()))).range(256).count(15).chance(1).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,crop.getRegistryName(),f);
        return f;
    }
}

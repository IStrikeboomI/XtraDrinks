package Strikeboom.XtraDrinks.worldgen.hangingcrops;

import Strikeboom.XtraDrinks.init.XtraDrinksBlocks;
import Strikeboom.XtraDrinks.init.XtraDrinksFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HangingCropFeatures {

    public static final ConfiguredFeature<?, ?> ORANGE = register(XtraDrinksBlocks.ORANGE.get().defaultBlockState(), Arrays.asList(Blocks.OAK_LEAVES.defaultBlockState(),Blocks.BIRCH_LEAVES.defaultBlockState(),Blocks.DARK_OAK_LEAVES.defaultBlockState()));
    public static final ConfiguredFeature<?, ?> COCONUT = register(XtraDrinksBlocks.COCONUT.get().defaultBlockState(), Collections.singletonList(Blocks.JUNGLE_LEAVES.defaultBlockState()));
    public static final ConfiguredFeature<?, ?> PINES = register(XtraDrinksBlocks.PINES.get().defaultBlockState(), Collections.singletonList(Blocks.SPRUCE_LEAVES.defaultBlockState()));

    private static ConfiguredFeature<?, ?> register(BlockState crop, List<BlockState> toPlaceUnder) {
        ConfiguredFeature<?, ?> f = XtraDrinksFeatures.HANGING_CROP.get().configured(new HangingCropConfig(crop,toPlaceUnder)).range(128).count(128).chance(1).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,crop.getBlock().getRegistryName(),f);
        return f;
    }
}

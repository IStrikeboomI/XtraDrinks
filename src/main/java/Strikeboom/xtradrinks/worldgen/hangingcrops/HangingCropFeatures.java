package Strikeboom.XtraDrinks.worldgen.hangingcrops;

import Strikeboom.XtraDrinks.init.XtraDrinksBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.BlockWithContextConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HangingCropFeatures {
    public static final ConfiguredFeature<?, ?> ORANGE = register(XtraDrinksBlocks.ORANGE.get(), Arrays.asList(Blocks.OAK_LEAVES,Blocks.BIRCH_LEAVES,Blocks.DARK_OAK_LEAVES));
    public static final ConfiguredFeature<?, ?> COCONUT = register(XtraDrinksBlocks.COCONUT.get(), Collections.singletonList(Blocks.JUNGLE_LEAVES));
    public static final ConfiguredFeature<?, ?> PINES = register(XtraDrinksBlocks.PINES.get(), Collections.singletonList(Blocks.SPRUCE_LEAVES));

    private static ConfiguredFeature<?, ?> register(Block crop, List<Block> spawnUnder) {
        ConfiguredFeature<?, ?> f = Feature.SIMPLE_BLOCK.configured(new BlockWithContextConfig(crop.defaultBlockState(), Collections.emptyList(), Collections.emptyList(),spawnUnder.stream().map(Block::defaultBlockState).collect(Collectors.toList()))).range(110).count(1).chance(10);
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,crop.getRegistryName(),f);
        return f;
    }
}

package Strikeboom.xtradrinks.worldgen.hangingcrops;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.init.XtraDrinksBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class HangingCropFeatures {
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> ORANGE = register("orange", XtraDrinksBlocks.ORANGE.get());
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> COCONUT = register("coconut", XtraDrinksBlocks.COCONUT.get());
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> PINES = register("pines", XtraDrinksBlocks.PINES.get());

    private static Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> register(String name, Block crop) {
        return  FeatureUtils.register(XtraDrinks.MOD_ID + name, Feature.SIMPLE_BLOCK,new SimpleBlockConfiguration(BlockStateProvider.simple(crop)));
    }
}

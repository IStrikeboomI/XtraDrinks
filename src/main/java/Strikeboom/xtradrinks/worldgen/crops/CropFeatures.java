package Strikeboom.xtradrinks.worldgen.crops;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.init.XtraDrinksBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class CropFeatures {
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> POMEGRANATE = register("pomegranate",XtraDrinksBlocks.POMEGRANATE.get());
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> GRAPE = register("grape",XtraDrinksBlocks.GRAPE.get());
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> PINEAPPLE = register("pineapple",XtraDrinksBlocks.PINEAPPLE.get());
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> LEMON = register("lemon",XtraDrinksBlocks.LEMON.get());
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> LIME = register("lime",XtraDrinksBlocks.LIME.get());
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> BLACKBERRY = register("blackberry",XtraDrinksBlocks.BLACKBERRY.get());
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> CRANBERRY = register("cranberry",XtraDrinksBlocks.CRANBERRY.get());
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> BLUEBERRY = register("blueberry",XtraDrinksBlocks.BLUEBERRY.get());

    private static BlockState getMaxAgeState(Block block) {
        if (block instanceof CropBlock cropBlock) {
            return cropBlock.getStateForAge(cropBlock.getMaxAge());
        }
        return block.defaultBlockState();
    }
    private static Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> register(String name, Block crop) {
        return  FeatureUtils.register(XtraDrinks.MOD_ID + name,Feature.SIMPLE_BLOCK,new SimpleBlockConfiguration(BlockStateProvider.simple(getMaxAgeState(crop))));
    }
}

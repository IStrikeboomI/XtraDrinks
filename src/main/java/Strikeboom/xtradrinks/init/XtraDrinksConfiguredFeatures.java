package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class XtraDrinksConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registries.CONFIGURED_FEATURE, XtraDrinks.MOD_ID);

    public static final Supplier<ConfiguredFeature<SimpleBlockConfiguration, ?>> ORANGE = CONFIGURED_FEATURES.register("orange", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(XtraDrinksBlocks.ORANGE.get()))));
    public static final Supplier<ConfiguredFeature<SimpleBlockConfiguration, ?>> COCONUT = CONFIGURED_FEATURES.register("coconut", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(XtraDrinksBlocks.COCONUT.get()))));
    public static final Supplier<ConfiguredFeature<SimpleBlockConfiguration, ?>> PINES = CONFIGURED_FEATURES.register("pines", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(XtraDrinksBlocks.PINES.get()))));

    public static final Supplier<ConfiguredFeature<SimpleBlockConfiguration, ?>> POMEGRANATE = CONFIGURED_FEATURES.register("pomegranate",() -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(((CropBlock)XtraDrinksBlocks.POMEGRANATE.get()).getStateForAge(7)))));
    public static final Supplier<ConfiguredFeature<SimpleBlockConfiguration, ?>> GRAPE = CONFIGURED_FEATURES.register("grape",() -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(((CropBlock)XtraDrinksBlocks.GRAPE.get()).getStateForAge(7)))));
    public static final Supplier<ConfiguredFeature<SimpleBlockConfiguration, ?>> PINEAPPLE = CONFIGURED_FEATURES.register("pineapple",() -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(((CropBlock)XtraDrinksBlocks.PINEAPPLE.get()).getStateForAge(7)))));
    public static final Supplier<ConfiguredFeature<SimpleBlockConfiguration, ?>> LEMON = CONFIGURED_FEATURES.register("lemon",() -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(((CropBlock)XtraDrinksBlocks.LEMON.get()).getStateForAge(7)))));
    public static final Supplier<ConfiguredFeature<SimpleBlockConfiguration, ?>> LIME = CONFIGURED_FEATURES.register("lime",() -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(((CropBlock)XtraDrinksBlocks.LIME.get()).getStateForAge(7)))));
    public static final Supplier<ConfiguredFeature<SimpleBlockConfiguration, ?>> BLACKBERRY = CONFIGURED_FEATURES.register("blackberry",() -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(((CropBlock)XtraDrinksBlocks.BLACKBERRY.get()).getStateForAge(7)))));
    public static final Supplier<ConfiguredFeature<SimpleBlockConfiguration, ?>> CRANBERRY = CONFIGURED_FEATURES.register("cranberry",() -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(((CropBlock)XtraDrinksBlocks.CRANBERRY.get()).getStateForAge(7)))));
    public static final Supplier<ConfiguredFeature<SimpleBlockConfiguration, ?>> BLUEBERRY = CONFIGURED_FEATURES.register("blueberry",() -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(((CropBlock)XtraDrinksBlocks.BLUEBERRY.get()).getStateForAge(7)))));

}

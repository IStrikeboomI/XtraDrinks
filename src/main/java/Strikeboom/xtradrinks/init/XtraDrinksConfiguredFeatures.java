package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class XtraDrinksConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, XtraDrinks.MOD_ID);

    public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> ORANGE = CONFIGURED_FEATURES.register("orange", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(XtraDrinksBlocks.ORANGE.get()))));
    public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> COCONUT = CONFIGURED_FEATURES.register("coconut", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(XtraDrinksBlocks.COCONUT.get()))));
    public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> PINES = CONFIGURED_FEATURES.register("pines", () -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(XtraDrinksBlocks.PINES.get()))));

    public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> POMEGRANATE = CONFIGURED_FEATURES.register("pomegranate",() -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(((CropBlock)XtraDrinksBlocks.POMEGRANATE.get()).getStateForAge(7)))));
    public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> GRAPE = CONFIGURED_FEATURES.register("grape",() -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(((CropBlock)XtraDrinksBlocks.GRAPE.get()).getStateForAge(7)))));
    public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> PINEAPPLE = CONFIGURED_FEATURES.register("pineapple",() -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(((CropBlock)XtraDrinksBlocks.PINEAPPLE.get()).getStateForAge(7)))));
    public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> LEMON = CONFIGURED_FEATURES.register("lemon",() -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(((CropBlock)XtraDrinksBlocks.LEMON.get()).getStateForAge(7)))));
    public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> LIME = CONFIGURED_FEATURES.register("lime",() -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(((CropBlock)XtraDrinksBlocks.LIME.get()).getStateForAge(7)))));
    public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> BLACKBERRY = CONFIGURED_FEATURES.register("blackberry",() -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(((CropBlock)XtraDrinksBlocks.BLACKBERRY.get()).getStateForAge(7)))));
    public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> CRANBERRY = CONFIGURED_FEATURES.register("cranberry",() -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(((CropBlock)XtraDrinksBlocks.CRANBERRY.get()).getStateForAge(7)))));
    public static final RegistryObject<ConfiguredFeature<SimpleBlockConfiguration, ?>> BLUEBERRY = CONFIGURED_FEATURES.register("blueberry",() -> new ConfiguredFeature<>(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(((CropBlock)XtraDrinksBlocks.BLUEBERRY.get()).getStateForAge(7)))));

}

package Strikeboom.XtraDrinks.worldgen.crops;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.init.XtraDrinksBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.BlockWithContextConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CropFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, XtraDrinks.MOD_ID);
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
        ConfiguredFeature<?, ?> f = Feature.SIMPLE_BLOCK.configured(new BlockWithContextConfig(getMaxAgeState(crop), Stream.of(Blocks.GRASS_BLOCK,Blocks.DIRT,Blocks.COARSE_DIRT).map(Block::defaultBlockState).collect(Collectors.toList()), Collections.emptyList(), Collections.emptyList())).range(115).count(1).chance(10).decorated(Placement.TOP_SOLID_HEIGHTMAP.configured(IPlacementConfig.NONE));
        Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,crop.getRegistryName(),f);
        return f;
    }
}

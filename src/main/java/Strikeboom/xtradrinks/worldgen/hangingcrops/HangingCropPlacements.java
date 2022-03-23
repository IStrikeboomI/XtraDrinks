package Strikeboom.xtradrinks.worldgen.hangingcrops;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.init.XtraDrinksTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class HangingCropPlacements {
    public static final Holder<PlacedFeature> ORANGE = register("orange", HangingCropFeatures.ORANGE,XtraDrinksTags.ORANGE_CAN_SPAWN_ON);
    public static final Holder<PlacedFeature> COCONUT = register("coconut", HangingCropFeatures.COCONUT,XtraDrinksTags.COCONUT_CAN_SPAWN_ON);
    public static final Holder<PlacedFeature> PINES = register("pines", HangingCropFeatures.PINES, XtraDrinksTags.PINES_CAN_SPAWN_ON);
    
    private static Holder<PlacedFeature> register(String name, Holder<? extends ConfiguredFeature<?, ?>> feature, TagKey<Block> tag) {
        return PlacementUtils.register(XtraDrinks.MOD_ID + name,feature, CountPlacement.of(256), InSquarePlacement.spread(),HeightRangePlacement.uniform(VerticalAnchor.absolute(50), VerticalAnchor.absolute(200)),BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.allOf(BlockPredicate.matchesBlock(Blocks.AIR, BlockPos.ZERO),BlockPredicate.matchesTag(tag,new BlockPos(0,1,0)))));
    }
}

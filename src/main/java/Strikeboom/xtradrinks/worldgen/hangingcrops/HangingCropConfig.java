package Strikeboom.XtraDrinks.worldgen.hangingcrops;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.IFeatureConfig;

import java.util.List;

public class HangingCropConfig implements IFeatureConfig {
    public static final Codec<HangingCropConfig> CODEC = RecordCodecBuilder.create((p_236638_0_) -> p_236638_0_.group(
            BlockState.CODEC.fieldOf("to_place").forGetter((p_236641_0_) -> p_236641_0_.toPlace),
            BlockState.CODEC.listOf().fieldOf("to_place_under").forGetter((p_236637_0_) -> p_236637_0_.toPlaceUnder))
            .apply(p_236638_0_, HangingCropConfig::new));
    BlockState toPlace;
    List<BlockState> toPlaceUnder;
    public HangingCropConfig(BlockState toPlace, List<BlockState> toPlaceUnder) {
        this.toPlace = toPlace;
        this.toPlaceUnder = toPlaceUnder;
    }
}

package Strikeboom.xtradrinks.biomemodifiers;

import Strikeboom.xtradrinks.init.XtraDrinksBiomeModifiers;
import Strikeboom.xtradrinks.init.XtraDrinksConfig;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;

public record VegetalBiomeModifier(Holder<PlacedFeature> feature) implements BiomeModifier {
    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && XtraDrinksConfig.CROP_GENERATION_ENABLED.get()) {
            builder.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, feature);
        }
    }

    @Override
    public MapCodec<? extends BiomeModifier> codec() {
        return XtraDrinksBiomeModifiers.VEGETAL_BIOME_MODIFIER.get();
    }
}

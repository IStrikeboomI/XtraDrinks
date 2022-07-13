package Strikeboom.xtradrinks.biomemodifiers;

import Strikeboom.xtradrinks.init.XtraDrinksBiomeModifiers;
import Strikeboom.xtradrinks.init.XtraDrinksConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public record VegetalBiomeModifier(Holder<PlacedFeature> feature) implements BiomeModifier {
    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && XtraDrinksConfig.CROP_GENERATION_ENABLED.get()) {
            builder.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, feature);
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return XtraDrinksBiomeModifiers.VEGETAL_BIOME_MODIFIER.get();
    }
}

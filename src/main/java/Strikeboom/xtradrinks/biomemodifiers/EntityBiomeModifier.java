package Strikeboom.xtradrinks.biomemodifiers;

import Strikeboom.xtradrinks.init.XtraDrinksBiomeModifiers;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public record EntityBiomeModifier(HolderSet<Biome> biomes, MobSpawnSettings.SpawnerData spawnerData) implements BiomeModifier {
    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase == Phase.ADD && biomes.contains(biome)) {
            builder.getMobSpawnSettings().addSpawn(spawnerData.type.getCategory(),spawnerData);
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return XtraDrinksBiomeModifiers.ENTITY_BIOME_MODIFIER.get();
    }
}

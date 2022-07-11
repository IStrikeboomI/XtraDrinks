package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.worldgen.biomemodifiers.EntityBiomeModifier;
import Strikeboom.xtradrinks.worldgen.biomemodifiers.VegetalBiomeModifier;
import com.mojang.datafixers.kinds.App;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XtraDrinksBiomeModifiers {
    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, XtraDrinks.MOD_ID);

    public static final RegistryObject<Codec<VegetalBiomeModifier>> VEGETAL_BIOME_MODIFIER = BIOME_MODIFIERS.register("vegetal", () -> RecordCodecBuilder.create(instance -> instance.group(
            PlacedFeature.CODEC.fieldOf("feature").forGetter(VegetalBiomeModifier::feature)
    ).apply(instance, VegetalBiomeModifier::new)));

    public static final RegistryObject<Codec<EntityBiomeModifier>> ENTITY_BIOME_MODIFIER = BIOME_MODIFIERS.register("entity", () -> RecordCodecBuilder.create(instance -> instance.group(
            Biome.LIST_CODEC.fieldOf("biomes").forGetter(EntityBiomeModifier::biomes),
            MobSpawnSettings.SpawnerData.CODEC.fieldOf("entity").forGetter(EntityBiomeModifier::spawnerData)
    ).apply(instance, EntityBiomeModifier::new)));
}

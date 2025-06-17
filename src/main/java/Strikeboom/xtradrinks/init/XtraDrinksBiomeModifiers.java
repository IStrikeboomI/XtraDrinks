package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.biomemodifiers.VegetalBiomeModifier;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class XtraDrinksBiomeModifiers {
    public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, XtraDrinks.MOD_ID);
    public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<VegetalBiomeModifier>> VEGETAL_BIOME_MODIFIER = BIOME_MODIFIERS.register("vegetal", () -> RecordCodecBuilder.mapCodec(instance -> instance.group(
            PlacedFeature.CODEC.fieldOf("feature").forGetter(VegetalBiomeModifier::feature)
    ).apply(instance, VegetalBiomeModifier::new)));

}

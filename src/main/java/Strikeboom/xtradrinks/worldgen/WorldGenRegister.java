package Strikeboom.XtraDrinks.worldgen;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.init.XtraDrinksConfig;
import Strikeboom.XtraDrinks.init.XtraDrinksStructureFeatures;
import Strikeboom.XtraDrinks.worldgen.crops.CropFeatures;
import Strikeboom.XtraDrinks.worldgen.hangingcrops.HangingCropFeatures;
import Strikeboom.XtraDrinks.worldgen.structures.FarmStructure;
import Strikeboom.XtraDrinks.worldgen.structures.WellStructure;
import com.mojang.serialization.Codec;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Mod.EventBusSubscriber(modid = XtraDrinks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WorldGenRegister {
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        if (event.getCategory() != Biome.Category.NETHER && event.getCategory() != Biome.Category.THEEND) {
            if (XtraDrinksConfig.CROP_GENERATION_ENABLED.get()) {
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, CropFeatures.POMEGRANATE);
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, CropFeatures.GRAPE);
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, CropFeatures.PINEAPPLE);
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, CropFeatures.LEMON);
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, CropFeatures.LIME);
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, CropFeatures.BLACKBERRY);
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, CropFeatures.CRANBERRY);
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, CropFeatures.BLUEBERRY);
            }
            if (XtraDrinksConfig.HANGING_CROP_GENERATION_ENABLED.get()) {
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HangingCropFeatures.ORANGE);
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HangingCropFeatures.COCONUT);
                event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HangingCropFeatures.PINES);
            }
            if (XtraDrinksConfig.STRUCTURES_ENABLED.get()) {
                if (XtraDrinksConfig.FARMS_ENABLED.get()) {
                    //loop through all the structures and add only the farms
                    for (Structure<?> s : XtraDrinksStructureFeatures.CONFIGURED_STRUCTURES.keySet()) {
                        if (s instanceof FarmStructure) {
                            event.getGeneration().addStructureStart(XtraDrinksStructureFeatures.CONFIGURED_STRUCTURES.get(s));
                        }
                    }
                }
                if (XtraDrinksConfig.WELLS_ENABLED.get()) {
                    for (Structure<?> s : XtraDrinksStructureFeatures.CONFIGURED_STRUCTURES.keySet()) {
                        if (s instanceof WellStructure) {
                            event.getGeneration().addStructureStart(XtraDrinksStructureFeatures.CONFIGURED_STRUCTURES.get(s));
                        }
                    }
                }
            }
        }
    }

    private static Method GETCODEC_METHOD;

    @SubscribeEvent
    public static void addDimensionalSpacing(final WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();

            //skip terraforged world generation
            try {
                if (GETCODEC_METHOD == null)
                    GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkSource().generator));
                if (cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            } catch (Exception ignored) {
            }

            //don't spawn in a superflat world
            if (serverWorld.getChunkSource().getGenerator() instanceof FlatChunkGenerator &&
                    serverWorld.dimension().equals(World.OVERWORLD)) {
                return;
            }
            //only in the overworld
            if (serverWorld.dimension().equals(World.OVERWORLD)) {
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkSource().generator.getSettings().structureConfig());
                for (Structure<?> s : XtraDrinksStructureFeatures.CONFIGURED_STRUCTURES.keySet()) {
                    tempMap.putIfAbsent(s, DimensionStructuresSettings.DEFAULTS.get(s));
                }
                serverWorld.getChunkSource().generator.getSettings().structureConfig = tempMap;
            } else {
                //if the structure is in any other dimensions then remove it
                Set<Structure<?>> tempSet = new HashSet<>(serverWorld.getChunkSource().generator.getSettings().structureConfig().keySet());
                tempSet.removeIf(XtraDrinksStructureFeatures.CONFIGURED_STRUCTURES::containsKey);
                serverWorld.getChunkSource().generator.getSettings().structureConfig.keySet().retainAll(tempSet);
            }
        }
    }
}
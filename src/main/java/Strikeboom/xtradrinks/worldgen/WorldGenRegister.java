package Strikeboom.xtradrinks.worldgen;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.init.XtraDrinksConfig;
import Strikeboom.xtradrinks.worldgen.crops.CropPlacements;
import Strikeboom.xtradrinks.worldgen.hangingcrops.HangingCropPlacements;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = XtraDrinks.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WorldGenRegister {
    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        if (event.getCategory() != Biome.BiomeCategory.NETHER && event.getCategory() != Biome.BiomeCategory.THEEND && event.getCategory() != Biome.BiomeCategory.UNDERGROUND) {
            if (XtraDrinksConfig.CROP_GENERATION_ENABLED.get()) {
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CropPlacements.POMEGRANATE);
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CropPlacements.GRAPE);
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CropPlacements.PINEAPPLE);
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CropPlacements.LEMON);
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CropPlacements.LIME);
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CropPlacements.BLACKBERRY);
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CropPlacements.CRANBERRY);
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, CropPlacements.BLUEBERRY);
            }
            if (XtraDrinksConfig.HANGING_CROP_GENERATION_ENABLED.get()) {
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HangingCropPlacements.ORANGE);
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HangingCropPlacements.COCONUT);
                event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, HangingCropPlacements.PINES);

            }
        }
    }
}

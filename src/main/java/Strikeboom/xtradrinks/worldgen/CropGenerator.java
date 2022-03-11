package Strikeboom.xtradrinks.worldgen;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class CropGenerator {
    public static Holder<PlacedFeature> pomegranate;
    public static void init() {

    }
    @Mod.EventBusSubscriber(modid = XtraDrinks.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class CropRegister {
        @SubscribeEvent
        public static void onBiomeLoad(BiomeLoadingEvent event) {
            if (event.getCategory() != Biome.BiomeCategory.NETHER && event.getCategory() != Biome.BiomeCategory.THEEND) {
                //event.getGeneration().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION,pomegranate);
            }
        }
    }
}

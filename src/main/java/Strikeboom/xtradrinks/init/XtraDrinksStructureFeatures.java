package Strikeboom.XtraDrinks.init;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.FlatGenerationSettings;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;

import java.util.HashMap;

public class XtraDrinksStructureFeatures {
    public static final HashMap<Structure<?>, StructureFeature<?, ?>> CONFIGURED_STRUCTURES = new HashMap<>();
    
    public static final StructureFeature<?, ?> FIZZIUM_WELL = CONFIGURED_STRUCTURES.put(XtraDrinksStructures.FIZZIUM_WELL_STRUCTURE.get(), XtraDrinksStructures.FIZZIUM_WELL_STRUCTURE.get().configured(NoFeatureConfig.INSTANCE));
    public static final StructureFeature<?, ?> FIZZIUM_WELL_LARGE = CONFIGURED_STRUCTURES.put(XtraDrinksStructures.FIZZIUM_WELL_LARGE_STRUCTURE.get(), XtraDrinksStructures.FIZZIUM_WELL_LARGE_STRUCTURE.get().configured(NoFeatureConfig.INSTANCE));
    public static final StructureFeature<?, ?> LIQUADIUM_WELL_LARGE = CONFIGURED_STRUCTURES.put(XtraDrinksStructures.LIQUADIUM_WELL_LARGE_STRUCTURE.get(), XtraDrinksStructures.LIQUADIUM_WELL_LARGE_STRUCTURE.get().configured(NoFeatureConfig.INSTANCE));
    public static final StructureFeature<?, ?> BERRY_FARM = CONFIGURED_STRUCTURES.put(XtraDrinksStructures.BERRY_FARM_STRUCTURE.get(), XtraDrinksStructures.BERRY_FARM_STRUCTURE.get().configured(NoFeatureConfig.INSTANCE));
    public static final StructureFeature<?, ?> TROPICAL_FARM = CONFIGURED_STRUCTURES.put(XtraDrinksStructures.TROPICAL_FARM_STRUCTURE.get(), XtraDrinksStructures.TROPICAL_FARM_STRUCTURE.get().configured(NoFeatureConfig.INSTANCE));
    public static final StructureFeature<?, ?> POMEGRANATE_FARM = CONFIGURED_STRUCTURES.put(XtraDrinksStructures.POMEGRANATE_FARM_STRUCTURE.get(), XtraDrinksStructures.POMEGRANATE_FARM_STRUCTURE.get().configured(NoFeatureConfig.INSTANCE));
    public static final StructureFeature<?, ?> ORANGE_FARM = CONFIGURED_STRUCTURES.put(XtraDrinksStructures.ORANGE_FARM_STRUCTURE.get(), XtraDrinksStructures.ORANGE_FARM_STRUCTURE.get().configured(NoFeatureConfig.INSTANCE));
    public static final StructureFeature<?, ?> COCONUT_FARM = CONFIGURED_STRUCTURES.put(XtraDrinksStructures.COCONUT_FARM_STRUCTURE.get(), XtraDrinksStructures.COCONUT_FARM_STRUCTURE.get().configured(NoFeatureConfig.INSTANCE));
    public static final StructureFeature<?, ?> PINES_FARM = CONFIGURED_STRUCTURES.put(XtraDrinksStructures.PINES_FARM_STRUCTURE.get(), XtraDrinksStructures.PINES_FARM_STRUCTURE.get().configured(NoFeatureConfig.INSTANCE));

    public static void registerConfiguredStructures() {
        for (Structure<?> s : CONFIGURED_STRUCTURES.keySet()) {
            Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, s.getRegistryName(), CONFIGURED_STRUCTURES.get(s));
            //used to prevent a crash if the world is superflat
            FlatGenerationSettings.STRUCTURE_FEATURES.put(s, CONFIGURED_STRUCTURES.get(s));
        }
    }
}

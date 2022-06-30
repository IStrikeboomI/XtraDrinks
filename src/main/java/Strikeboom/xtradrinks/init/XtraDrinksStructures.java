package Strikeboom.XtraDrinks.init;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.worldgen.structures.FarmStructure;
import Strikeboom.XtraDrinks.worldgen.structures.WellStructure;
import com.google.common.collect.ImmutableMap;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

//Thank you to the structure tutorial mod by Telepathic Grunt for help on the structures
public class XtraDrinksStructures {
    public static final DeferredRegister<Structure<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, XtraDrinks.MOD_ID);

    public static final RegistryObject<Structure<NoFeatureConfig>> FIZZIUM_WELL_STRUCTURE = STRUCTURES.register("fizzium_well", () -> new WellStructure("fizzium_well"));
    public static final RegistryObject<Structure<NoFeatureConfig>> FIZZIUM_WELL_LARGE_STRUCTURE = STRUCTURES.register("fizzium_well_large", () -> new WellStructure("fizzium_well_large"));

    public static final RegistryObject<Structure<NoFeatureConfig>> LIQUADIUM_WELL_STRUCTURE = STRUCTURES.register("liquadium_well", () -> new WellStructure("liquadium_well"));
    public static final RegistryObject<Structure<NoFeatureConfig>> LIQUADIUM_WELL_LARGE_STRUCTURE = STRUCTURES.register("liquadium_well_large", () -> new WellStructure("liquadium_well_large"));

    public static final RegistryObject<Structure<NoFeatureConfig>> BERRY_FARM_STRUCTURE = STRUCTURES.register("berry_farm", () -> new FarmStructure("berry_farm"));
    public static final RegistryObject<Structure<NoFeatureConfig>> TROPICAL_FARM_STRUCTURE = STRUCTURES.register("tropical_farm", () -> new FarmStructure("tropical_farm"));
    public static final RegistryObject<Structure<NoFeatureConfig>> POMEGRANATE_FARM_STRUCTURE = STRUCTURES.register("pomegranate_farm", () -> new FarmStructure("pomegranate_farm"));
    public static final RegistryObject<Structure<NoFeatureConfig>> ORANGE_FARM_STRUCTURE = STRUCTURES.register("orange_farm", () -> new FarmStructure("orange_farm"));
    public static final RegistryObject<Structure<NoFeatureConfig>> COCONUT_FARM_STRUCTURE = STRUCTURES.register("coconut_farm", () -> new FarmStructure("coconut_farm"));
    public static final RegistryObject<Structure<NoFeatureConfig>> PINES_FARM_STRUCTURE = STRUCTURES.register("pines_farm", () -> new FarmStructure("pines_farm"));

    public static void setupStructures() {
        setupMapSpacingAndLand(FIZZIUM_WELL_STRUCTURE.get(), new StructureSeparationSettings(40, 5, 253467201));
        setupMapSpacingAndLand(FIZZIUM_WELL_LARGE_STRUCTURE.get(), new StructureSeparationSettings(60, 5, 258467201));
        setupMapSpacingAndLand(LIQUADIUM_WELL_STRUCTURE.get(), new StructureSeparationSettings(40, 5, 253126201));
        setupMapSpacingAndLand(LIQUADIUM_WELL_LARGE_STRUCTURE.get(), new StructureSeparationSettings(60, 5, 3743222));
        setupMapSpacingAndLand(BERRY_FARM_STRUCTURE.get(), new StructureSeparationSettings(60, 5, 6234127));
        setupMapSpacingAndLand(TROPICAL_FARM_STRUCTURE.get(), new StructureSeparationSettings(60, 5, 29013356));
        setupMapSpacingAndLand(POMEGRANATE_FARM_STRUCTURE.get(), new StructureSeparationSettings(60, 5, 12732125));
        setupMapSpacingAndLand(ORANGE_FARM_STRUCTURE.get(), new StructureSeparationSettings(60, 5, 282572723));
        setupMapSpacingAndLand(COCONUT_FARM_STRUCTURE.get(), new StructureSeparationSettings(60, 5, 34526789));
        setupMapSpacingAndLand(PINES_FARM_STRUCTURE.get(), new StructureSeparationSettings(60, 5, 1290878934));

    }
    public static <F extends Structure<?>> void setupMapSpacingAndLand(F structure, StructureSeparationSettings settings)  {
        Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);

        WorldGenRegistries.NOISE_GENERATOR_SETTINGS.entrySet().forEach(s -> {
            Map<Structure<?>, StructureSeparationSettings> structureMap = s.getValue().structureSettings().structureConfig();

            if(structureMap instanceof ImmutableMap){
                Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(structureMap);
                tempMap.put(structure, settings);
                s.getValue().structureSettings().structureConfig = tempMap;
            }
            else{
                structureMap.put(structure, settings);
            }
        });
    }
}

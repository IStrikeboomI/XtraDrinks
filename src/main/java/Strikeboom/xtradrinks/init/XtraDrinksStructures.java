package Strikeboom.XtraDrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.worldgen.structures.FarmStructure;
import Strikeboom.xtradrinks.worldgen.structures.WellStructure;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XtraDrinksStructures {
    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, XtraDrinks.MOD_ID);

    public static final RegistryObject<StructureFeature<JigsawConfiguration>> FIZZIUM_WELL = STRUCTURES.register("fizzium_well", WellStructure::new);
    public static final RegistryObject<StructureFeature<JigsawConfiguration>> FIZZIUM_WELL_LARGE = STRUCTURES.register("fizzium_well_large", WellStructure::new);

    public static final RegistryObject<StructureFeature<JigsawConfiguration>> LIQUADIUM_WELL = STRUCTURES.register("liquadium_well", WellStructure::new);
    public static final RegistryObject<StructureFeature<JigsawConfiguration>> LIQUADIUM_WELL_LARGE = STRUCTURES.register("liquadium_well_large", WellStructure::new);

    public static final RegistryObject<StructureFeature<JigsawConfiguration>> BERRY_FARM = STRUCTURES.register("berry_farm", FarmStructure::new);
    public static final RegistryObject<StructureFeature<JigsawConfiguration>> TROPICAL_FARM = STRUCTURES.register("tropical_farm", FarmStructure::new);
    public static final RegistryObject<StructureFeature<JigsawConfiguration>> POMEGRANATE_FARM = STRUCTURES.register("pomegranate_farm", FarmStructure::new);

    public static final RegistryObject<StructureFeature<JigsawConfiguration>> ORANGE_FARM = STRUCTURES.register("orange_farm", FarmStructure::new);
    public static final RegistryObject<StructureFeature<JigsawConfiguration>> COCONUT_FARM = STRUCTURES.register("coconut_farm", FarmStructure::new);
    public static final RegistryObject<StructureFeature<JigsawConfiguration>> PINES_FARM = STRUCTURES.register("pines_farm", FarmStructure::new);

}

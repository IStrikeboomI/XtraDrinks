package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;

public class XtraDrinksStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registry.STRUCTURE_TYPE_REGISTRY, XtraDrinks.MOD_ID);

    //public static final RegistryObject<StructureType<?>> WELL_STRUCTURE = STRUCTURES.register("well_structure", () -> typeConvert(WellStructure.CODEC));

    private static <S extends Structure> StructureType<S> typeConvert(Codec<S> codec) {
        return () -> codec;
    }

}

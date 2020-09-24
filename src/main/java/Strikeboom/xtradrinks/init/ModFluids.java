package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.util.LinkedList;
import java.util.List;

public class ModFluids {

    public static final List<Fluid> FLUIDS = new LinkedList<>();

    public static Fluid fizzium_fluid;
    public static Fluid liquadium_fluid;

    public static void preInit() {
        fizzium_fluid = new Fluid("fizzium_fluid",new ResourceLocation(XtraDrinks.MOD_ID,"blocks/fizzium_still"),new ResourceLocation(XtraDrinks.MOD_ID,"blocks/fizzium_flow")).setViscosity(2000).setTemperature(1000).setLuminosity(3000);
        liquadium_fluid = new Fluid("liquadium_fluid",new ResourceLocation(XtraDrinks.MOD_ID,"blocks/liquadium_still"),new ResourceLocation(XtraDrinks.MOD_ID,"blocks/liquadium_flow")).setViscosity(2000).setTemperature(1000).setLuminosity(3000);

        add(fizzium_fluid);
        add(liquadium_fluid);
    }

    private static void add(Fluid fluid) {
        FLUIDS.add(fluid);
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
        ModBlocks.BLOCKS.add(new BlockFluidClassic(fluid,Material.LAVA).setRegistryName(fluid.getName()+"_block"));
    }
}

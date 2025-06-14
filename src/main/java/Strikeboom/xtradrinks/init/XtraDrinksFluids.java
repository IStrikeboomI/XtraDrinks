package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.PathType;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;


import java.util.function.Consumer;

public class XtraDrinksFluids {
    public static final FluidType.Properties FLUID_TYPE = FluidType.Properties.create()
            .lightLevel(15)
                .density(2000)
                .viscosity(10000)
                .temperature(1300)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_EMPTY_LAVA)
                .rarity(Rarity.UNCOMMON)
                .pathType(PathType.LAVA)
                .adjacentPathType(null);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, XtraDrinks.MOD_ID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, XtraDrinks.MOD_ID);

    public static final DeferredHolder<FluidType,FluidType> MOLTEN_FIZZIUM_TYPE = FLUID_TYPES.register("molten_fizzium",(loc) -> new FluidType(FLUID_TYPE.descriptionId("fluid." + XtraDrinks.MOD_ID + "." + loc.getPath())));
    public static final DeferredHolder<Fluid,Fluid> MOLTEN_FIZZIUM = FLUIDS.register("molten_fizzium",() -> new BaseFlowingFluid.Source(fizziumProperties()));
    public static final DeferredHolder<Fluid,Fluid> MOLTEN_FIZZIUM_FLOWING = FLUIDS.register("molten_fizzium_flowing",() -> new BaseFlowingFluid.Flowing(fizziumProperties()));
    
    public static final DeferredHolder<FluidType,FluidType>  MOLTEN_LIQUADIUM_TYPE = FLUID_TYPES.register("molten_liquadium",(loc) -> new FluidType(FLUID_TYPE.descriptionId("fluid." + XtraDrinks.MOD_ID + "." + loc.getPath())));
    public static final DeferredHolder<Fluid,Fluid> MOLTEN_LIQUADIUM = FLUIDS.register("molten_liquadium",() -> new BaseFlowingFluid.Source(liquadiumProperties()));
    public static final DeferredHolder<Fluid,Fluid> MOLTEN_LIQUADIUM_FLOWING = FLUIDS.register("molten_liquadium_flowing",() -> new BaseFlowingFluid.Flowing(liquadiumProperties()));

    public static final DeferredHolder<FluidType,FluidType>  MOLTEN_JUICETANIUM_TYPE = FLUID_TYPES.register("molten_juicetanium",(loc) -> new FluidType(FLUID_TYPE.descriptionId("fluid." + XtraDrinks.MOD_ID + "." + loc.getPath())));
    public static final DeferredHolder<Fluid,Fluid> MOLTEN_JUICETANIUM = FLUIDS.register("molten_juicetanium",() -> new BaseFlowingFluid.Source(juicetaniumProperties()));
    public static final DeferredHolder<Fluid,Fluid> MOLTEN_JUICETANIUM_FLOWING = FLUIDS.register("molten_juicetanium_flowing",() -> new BaseFlowingFluid.Flowing(juicetaniumProperties()));

    private static BaseFlowingFluid.Properties fizziumProperties() {
        return new BaseFlowingFluid.Properties(MOLTEN_FIZZIUM_TYPE,MOLTEN_FIZZIUM,MOLTEN_FIZZIUM_FLOWING).bucket(XtraDrinksItems.MOLTEN_FIZZIUM_BUCKET).block(() -> (LiquidBlock) XtraDrinksBlocks.MOLTEN_FIZZIUM_BLOCK.get()).tickRate(30);
    }
    private static BaseFlowingFluid.Properties liquadiumProperties() {
        return new BaseFlowingFluid.Properties(MOLTEN_LIQUADIUM_TYPE,MOLTEN_LIQUADIUM,MOLTEN_LIQUADIUM_FLOWING).tickRate(30).bucket(XtraDrinksItems.MOLTEN_LIQUADIUM_BUCKET).block(() -> (LiquidBlock) XtraDrinksBlocks.MOLTEN_LIQUADIUM_BLOCK.get());
    }
    private static BaseFlowingFluid.Properties juicetaniumProperties() {
        return new BaseFlowingFluid.Properties(MOLTEN_JUICETANIUM_TYPE,MOLTEN_JUICETANIUM,MOLTEN_JUICETANIUM_FLOWING).tickRate(30).bucket(XtraDrinksItems.MOLTEN_JUICETANIUM_BUCKET).block(() -> (LiquidBlock) XtraDrinksBlocks.MOLTEN_JUICETANIUM_BLOCK.get());
    }
}

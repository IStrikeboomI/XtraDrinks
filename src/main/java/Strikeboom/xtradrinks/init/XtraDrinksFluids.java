package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class XtraDrinksFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, XtraDrinks.MOD_ID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, XtraDrinks.MOD_ID);

    public static final RegistryObject<FluidType> MOLTEN_FIZZIUM_TYPE = registerFluidType("molten_fizzium",new ResourceLocation(XtraDrinks.MOD_ID,"block/molten_fizzium_still"),new ResourceLocation(XtraDrinks.MOD_ID,"block/molten_fizzium_flowing"),new ResourceLocation(XtraDrinks.MOD_ID,"block/molten_fizzium_flowing"),0xfaaa39);
    public static final RegistryObject<Fluid> MOLTEN_FIZZIUM = FLUIDS.register("molten_fizzium",() -> new ForgeFlowingFluid.Source(fizziumProperties()));
    public static final RegistryObject<Fluid> MOLTEN_FIZZIUM_FLOWING = FLUIDS.register("molten_fizzium_flowing",() -> new ForgeFlowingFluid.Flowing(fizziumProperties()));
    
    public static final RegistryObject<FluidType> MOLTEN_LIQUADIUM_TYPE = registerFluidType("molten_liquadium",new ResourceLocation(XtraDrinks.MOD_ID,"block/molten_liquadium_still"),new ResourceLocation(XtraDrinks.MOD_ID,"block/molten_liquadium_flowing"),new ResourceLocation(XtraDrinks.MOD_ID,"block/molten_liquadium_flowing"),0x4fb8e8);
    public static final RegistryObject<Fluid> MOLTEN_LIQUADIUM = FLUIDS.register("molten_liquadium",() -> new ForgeFlowingFluid.Source(liquadiumProperties()));
    public static final RegistryObject<Fluid> MOLTEN_LIQUADIUM_FLOWING = FLUIDS.register("molten_liquadium_flowing",() -> new ForgeFlowingFluid.Flowing(liquadiumProperties()));

    public static final RegistryObject<FluidType> MOLTEN_JUICETANIUM_TYPE = registerFluidType("molten_juicetanium",new ResourceLocation(XtraDrinks.MOD_ID,"block/molten_juicetanium_still"),new ResourceLocation(XtraDrinks.MOD_ID,"block/molten_juicetanium_flowing"),new ResourceLocation(XtraDrinks.MOD_ID,"block/molten_juicetanium_flowing"),0x2ccf17);
    public static final RegistryObject<Fluid> MOLTEN_JUICETANIUM = FLUIDS.register("molten_juicetanium",() -> new ForgeFlowingFluid.Source(juicetaniumProperties()));
    public static final RegistryObject<Fluid> MOLTEN_JUICETANIUM_FLOWING = FLUIDS.register("molten_juicetanium_flowing",() -> new ForgeFlowingFluid.Flowing(juicetaniumProperties()));

    private static ForgeFlowingFluid.Properties fizziumProperties() {
        return new ForgeFlowingFluid.Properties(MOLTEN_FIZZIUM_TYPE,MOLTEN_FIZZIUM,MOLTEN_FIZZIUM_FLOWING).bucket(XtraDrinksItems.MOLTEN_FIZZIUM_BUCKET).block(() -> (LiquidBlock) XtraDrinksBlocks.MOLTEN_FIZZIUM_BLOCK.get()).tickRate(30);
    }
    private static ForgeFlowingFluid.Properties liquadiumProperties() {
        return new ForgeFlowingFluid.Properties(MOLTEN_LIQUADIUM_TYPE,MOLTEN_LIQUADIUM,MOLTEN_LIQUADIUM_FLOWING).tickRate(30).bucket(XtraDrinksItems.MOLTEN_LIQUADIUM_BUCKET).block(() -> (LiquidBlock) XtraDrinksBlocks.MOLTEN_LIQUADIUM_BLOCK.get());
    }
    private static ForgeFlowingFluid.Properties juicetaniumProperties() {
        return new ForgeFlowingFluid.Properties(MOLTEN_JUICETANIUM_TYPE,MOLTEN_JUICETANIUM,MOLTEN_JUICETANIUM_FLOWING).tickRate(30).bucket(XtraDrinksItems.MOLTEN_JUICETANIUM_BUCKET).block(() -> (LiquidBlock) XtraDrinksBlocks.MOLTEN_JUICETANIUM_BLOCK.get());
    }
    public static RegistryObject<FluidType> registerFluidType(String name, ResourceLocation still, ResourceLocation flowing, ResourceLocation overlay, int tint) {
        return FLUID_TYPES.register(name, () -> new FluidType(FluidType.Properties.create()
                .lightLevel(15)
                .density(2000)
                .viscosity(10000)
                .temperature(1300)
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_EMPTY_LAVA)
                .rarity(Rarity.UNCOMMON)
                .pathType(BlockPathTypes.LAVA)
                .adjacentPathType(null)
                .descriptionId("fluid." + XtraDrinks.MOD_ID + "." + name)) {
            @Override
            public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                consumer.accept(new IClientFluidTypeExtensions() {
                    @Override
                    public int getTintColor() {
                        return tint;
                    }

                    @Override
                    public ResourceLocation getStillTexture() {
                        return still;
                    }

                    @Override
                    public ResourceLocation getFlowingTexture() {
                        return flowing;
                    }

                    @Override
                    public @Nullable ResourceLocation getOverlayTexture() {
                        return overlay;
                    }
                });
            }
        });
    }
}

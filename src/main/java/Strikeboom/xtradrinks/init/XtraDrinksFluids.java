package Strikeboom.XtraDrinks.init;

import Strikeboom.XtraDrinks.XtraDrinks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class XtraDrinksFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, XtraDrinks.MOD_ID);

    public static final RegistryObject<Fluid> MOLTEN_FIZZIUM = FLUIDS.register("molten_fizzium",() -> new ForgeFlowingFluid.Source(fizziumProperties()));
    public static final RegistryObject<Fluid> MOLTEN_FIZZIUM_FLOWING = FLUIDS.register("molten_fizzium_flowing",() -> new ForgeFlowingFluid.Flowing(fizziumProperties()));

    public static final RegistryObject<Block> MOLTEN_FIZZIUM_BLOCK = XtraDrinksBlocks.BLOCKS.register("molten_fizzium", () -> new FlowingFluidBlock(() -> (FlowingFluid) MOLTEN_FIZZIUM.get(), Block.Properties.copy(Blocks.LAVA)));
    public static final RegistryObject<Item> MOLTEN_FIZZIUM_BUCKET = XtraDrinksItems.ITEMS.register("molten_fizzium_bucket", () -> new BucketItem(MOLTEN_FIZZIUM, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(XtraDrinks.CREATIVE_MODE_TAB)));


    public static final RegistryObject<Fluid> MOLTEN_LIQUADIUM = FLUIDS.register("molten_liquadium",() -> new ForgeFlowingFluid.Source(liquadiumProperties()));
    public static final RegistryObject<Fluid> MOLTEN_LIQUADIUM_FLOWING = FLUIDS.register("molten_liquadium_flowing",() -> new ForgeFlowingFluid.Flowing(liquadiumProperties()));

    public static final RegistryObject<Block> MOLTEN_LIQUADIUM_BLOCK = XtraDrinksBlocks.BLOCKS.register("molten_liquadium", () -> new FlowingFluidBlock(() -> (FlowingFluid) MOLTEN_LIQUADIUM.get(), Block.Properties.copy(Blocks.LAVA)));
    public static final RegistryObject<Item> MOLTEN_LIQUADIUM_BUCKET = XtraDrinksItems.ITEMS.register("molten_liquadium_bucket", () -> new BucketItem(MOLTEN_LIQUADIUM, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(XtraDrinks.CREATIVE_MODE_TAB)));


    public static final RegistryObject<Fluid> MOLTEN_JUICETANIUM = FLUIDS.register("molten_juicetanium",() -> new ForgeFlowingFluid.Source(juicetaniumProperties()));
    public static final RegistryObject<Fluid> MOLTEN_JUICETANIUM_FLOWING = FLUIDS.register("molten_juicetanium_flowing",() -> new ForgeFlowingFluid.Flowing(juicetaniumProperties()));

    public static final RegistryObject<Block> MOLTEN_JUICETANIUM_BLOCK = XtraDrinksBlocks.BLOCKS.register("molten_juicetanium", () -> new FlowingFluidBlock(() -> (FlowingFluid) MOLTEN_JUICETANIUM.get(), Block.Properties.copy(Blocks.LAVA)));
    public static final RegistryObject<Item> MOLTEN_JUICETANIUM_BUCKET = XtraDrinksItems.ITEMS.register("molten_juicetanium_bucket", () -> new BucketItem(MOLTEN_JUICETANIUM, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(XtraDrinks.CREATIVE_MODE_TAB)));

    private static ForgeFlowingFluid.Properties fizziumProperties() {
        return new ForgeFlowingFluid.Properties(MOLTEN_FIZZIUM,MOLTEN_FIZZIUM_FLOWING, FluidAttributes.builder(
                        new ResourceLocation(XtraDrinks.MOD_ID,"block/molten_fizzium_still"),
                        new ResourceLocation(XtraDrinks.MOD_ID,"block/molten_fizzium_flowing")
                )
                .overlay(new ResourceLocation(XtraDrinks.MOD_ID,"block/molten_fizzium_flowing"))
                .density(2000)
                .temperature(1300)
                .viscosity(1000)
                .sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA)
                .translationKey("fluid."+XtraDrinks.MOD_ID+".molten_fizzium")
                .luminosity(15))
                .tickRate(30)
                .bucket(MOLTEN_FIZZIUM_BUCKET)
                .block(() -> (FlowingFluidBlock) MOLTEN_FIZZIUM_BLOCK.get());
    }
    private static ForgeFlowingFluid.Properties liquadiumProperties() {
        return new ForgeFlowingFluid.Properties(MOLTEN_LIQUADIUM,MOLTEN_LIQUADIUM_FLOWING, FluidAttributes.builder(
                        new ResourceLocation(XtraDrinks.MOD_ID,"block/molten_liquadium_still"),
                        new ResourceLocation(XtraDrinks.MOD_ID,"block/molten_liquadium_flowing")
                )
                .overlay(new ResourceLocation(XtraDrinks.MOD_ID,"block/molten_liquadium_flowing"))
                .density(2000)
                .temperature(1300)
                .viscosity(1000)
                .sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA)
                .translationKey("fluid."+XtraDrinks.MOD_ID+".molten_liquadium")
                .luminosity(15))
                .tickRate(30)
                .bucket(MOLTEN_LIQUADIUM_BUCKET)
                .block(() -> (FlowingFluidBlock) MOLTEN_LIQUADIUM_BLOCK.get());
    }

    private static ForgeFlowingFluid.Properties juicetaniumProperties() {
        return new ForgeFlowingFluid.Properties(MOLTEN_JUICETANIUM,MOLTEN_JUICETANIUM_FLOWING, FluidAttributes.builder(
                        new ResourceLocation(XtraDrinks.MOD_ID,"block/molten_juicetanium_still"),
                        new ResourceLocation(XtraDrinks.MOD_ID,"block/molten_juicetanium_flowing")
                )
                .overlay(new ResourceLocation(XtraDrinks.MOD_ID,"block/molten_juicetanium_flowing"))
                .density(2000)
                .temperature(1300)
                .viscosity(1000)
                .sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA)
                .translationKey("fluid."+XtraDrinks.MOD_ID+".molten_juicetanium")
                .luminosity(15))
                .tickRate(30)
                .bucket(MOLTEN_JUICETANIUM_BUCKET)
                .block(() -> (FlowingFluidBlock) MOLTEN_JUICETANIUM_BLOCK.get());
    }
}

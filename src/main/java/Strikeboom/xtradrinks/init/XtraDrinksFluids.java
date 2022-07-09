package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XtraDrinksFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, XtraDrinks.MOD_ID);

    public static final RegistryObject<Fluid> MOLTEN_FIZZIUM = FLUIDS.register("molten_fizzium",() -> new ForgeFlowingFluid.Source(fizziumProperties()));
    public static final RegistryObject<Fluid> MOLTEN_FIZZIUM_FLOWING = FLUIDS.register("molten_fizzium_flowing",() -> new ForgeFlowingFluid.Flowing(fizziumProperties()));

    public static final RegistryObject<Block> MOLTEN_FIZZIUM_BLOCK = XtraDrinksBlocks.BLOCKS.register("molten_fizzium", () -> new LiquidBlock(() -> (FlowingFluid) MOLTEN_FIZZIUM.get(), BlockBehaviour.Properties.copy(Blocks.LAVA)));
    public static final RegistryObject<Item> MOLTEN_FIZZIUM_BUCKET = XtraDrinksItems.ITEMS.register("molten_fizzium_bucket", () -> new BucketItem(MOLTEN_FIZZIUM, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(XtraDrinks.CREATIVE_MODE_TAB)));


    public static final RegistryObject<Fluid> MOLTEN_LIQUADIUM = FLUIDS.register("molten_liquadium",() -> new ForgeFlowingFluid.Source(liquadiumProperties()));
    public static final RegistryObject<Fluid> MOLTEN_LIQUADIUM_FLOWING = FLUIDS.register("molten_liquadium_flowing",() -> new ForgeFlowingFluid.Flowing(liquadiumProperties()));

    public static final RegistryObject<Block> MOLTEN_LIQUADIUM_BLOCK = XtraDrinksBlocks.BLOCKS.register("molten_liquadium", () -> new LiquidBlock(() -> (FlowingFluid) MOLTEN_LIQUADIUM.get(), BlockBehaviour.Properties.copy(Blocks.LAVA)));
    public static final RegistryObject<Item> MOLTEN_LIQUADIUM_BUCKET = XtraDrinksItems.ITEMS.register("molten_liquadium_bucket", () -> new BucketItem(MOLTEN_LIQUADIUM, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(XtraDrinks.CREATIVE_MODE_TAB)));

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
                .block(() -> (LiquidBlock) MOLTEN_FIZZIUM_BLOCK.get());
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
                .block(() -> (LiquidBlock) MOLTEN_LIQUADIUM_BLOCK.get());
    }
}

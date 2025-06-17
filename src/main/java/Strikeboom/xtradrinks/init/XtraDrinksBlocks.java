package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.blocks.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class XtraDrinksBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, XtraDrinks.MOD_ID);

    public static final DeferredHolder<Block,Block> PINEAPPLE = BLOCKS.register("pineapple", FruitCrop::new);
    public static final DeferredHolder<Block,Block> LEMON = BLOCKS.register("lemon",FruitCrop::new);
    public static final DeferredHolder<Block,Block> LIME = BLOCKS.register("lime",FruitCrop::new);
    public static final DeferredHolder<Block,Block> POMEGRANATE = BLOCKS.register("pomegranate",FruitCrop::new);
    public static final DeferredHolder<Block,Block> GRAPE = BLOCKS.register("grape",FruitCrop::new);
    public static final DeferredHolder<Block,Block> CRANBERRY = BLOCKS.register("cranberry",FruitCrop::new);
    public static final DeferredHolder<Block,Block> BLUEBERRY = BLOCKS.register("blueberry",FruitCrop::new);
    public static final DeferredHolder<Block,Block> BLACKBERRY = BLOCKS.register("blackberry",FruitCrop::new);
    public static final DeferredHolder<Block,Block> ORANGE = BLOCKS.register("orange", HangingFruit::new);
    public static final DeferredHolder<Block,Block> COCONUT = BLOCKS.register("coconut",HangingFruit::new);
    public static final DeferredHolder<Block,Block> PINES = BLOCKS.register("pines",HangingFruit::new);

    public static final DeferredHolder<Block,Block> DEHYDRATOR = BLOCKS.register("dehydrator",(loc) -> new Dehydrator(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.METAL).strength(3.5f).requiresCorrectToolForDrops().setId(ResourceKey.create(BuiltInRegistries.BLOCK.key(),loc))));
    public static final DeferredHolder<Block,Block> LIQUID_DEHYDRATOR = BLOCKS.register("liquid_dehydrator",(loc) -> new LiquidDehydrator(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).sound(SoundType.METAL).strength(3.5f).requiresCorrectToolForDrops().setId(ResourceKey.create(BuiltInRegistries.BLOCK.key(),loc))));

    public static final DeferredHolder<Block,Block> GREENMAN_LURE = BLOCKS.register("greenman_lure",(loc) -> new GreenmanLure(BlockBehaviour.Properties.ofFullCopy(Blocks.GLASS).sound(SoundType.GLASS).strength(3.5f).requiresCorrectToolForDrops().setId(ResourceKey.create(BuiltInRegistries.BLOCK.key(),loc))));

    public static final DeferredHolder<Block,Block> FIZZIUM_BLOCK = BLOCKS.register("fizzium_block",(loc) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).setId(ResourceKey.create(BuiltInRegistries.BLOCK.key(),loc))));
    public static final DeferredHolder<Block,Block> LIQUADIUM_BLOCK = BLOCKS.register("liquadium_block",(loc) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).setId(ResourceKey.create(BuiltInRegistries.BLOCK.key(),loc))));
    public static final DeferredHolder<Block,Block> JUICETANIUM_BLOCK = BLOCKS.register("juicetanium_block",(loc) -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL).setId(ResourceKey.create(BuiltInRegistries.BLOCK.key(),loc))));

    public static final DeferredHolder<Block,Block> MOLTEN_FIZZIUM_BLOCK = XtraDrinksBlocks.BLOCKS.register("molten_fizzium", (loc) -> new LiquidBlock((FlowingFluid) XtraDrinksFluids.MOLTEN_FIZZIUM.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.LAVA).setId(ResourceKey.create(BuiltInRegistries.BLOCK.key(),loc))));
    public static final DeferredHolder<Block,Block> MOLTEN_LIQUADIUM_BLOCK = XtraDrinksBlocks.BLOCKS.register("molten_liquadium", (loc) -> new LiquidBlock((FlowingFluid) XtraDrinksFluids.MOLTEN_LIQUADIUM.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.LAVA).setId(ResourceKey.create(BuiltInRegistries.BLOCK.key(),loc))));
    public static final DeferredHolder<Block,Block> MOLTEN_JUICETANIUM_BLOCK = XtraDrinksBlocks.BLOCKS.register("molten_juicetanium", (loc) -> new LiquidBlock((FlowingFluid)XtraDrinksFluids.MOLTEN_JUICETANIUM.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.LAVA).setId(ResourceKey.create(BuiltInRegistries.BLOCK.key(),loc))));

}

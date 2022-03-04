package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.blocks.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XtraDrinksBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, XtraDrinks.MOD_ID);

    public static final RegistryObject<Block> PINEAPPLE = BLOCKS.register("pineapple", FruitCrop::new);
    public static final RegistryObject<Block> LEMON = BLOCKS.register("lemon",FruitCrop::new);
    public static final RegistryObject<Block> LIME = BLOCKS.register("lime",FruitCrop::new);
    public static final RegistryObject<Block> POMEGRANATE = BLOCKS.register("pomegranate",FruitCrop::new);
    public static final RegistryObject<Block> GRAPE = BLOCKS.register("grape",FruitCrop::new);
    public static final RegistryObject<Block> CRANBERRY = BLOCKS.register("cranberry",FruitCrop::new);
    public static final RegistryObject<Block> BLUEBERRY = BLOCKS.register("blueberry",FruitCrop::new);
    public static final RegistryObject<Block> BLACKBERRY = BLOCKS.register("blackberry",FruitCrop::new);
    public static final RegistryObject<Block> ORANGE = BLOCKS.register("orange", HangingFruit::new);
    public static final RegistryObject<Block> COCONUT = BLOCKS.register("coconut",HangingFruit::new);
    public static final RegistryObject<Block> PINES = BLOCKS.register("pines",HangingFruit::new);

    public static final RegistryObject<Block> DEHYDRATOR = BLOCKS.register("dehydrator",() -> new Dehydrator(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Item> DEHYDRATOR_ITEM = fromBlock(DEHYDRATOR);
    public static final RegistryObject<Block> LIQUID_DEHYDRATOR = BLOCKS.register("liquid_dehydrator",() -> new LiquidDehydrator(BlockBehaviour.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Item> LIQUID_DEHYDRATOR_ITEM = fromBlock(LIQUID_DEHYDRATOR);

    public static final RegistryObject<Block> GREENMAN_LURE = BLOCKS.register("greenman_lure",() -> new GreenmanLure(BlockBehaviour.Properties.of(Material.GRASS).sound(SoundType.GLASS).strength(3.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Item> GREENMAN_LURE_ITEM = fromBlock(GREENMAN_LURE);

    public static final RegistryObject<Block> FIZZIUM_BLOCK = BLOCKS.register("fizzium_block",() -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Item> FIZZIUM_BLOCK_ITEM = fromBlock(FIZZIUM_BLOCK);
    public static final RegistryObject<Block> LIQUADIUM_BLOCK = BLOCKS.register("liquadium_block",() -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Item> LIQUADIUM_BLOCK_ITEM = fromBlock(LIQUADIUM_BLOCK);
    public static final RegistryObject<Block> JUICETANIUM_BLOCK = BLOCKS.register("juicetanium_block",() -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Item> JUICETANIUM_BLOCK_ITEM = fromBlock(JUICETANIUM_BLOCK);

    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return XtraDrinksItems.ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().durability(0).stacksTo(64).tab(XtraDrinks.CREATIVE_MODE_TAB)));
    }
}

package Strikeboom.XtraDrinks.init;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

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

    public static final RegistryObject<Block> DEHYDRATOR = BLOCKS.register("dehydrator",() -> new Dehydrator(Block.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.5f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LIQUID_DEHYDRATOR = BLOCKS.register("liquid_dehydrator",() -> new LiquidDehydrator(Block.Properties.of(Material.METAL).sound(SoundType.METAL).strength(3.5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> GREENMAN_LURE = BLOCKS.register("greenman_lure",() -> new GreenmanLure(Block.Properties.of(Material.GRASS).sound(SoundType.GLASS).strength(3.5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> FIZZIUM_BLOCK = BLOCKS.register("fizzium_block",() -> new Block(Block.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> LIQUADIUM_BLOCK = BLOCKS.register("liquadium_block",() -> new Block(Block.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
    public static final RegistryObject<Block> JUICETANIUM_BLOCK = BLOCKS.register("juicetanium_block",() -> new Block(Block.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F, 6.0F).sound(SoundType.METAL)));
}

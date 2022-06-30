package Strikeboom.XtraDrinks.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class XtraDrinksTags {
    public static final Tags.IOptionalNamedTag<Item> INGOT_FIZZIUM = ItemTags.createOptional(new ResourceLocation("forge","ingots/fizzium"));
    public static final Tags.IOptionalNamedTag<Item> INGOT_JUICETANIUM = ItemTags.createOptional(new ResourceLocation("forge","ingots/juicetanium"));
    public static final Tags.IOptionalNamedTag<Item> INGOT_LIQUADIUM = ItemTags.createOptional(new ResourceLocation("forge","ingots/liquadium"));

    public static final Tags.IOptionalNamedTag<Block> STORAGE_BLOCKS_FIZZIUM = BlockTags.createOptional(new ResourceLocation("forge","storage_blocks/fizzium"));
    public static final Tags.IOptionalNamedTag<Block> STORAGE_BLOCKS_JUICETANIUM = BlockTags.createOptional(new ResourceLocation("forge","storage_blocks/juicetanium"));
    public static final Tags.IOptionalNamedTag<Block> STORAGE_BLOCKS_LIQUADIUM = BlockTags.createOptional(new ResourceLocation("forge","storage_blocks/liquadium"));

    public static final Tags.IOptionalNamedTag<Item> FRUIT = ItemTags.createOptional(new ResourceLocation("forge","fruit"));
    public static final Tags.IOptionalNamedTag<Item> FRUIT_CITRIC = ItemTags.createOptional(new ResourceLocation("forge","fruit_citric"));
    public static final Tags.IOptionalNamedTag<Item> FRUIT_TROPICAL = ItemTags.createOptional(new ResourceLocation("forge","fruit_tropical"));

}

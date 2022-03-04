package Strikeboom.xtradrinks.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

public class XtraDrinksTags {
    public static final Tags.IOptionalNamedTag<Item> INGOT_FIZZIUM = ItemTags.createOptional(new ResourceLocation("forge","ingots/fizzium"));
    public static final Tags.IOptionalNamedTag<Item> INGOT_JUICETANIUM = ItemTags.createOptional(new ResourceLocation("forge","ingots/juicetanium"));
    public static final Tags.IOptionalNamedTag<Item> INGOT_LIQUADIUM = ItemTags.createOptional(new ResourceLocation("forge","ingots/liquadium"));

    public static final Tags.IOptionalNamedTag<Item> FRUIT = ItemTags.createOptional(new ResourceLocation("forge","fruit"));
    public static final Tags.IOptionalNamedTag<Item> FRUIT_CITRIC = ItemTags.createOptional(new ResourceLocation("forge","fruit_citric"));
    public static final Tags.IOptionalNamedTag<Item> FRUIT_TROPICAL = ItemTags.createOptional(new ResourceLocation("forge","fruit_tropical"));
}

package Strikeboom.xtradrinks.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

public class XtraDrinksTags {
    public static final TagKey<Item> INGOT_FIZZIUM = ItemTags.create(new ResourceLocation("forge","ingots/fizzium"));
    public static final TagKey<Item> INGOT_JUICETANIUM = ItemTags.create(new ResourceLocation("forge","ingots/juicetanium"));
    public static final TagKey<Item> INGOT_LIQUADIUM = ItemTags.create(new ResourceLocation("forge","ingots/liquadium"));

    public static final TagKey<Item> FRUIT = ItemTags.create(new ResourceLocation("forge","fruit"));
    public static final TagKey<Item> FRUIT_CITRIC = ItemTags.create(new ResourceLocation("forge","fruit_citric"));
    public static final TagKey<Item> FRUIT_TROPICAL = ItemTags.create(new ResourceLocation("forge","fruit_tropical"));
}

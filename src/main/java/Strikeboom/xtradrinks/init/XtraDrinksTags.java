package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class XtraDrinksTags {
    public static final TagKey<Item> INGOT_FIZZIUM = ItemTags.create(new ResourceLocation("forge","ingots/fizzium"));
    public static final TagKey<Item> INGOT_JUICETANIUM = ItemTags.create(new ResourceLocation("forge","ingots/juicetanium"));
    public static final TagKey<Item> INGOT_LIQUADIUM = ItemTags.create(new ResourceLocation("forge","ingots/liquadium"));

    public static final TagKey<Item> FRUIT = ItemTags.create(new ResourceLocation("forge","fruit"));
    public static final TagKey<Item> FRUIT_CITRIC = ItemTags.create(new ResourceLocation("forge","fruit_citric"));
    public static final TagKey<Item> FRUIT_TROPICAL = ItemTags.create(new ResourceLocation("forge","fruit_tropical"));

    public static final TagKey<Block> ORANGE_CAN_SPAWN_ON = BlockTags.create(new ResourceLocation(XtraDrinks.MOD_ID,"orange_can_spawn_on"));
    public static final TagKey<Block> COCONUT_CAN_SPAWN_ON = BlockTags.create(new ResourceLocation(XtraDrinks.MOD_ID,"coconut_can_spawn_on"));
    public static final TagKey<Block> PINES_CAN_SPAWN_ON = BlockTags.create(new ResourceLocation(XtraDrinks.MOD_ID,"pines_can_spawn_on"));

    public static final TagKey<Biome> WELL = TagKey.create(Registry.BIOME_REGISTRY,new ResourceLocation(XtraDrinks.MOD_ID,"has_structure/well"));
    public static final TagKey<Biome> FARM = TagKey.create(Registry.BIOME_REGISTRY,new ResourceLocation(XtraDrinks.MOD_ID,"has_structure/farm"));

}

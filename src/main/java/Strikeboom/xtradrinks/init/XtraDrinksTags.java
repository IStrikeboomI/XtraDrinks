package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class XtraDrinksTags {
    public static final TagKey<Item> INGOT_FIZZIUM = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c","ingots/fizzium"));
    public static final TagKey<Item> INGOT_JUICETANIUM = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c","ingots/juicetanium"));
    public static final TagKey<Item> INGOT_LIQUADIUM = ItemTags.create(ResourceLocation.fromNamespaceAndPath("c","ingots/liquadium"));

    public static final TagKey<Block> ORANGE_CAN_SPAWN_ON = BlockTags.create(ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID,"orange_can_spawn_on"));
    public static final TagKey<Block> COCONUT_CAN_SPAWN_ON = BlockTags.create(ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID,"coconut_can_spawn_on"));
    public static final TagKey<Block> PINES_CAN_SPAWN_ON = BlockTags.create(ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID,"pines_can_spawn_on"));

}

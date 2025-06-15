package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.items.Juicer;
import Strikeboom.xtradrinks.items.TooltipBlockItem;
import Strikeboom.xtradrinks.items.TooltipItem;
import Strikeboom.xtradrinks.items.juice.Juice;
import Strikeboom.xtradrinks.items.juice.ThickJuice;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.function.Consumer;

public class XtraDrinksItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, XtraDrinks.MOD_ID);

    public static final DeferredHolder<Item,Item> FIZZIUM_SHARD = ITEMS.register("fizzium_shard",(loc) -> new Item(new Item.Properties().setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> LIQUADIUM_SHARD = ITEMS.register("liquadium_shard",(loc) -> new Item(new Item.Properties().setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));

    public static final DeferredHolder<Item,Item> FIZZIUM_INGOT = ITEMS.register("fizzium_ingot",(loc) -> new Item(new Item.Properties().setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> LIQUADIUM_INGOT = ITEMS.register("liquadium_ingot",(loc) -> new Item(new Item.Properties().setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> JUICETANIUM_INGOT = ITEMS.register("juicetanium_ingot",(loc) -> new Item(new Item.Properties().setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));

    public static final ArmorMaterial FIZZIUM_ARMOR_MATERIAL = new ArmorMaterial(30, Util.make(new EnumMap<>(ArmorType.class),armorTypeIntegerEnumMap -> {
       armorTypeIntegerEnumMap.put(ArmorType.BOOTS, 3);
       armorTypeIntegerEnumMap.put(ArmorType.LEGGINGS, 5);
       armorTypeIntegerEnumMap.put(ArmorType.CHESTPLATE, 7);
       armorTypeIntegerEnumMap.put(ArmorType.HELMET, 4);
       armorTypeIntegerEnumMap.put(ArmorType.BODY, 11);
    }),10, SoundEvents.ARMOR_EQUIP_GENERIC, 2.0F, 1.0f, XtraDrinksTags.INGOT_FIZZIUM, ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID, "fizzium")));
    public static final DeferredHolder<Item,Item> FIZZIUM_HELMET = ITEMS.register("fizzium_helmet",(loc) -> new TooltipItem("item."+XtraDrinks.MOD_ID+".tooltip.fizzium_armor",new Item.Properties().humanoidArmor(FIZZIUM_ARMOR_MATERIAL,ArmorType.HELMET).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> FIZZIUM_CHESTPLATE = ITEMS.register("fizzium_chestplate",(loc) -> new TooltipItem("item."+XtraDrinks.MOD_ID+".tooltip.fizzium_armor",new Item.Properties().humanoidArmor(FIZZIUM_ARMOR_MATERIAL,ArmorType.CHESTPLATE).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> FIZZIUM_LEGGINGS = ITEMS.register("fizzium_leggings",(loc) -> new TooltipItem("item."+XtraDrinks.MOD_ID+".tooltip.fizzium_armor",new Item.Properties().humanoidArmor(FIZZIUM_ARMOR_MATERIAL,ArmorType.LEGGINGS).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> FIZZIUM_BOOTS = ITEMS.register("fizzium_boots",(loc) -> new TooltipItem("item."+XtraDrinks.MOD_ID+".tooltip.fizzium_armor",new Item.Properties().humanoidArmor(FIZZIUM_ARMOR_MATERIAL,ArmorType.BOOTS).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));

    public static final ArmorMaterial LIQUADIUM_ARMOR_MATERIAL = new ArmorMaterial(30, Util.make(new EnumMap<>(ArmorType.class),armorTypeIntegerEnumMap -> {
        armorTypeIntegerEnumMap.put(ArmorType.BOOTS, 4);
        armorTypeIntegerEnumMap.put(ArmorType.LEGGINGS, 6);
        armorTypeIntegerEnumMap.put(ArmorType.CHESTPLATE, 7);
        armorTypeIntegerEnumMap.put(ArmorType.HELMET, 4);
        armorTypeIntegerEnumMap.put(ArmorType.BODY, 11);
    }),12, SoundEvents.ARMOR_EQUIP_GENERIC, 2.0F, 1.0f, XtraDrinksTags.INGOT_LIQUADIUM, ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID, "liquadium")));
    public static final DeferredHolder<Item,Item> LIQUADIUM_HELMET = ITEMS.register("liquadium_helmet",(loc) -> new TooltipItem("item."+XtraDrinks.MOD_ID+".tooltip.liquadium_armor",new Item.Properties().humanoidArmor(LIQUADIUM_ARMOR_MATERIAL,ArmorType.HELMET).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> LIQUADIUM_CHESTPLATE = ITEMS.register("liquadium_chestplate",(loc) -> new TooltipItem("item."+XtraDrinks.MOD_ID+".tooltip.liquadium_armor",new Item.Properties().humanoidArmor(LIQUADIUM_ARMOR_MATERIAL,ArmorType.CHESTPLATE).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> LIQUADIUM_LEGGINGS = ITEMS.register("liquadium_leggings",(loc) ->new TooltipItem("item."+XtraDrinks.MOD_ID+".tooltip.liquadium_armor",new Item.Properties().humanoidArmor(LIQUADIUM_ARMOR_MATERIAL,ArmorType.LEGGINGS).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> LIQUADIUM_BOOTS = ITEMS.register("liquadium_boots",(loc) ->new TooltipItem("item."+XtraDrinks.MOD_ID+".tooltip.liquadium_armor",new Item.Properties().humanoidArmor(LIQUADIUM_ARMOR_MATERIAL,ArmorType.BOOTS).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));

    public static final ArmorMaterial JUICETANIUM_ARMOR_MATERIAL = new ArmorMaterial(30, Util.make(new EnumMap<>(ArmorType.class),armorTypeIntegerEnumMap -> {
        armorTypeIntegerEnumMap.put(ArmorType.BOOTS, 6);
        armorTypeIntegerEnumMap.put(ArmorType.LEGGINGS, 7);
        armorTypeIntegerEnumMap.put(ArmorType.CHESTPLATE, 9);
        armorTypeIntegerEnumMap.put(ArmorType.HELMET, 6);
        armorTypeIntegerEnumMap.put(ArmorType.BODY, 14);
    }),17, SoundEvents.ARMOR_EQUIP_GENERIC, 3.0F, 2.0f, XtraDrinksTags.INGOT_JUICETANIUM, ResourceKey.create(EquipmentAssets.ROOT_ID, ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID, "juicetanium")));
    public static final DeferredHolder<Item,Item> JUICETANIUM_HELMET = ITEMS.register("juicetanium_helmet",(loc) -> new TooltipItem("item."+XtraDrinks.MOD_ID+".tooltip.juicetanium_armor",new Item.Properties().humanoidArmor(JUICETANIUM_ARMOR_MATERIAL,ArmorType.HELMET).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> JUICETANIUM_CHESTPLATE = ITEMS.register("juicetanium_chestplate",(loc) ->new TooltipItem("item."+XtraDrinks.MOD_ID+".tooltip.juicetanium_armor",new Item.Properties().humanoidArmor(JUICETANIUM_ARMOR_MATERIAL,ArmorType.CHESTPLATE).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> JUICETANIUM_LEGGINGS = ITEMS.register("juicetanium_leggings",(loc) ->new TooltipItem("item."+XtraDrinks.MOD_ID+".tooltip.juicetanium_armor",new Item.Properties().humanoidArmor(JUICETANIUM_ARMOR_MATERIAL,ArmorType.LEGGINGS).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> JUICETANIUM_BOOTS = ITEMS.register("juicetanium_boots",(loc) ->new TooltipItem("item."+XtraDrinks.MOD_ID+".tooltip.juicetanium_armor",new Item.Properties().humanoidArmor(JUICETANIUM_ARMOR_MATERIAL,ArmorType.BOOTS).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    
    public static final DeferredHolder<Item,Item> JUICER = ITEMS.register("juicer",(loc) -> new Juicer("item."+XtraDrinks.MOD_ID+".tooltip.juicer",new Item.Properties().stacksTo(1).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));

    public static final DeferredHolder<Item,Item> PINEAPPLE = ITEMS.register("pineapple",(loc) -> new TooltipBlockItem("item."+XtraDrinks.MOD_ID+".tooltip.crop",XtraDrinksBlocks.PINEAPPLE.get(),new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(.5f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> LEMON = ITEMS.register("lemon",(loc) -> new TooltipBlockItem("item."+XtraDrinks.MOD_ID+".tooltip.crop",XtraDrinksBlocks.LEMON.get(),new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(.5f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> LIME = ITEMS.register("lime",(loc) -> new TooltipBlockItem("item."+XtraDrinks.MOD_ID+".tooltip.crop",XtraDrinksBlocks.LIME.get(),new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(.5f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> POMEGRANATE = ITEMS.register("pomegranate",(loc) -> new TooltipBlockItem("item."+XtraDrinks.MOD_ID+".tooltip.crop",XtraDrinksBlocks.POMEGRANATE.get(),new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(.5f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> GRAPE = ITEMS.register("grape",(loc) -> new TooltipBlockItem("item."+XtraDrinks.MOD_ID+".tooltip.crop",XtraDrinksBlocks.GRAPE .get(),new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(.5f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> CRANBERRY = ITEMS.register("cranberry",(loc) -> new TooltipBlockItem("item."+XtraDrinks.MOD_ID+".tooltip.crop",XtraDrinksBlocks.CRANBERRY.get(),new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(.5f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> BLUEBERRY = ITEMS.register("blueberry",(loc) -> new TooltipBlockItem("item."+XtraDrinks.MOD_ID+".tooltip.crop",XtraDrinksBlocks.BLUEBERRY.get(),new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(.5f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> BLACKBERRY = ITEMS.register("blackberry",(loc) -> new TooltipBlockItem("item."+XtraDrinks.MOD_ID+".tooltip.crop",XtraDrinksBlocks.BLACKBERRY.get(),new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(.5f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> ORANGE = ITEMS.register("orange",(loc) -> new TooltipBlockItem("item."+XtraDrinks.MOD_ID+".tooltip.hanging_crop",XtraDrinksBlocks.ORANGE.get(),new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(.5f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> COCONUT = ITEMS.register("coconut",(loc) -> new TooltipBlockItem("item."+XtraDrinks.MOD_ID+".tooltip.hanging_crop",XtraDrinksBlocks.COCONUT.get(),new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(.5f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> PINES = ITEMS.register("pines",(loc) -> new TooltipBlockItem("item."+XtraDrinks.MOD_ID+".tooltip.hanging_crop",XtraDrinksBlocks.PINES.get(),new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationModifier(.5f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> CINNAMON = ITEMS.register("cinnamon",(loc) -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationModifier(.2f).build(), Consumables.defaultFood().consumeSeconds(.6f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));

    public static final DeferredHolder<Item,Item> DEHYDRATED_GRAPE = ITEMS.register("dehydrated_grape", (loc) -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(1f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> DEHYDRATED_PINEAPPLE = ITEMS.register("dehydrated_pineapple",(loc) -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(1f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> DEHYDRATED_LEMON = ITEMS.register("dehydrated_lemon",(loc) -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(1f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> DEHYDRATED_LIME = ITEMS.register("dehydrated_lime",(loc) -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(1f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> DEHYDRATED_COCONUT = ITEMS.register("dehydrated_coconut",(loc) -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(1f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> DEHYDRATED_APPLE = ITEMS.register("dehydrated_apple",(loc) -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(1f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> DEHYDRATED_ORANGE = ITEMS.register("dehydrated_orange",(loc) -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(1f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> DEHYDRATED_CRANBERRY = ITEMS.register("dehydrated_cranberry",(loc) -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(1f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> DEHYDRATED_BLUEBERRY = ITEMS.register("dehydrated_blueberry",(loc) -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(1f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> DEHYDRATED_BLACKBERRY = ITEMS.register("dehydrated_blackberry",(loc) -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(1f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> DEHYDRATED_CARROT = ITEMS.register("dehydrated_carrot",(loc) -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(1f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> DEHYDRATED_POTATO = ITEMS.register("dehydrated_potato",(loc) -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(1f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> DEHYDRATED_BEETROOT = ITEMS.register("dehydrated_beetroot",(loc) -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(1f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));
    public static final DeferredHolder<Item,Item> DEHYDRATED_POMEGRANATE = ITEMS.register("dehydrated_pomegranate",(loc) -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(7).saturationModifier(1f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));

    public static final DeferredHolder<Item,Item> CITRIC_ACID = ITEMS.register("citric_acid",(loc) -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationModifier(.1f).build(), Consumables.defaultFood().consumeSeconds(.6f).build()).setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),loc))));

    public static final DeferredHolder<Item,Item> DRINK_CUP = ITEMS.register("drink_cup",(loc) -> new Item(new Item.Properties()));
    public static final DeferredHolder<Item,Item> THICK_CUP = ITEMS.register("thick_cup",(loc) -> new Item(new Item.Properties()));

    public static final DeferredHolder<Item,Item> DRINK_APPLE_JUICE = ITEMS.register("drink_apple_juice",(loc) -> new Juice(loc,new MobEffectInstance(MobEffects.SPEED,200)));
    public static final DeferredHolder<Item,Item> DRINK_APPLE_THICK = ITEMS.register("drink_apple_thick", (loc) -> new ThickJuice(loc,new MobEffectInstance(MobEffects.SPEED, 240, 1)));
    public static final DeferredHolder<Item,Item> DRINK_BEET_JUICE = ITEMS.register("drink_beet_juice", (loc) -> new Juice(loc,new MobEffectInstance(MobEffects.RESISTANCE, 200)));
    public static final DeferredHolder<Item,Item> DRINK_CARROT_JUICE = ITEMS.register("drink_carrot_juice", (loc) -> new Juice(loc,new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400)));
    public static final DeferredHolder<Item,Item> DRINK_CARROT_THICK = ITEMS.register("drink_carrot_thick", (loc) -> new ThickJuice(loc,new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 1)));
    public static final DeferredHolder<Item,Item> DRINK_CHOCOLATE_MILK_THICK = ITEMS.register("drink_chocolate_milk_thick", (loc) -> new ThickJuice(loc,new MobEffectInstance(MobEffects.SPEED, 240, 3)));
    public static final DeferredHolder<Item,Item> DRINK_CINNAMON_SPICE_THICK = ITEMS.register("drink_cinnamon_spice_thick", (loc) -> new ThickJuice(loc,new MobEffectInstance(MobEffects.SPEED, 240, 3)));
    public static final DeferredHolder<Item,Item> DRINK_COCONUT_JUICE = ITEMS.register("drink_coconut_juice", (loc) -> new Juice(loc,new MobEffectInstance(MobEffects.REGENERATION, 500, 1)));
    public static final DeferredHolder<Item,Item> DRINK_CRANAPPLE_JUICE = ITEMS.register("drink_cranapple_juice", (loc) -> new Juice(loc,new MobEffectInstance(MobEffects.SPEED, 200)));
    public static final DeferredHolder<Item,Item> DRINK_CRANLEMON_JUICE = ITEMS.register("drink_cranlemon_juice", (loc) -> new Juice(loc,new MobEffectInstance(MobEffects.WATER_BREATHING, 600)));
    public static final DeferredHolder<Item,Item> DRINK_CRANLIME_JUICE = ITEMS.register("drink_cranlime_juice", (loc) -> new Juice(loc,new MobEffectInstance(MobEffects.SPEED, 200)));
    public static final DeferredHolder<Item,Item> DRINK_FIZZIUM_THICK = ITEMS.register("drink_fizzium_thick", (loc) -> new ThickJuice(loc,new MobEffectInstance(MobEffects.SPEED, 600,3)));
    public static final DeferredHolder<Item,Item> DRINK_FRUIT_PUNCH_JUICE = ITEMS.register("drink_fruit_punch_juice", (loc) -> new Juice(loc,new MobEffectInstance(MobEffects.SPEED, 200)));
    public static final DeferredHolder<Item,Item> DRINK_GRAPE_JUICE = ITEMS.register("drink_grape_juice", (loc) -> new Juice(loc,new MobEffectInstance(MobEffects.JUMP_BOOST, 200,1)));
    public static final DeferredHolder<Item,Item> DRINK_JUICETANIUM_THICK = ITEMS.register("drink_juicetanium_thick", (loc) -> new ThickJuice(loc,new MobEffectInstance(MobEffects.SPEED, 1200,3),new MobEffectInstance(MobEffects.SATURATION, 1200),new MobEffectInstance(MobEffects.STRENGTH, 1200,3)));
    public static final DeferredHolder<Item,Item> DRINK_LEMON_JUICE = ITEMS.register("drink_lemon_juice", (loc) -> new Juice(loc,new MobEffectInstance(MobEffects.HASTE, 200,1)));
    public static final DeferredHolder<Item,Item> DRINK_LIME_JUICE = ITEMS.register("drink_lime_juice", (loc) -> new Juice(loc,new MobEffectInstance(MobEffects.HASTE, 200,1)));
    public static final DeferredHolder<Item,Item> DRINK_LIQUADIUM_THICK = ITEMS.register("drink_liquadium_thick", (loc) -> new ThickJuice(loc,new MobEffectInstance(MobEffects.STRENGTH, 600,2)));
    public static final DeferredHolder<Item,Item> DRINK_POTATO_JUICE = ITEMS.register("drink_potato_juice", (loc) -> new Juice(loc,new MobEffectInstance(MobEffects.STRENGTH, 200,1)));
    public static final DeferredHolder<Item,Item> DRINK_SOUR_JUICE = ITEMS.register("drink_sour_juice", (loc) -> new Juice(loc,new MobEffectInstance(MobEffects.ABSORPTION, 400,2),new MobEffectInstance(MobEffects.SPEED, 200)));
    public static final DeferredHolder<Item,Item> DRINK_TROPICAL_PUNCH_JUICE= ITEMS.register("drink_tropical_punch_juice", (loc) -> new Juice(loc,new MobEffectInstance(MobEffects.SPEED, 200),new MobEffectInstance(MobEffects.NIGHT_VISION, 200)));

    public static final DeferredHolder<Item,Item> DEHYDRATOR_ITEM = fromBlock(XtraDrinksBlocks.DEHYDRATOR);
    public static final DeferredHolder<Item,Item> LIQUID_DEHYDRATOR_ITEM = fromBlock(XtraDrinksBlocks.LIQUID_DEHYDRATOR);
    public static final DeferredHolder<Item,Item> GREENMAN_LURE_ITEM = fromBlock(XtraDrinksBlocks.GREENMAN_LURE);
    public static final DeferredHolder<Item,Item> FIZZIUM_BLOCK_ITEM = fromBlock(XtraDrinksBlocks.FIZZIUM_BLOCK);
    public static final DeferredHolder<Item,Item> LIQUADIUM_BLOCK_ITEM = fromBlock(XtraDrinksBlocks.LIQUADIUM_BLOCK);
    public static final DeferredHolder<Item,Item> JUICETANIUM_BLOCK_ITEM = fromBlock(XtraDrinksBlocks.JUICETANIUM_BLOCK);

    public static final DeferredHolder<Item,Item> MOLTEN_FIZZIUM_BUCKET = ITEMS.register("molten_fizzium_bucket", (loc) -> new BucketItem(XtraDrinksFluids.MOLTEN_FIZZIUM.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final DeferredHolder<Item,Item> MOLTEN_LIQUADIUM_BUCKET = ITEMS.register("molten_liquadium_bucket", (loc) -> new BucketItem(XtraDrinksFluids.MOLTEN_LIQUADIUM.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final DeferredHolder<Item,Item> MOLTEN_JUICETANIUM_BUCKET = ITEMS.register("molten_juicetanium_bucket", (loc) -> new BucketItem(XtraDrinksFluids.MOLTEN_JUICETANIUM.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    public static <B extends Block> DeferredHolder<Item,Item> fromBlock(DeferredHolder<B,B> block) {
        return XtraDrinksItems.ITEMS.register(block.getId().getPath(), (loc) -> new BlockItem(block.get(), new Item.Properties().setId(ResourceKey.create(BuiltInRegistries.ITEM.key(),block.getId()))) {
            @Override
            public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
                if (block.get() instanceof TooltipProvider tp) {
                    tp.addToTooltip(context,tooltipAdder,flag,stack);
                }
            }
        });
    }
}

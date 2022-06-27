package Strikeboom.XtraDrinks.init;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.items.Juicer;
import Strikeboom.XtraDrinks.items.armor.FizziumArmor;
import Strikeboom.XtraDrinks.items.armor.JuicetaniumArmor;
import Strikeboom.XtraDrinks.items.armor.LiquadiumArmor;
import Strikeboom.XtraDrinks.items.armor.armormaterials.FizziumArmorMaterial;
import Strikeboom.XtraDrinks.items.armor.armormaterials.JuicetaniumArmorMaterial;
import Strikeboom.XtraDrinks.items.armor.armormaterials.LiquadiumArmorMaterial;
import Strikeboom.XtraDrinks.items.fruit.DehydratedFruit;
import Strikeboom.XtraDrinks.items.fruit.HangingFruit;
import Strikeboom.XtraDrinks.items.fruit.PlantableFruit;
import Strikeboom.XtraDrinks.items.juice.Juice;
import Strikeboom.XtraDrinks.items.juice.ThickJuice;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Food;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class XtraDrinksItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, XtraDrinks.MOD_ID);

    public static final RegistryObject<Item> FIZZIUM_SHARD = ITEMS.register("fizzium_shard",() -> new Item(new Item.Properties().tab(XtraDrinks.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> LIQUADIUM_SHARD = ITEMS.register("liquadium_shard",() -> new Item(new Item.Properties().tab(XtraDrinks.CREATIVE_MODE_TAB)));

    public static final RegistryObject<Item> FIZZIUM_INGOT = ITEMS.register("fizzium_ingot",() -> new Item(new Item.Properties().tab(XtraDrinks.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> LIQUADIUM_INGOT = ITEMS.register("liquadium_ingot",() -> new Item(new Item.Properties().tab(XtraDrinks.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> JUICETANIUM_INGOT = ITEMS.register("juicetanium_ingot",() -> new Item(new Item.Properties().tab(XtraDrinks.CREATIVE_MODE_TAB)));

    public static final IArmorMaterial FIZZIUM_ARMOR_MATERIAL = new FizziumArmorMaterial();
    public static final RegistryObject<Item> FIZZIUM_HELMET = ITEMS.register("fizzium_helmet",() -> new FizziumArmor(FIZZIUM_ARMOR_MATERIAL, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> FIZZIUM_CHESTPLATE = ITEMS.register("fizzium_chestplate",() -> new FizziumArmor(FIZZIUM_ARMOR_MATERIAL,EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> FIZZIUM_LEGGINGS = ITEMS.register("fizzium_leggings",() -> new FizziumArmor(FIZZIUM_ARMOR_MATERIAL,EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> FIZZIUM_BOOTS = ITEMS.register("fizzium_boots",() -> new FizziumArmor(FIZZIUM_ARMOR_MATERIAL,EquipmentSlotType.FEET));
    
    public static final IArmorMaterial LIQUADIUM_ARMOR_MATERIAL = new LiquadiumArmorMaterial();
    public static final RegistryObject<Item> LIQUADIUM_HELMET = ITEMS.register("liquadium_helmet",() -> new LiquadiumArmor(LIQUADIUM_ARMOR_MATERIAL, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> LIQUADIUM_CHESTPLATE = ITEMS.register("liquadium_chestplate",() -> new LiquadiumArmor(LIQUADIUM_ARMOR_MATERIAL,EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> LIQUADIUM_LEGGINGS = ITEMS.register("liquadium_leggings",() -> new LiquadiumArmor(LIQUADIUM_ARMOR_MATERIAL,EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> LIQUADIUM_BOOTS = ITEMS.register("liquadium_boots",() -> new LiquadiumArmor(LIQUADIUM_ARMOR_MATERIAL,EquipmentSlotType.FEET));
    
    public static final IArmorMaterial JUICETANIUM_ARMOR_MATERIAL = new JuicetaniumArmorMaterial();
    public static final RegistryObject<Item> JUICETANIUM_HELMET = ITEMS.register("juicetanium_helmet",() -> new JuicetaniumArmor(JUICETANIUM_ARMOR_MATERIAL, EquipmentSlotType.HEAD));
    public static final RegistryObject<Item> JUICETANIUM_CHESTPLATE = ITEMS.register("juicetanium_chestplate",() -> new JuicetaniumArmor(JUICETANIUM_ARMOR_MATERIAL,EquipmentSlotType.CHEST));
    public static final RegistryObject<Item> JUICETANIUM_LEGGINGS = ITEMS.register("juicetanium_leggings",() -> new JuicetaniumArmor(JUICETANIUM_ARMOR_MATERIAL,EquipmentSlotType.LEGS));
    public static final RegistryObject<Item> JUICETANIUM_BOOTS = ITEMS.register("juicetanium_boots",() -> new JuicetaniumArmor(JUICETANIUM_ARMOR_MATERIAL,EquipmentSlotType.FEET));
    
    public static final RegistryObject<Item> JUICER = ITEMS.register("juicer",() -> new Juicer(new Item.Properties().stacksTo(1).tab(XtraDrinks.CREATIVE_MODE_TAB)));

    public static final RegistryObject<Item> PINEAPPLE = ITEMS.register("pineapple",() -> new PlantableFruit(XtraDrinksBlocks.PINEAPPLE.get()));
    public static final RegistryObject<Item> LEMON = ITEMS.register("lemon",() -> new PlantableFruit(XtraDrinksBlocks.LEMON.get()));
    public static final RegistryObject<Item> LIME = ITEMS.register("lime",() -> new PlantableFruit(XtraDrinksBlocks.LIME.get()));
    public static final RegistryObject<Item> POMEGRANATE = ITEMS.register("pomegranate",() -> new PlantableFruit(XtraDrinksBlocks.POMEGRANATE.get()));
    public static final RegistryObject<Item> GRAPE = ITEMS.register("grape",() -> new PlantableFruit(XtraDrinksBlocks.GRAPE.get()));
    public static final RegistryObject<Item> CRANBERRY = ITEMS.register("cranberry",() -> new PlantableFruit(XtraDrinksBlocks.CRANBERRY.get()));
    public static final RegistryObject<Item> BLUEBERRY = ITEMS.register("blueberry",() -> new PlantableFruit(XtraDrinksBlocks.BLUEBERRY.get()));
    public static final RegistryObject<Item> BLACKBERRY = ITEMS.register("blackberry",() -> new PlantableFruit(XtraDrinksBlocks.BLACKBERRY.get()));
    public static final RegistryObject<Item> ORANGE = ITEMS.register("orange",() -> new HangingFruit(XtraDrinksBlocks.ORANGE.get()));
    public static final RegistryObject<Item> COCONUT = ITEMS.register("coconut",() -> new HangingFruit(XtraDrinksBlocks.COCONUT.get()));
    public static final RegistryObject<Item> PINES = ITEMS.register("pines",() -> new HangingFruit(XtraDrinksBlocks.PINES.get()));
    public static final RegistryObject<Item> CINNAMON = ITEMS.register("cinnamon",() -> new Item(new Item.Properties().tab(XtraDrinks.CREATIVE_MODE_TAB)));

    public static final RegistryObject<Item> DEHYDRATED_GRAPE = ITEMS.register("dehydrated_grape", DehydratedFruit::new);
    public static final RegistryObject<Item> DEHYDRATED_PINEAPPLE = ITEMS.register("dehydrated_pineapple",DehydratedFruit::new);
    public static final RegistryObject<Item> DEHYDRATED_LEMON = ITEMS.register("dehydrated_lemon",DehydratedFruit::new);
    public static final RegistryObject<Item> DEHYDRATED_LIME = ITEMS.register("dehydrated_lime",DehydratedFruit::new);
    public static final RegistryObject<Item> DEHYDRATED_COCONUT = ITEMS.register("dehydrated_coconut",DehydratedFruit::new);
    public static final RegistryObject<Item> DEHYDRATED_APPLE = ITEMS.register("dehydrated_apple",DehydratedFruit::new);
    public static final RegistryObject<Item> DEHYDRATED_ORANGE = ITEMS.register("dehydrated_orange",DehydratedFruit::new);
    public static final RegistryObject<Item> DEHYDRATED_CRANBERRY = ITEMS.register("dehydrated_cranberry",DehydratedFruit::new);
    public static final RegistryObject<Item> DEHYDRATED_BLUEBERRY = ITEMS.register("dehydrated_blueberry",DehydratedFruit::new);
    public static final RegistryObject<Item> DEHYDRATED_BLACKBERRY = ITEMS.register("dehydrated_blackberry",DehydratedFruit::new);
    public static final RegistryObject<Item> DEHYDRATED_CARROT = ITEMS.register("dehydrated_carrot",DehydratedFruit::new);
    public static final RegistryObject<Item> DEHYDRATED_POTATO = ITEMS.register("dehydrated_potato",DehydratedFruit::new);
    public static final RegistryObject<Item> DEHYDRATED_BEETROOT = ITEMS.register("dehydrated_beetroot",DehydratedFruit::new);
    public static final RegistryObject<Item> DEHYDRATED_POMEGRANATE = ITEMS.register("dehydrated_pomegranate",DehydratedFruit::new);

    public static final RegistryObject<Item> CITRIC_ACID = ITEMS.register("citric_acid",() -> new Item(new Item.Properties().food(new Food.Builder().nutrition(1).saturationMod(.4f).fast().build()).tab(XtraDrinks.CREATIVE_MODE_TAB)));

    public static final RegistryObject<Item> DRINK_CUP = ITEMS.register("drink_cup",() -> new Item(new Item.Properties().tab(XtraDrinks.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> THICK_CUP = ITEMS.register("thick_cup",() -> new Item(new Item.Properties().tab(XtraDrinks.CREATIVE_MODE_TAB)));

    public static final RegistryObject<Item> DRINK_APPLE_JUICE = ITEMS.register("drink_apple_juice",() -> new Juice(new EffectInstance(Effects.MOVEMENT_SPEED,200)));
    public static final RegistryObject<Item> DRINK_APPLE_THICK = ITEMS.register("drink_apple_thick", () -> new ThickJuice(new EffectInstance(Effects.MOVEMENT_SPEED, 240, 1)));
    public static final RegistryObject<Item> DRINK_BEET_JUICE = ITEMS.register("drink_beet_juice", () -> new Juice(new EffectInstance(Effects.DAMAGE_RESISTANCE, 200)));
    public static final RegistryObject<Item> DRINK_CARROT_JUICE = ITEMS.register("drink_carrot_juice", () -> new Juice(new EffectInstance(Effects.FIRE_RESISTANCE, 400)));
    public static final RegistryObject<Item> DRINK_CARROT_THICK = ITEMS.register("drink_carrot_thick", () -> new ThickJuice(new EffectInstance(Effects.FIRE_RESISTANCE, 800, 1)));
    public static final RegistryObject<Item> DRINK_CHOCOLATE_MILK_THICK = ITEMS.register("drink_chocolate_milk_thick", () -> new ThickJuice(new EffectInstance(Effects.MOVEMENT_SPEED, 240, 3)));
    public static final RegistryObject<Item> DRINK_CINNAMON_SPICE_THICK = ITEMS.register("drink_cinnamon_spice_thick", () -> new ThickJuice(new EffectInstance(Effects.MOVEMENT_SPEED, 240, 3)));
    public static final RegistryObject<Item> DRINK_COCONUT_JUICE = ITEMS.register("drink_coconut_juice", () -> new Juice(new EffectInstance(Effects.REGENERATION, 500, 1)));
    public static final RegistryObject<Item> DRINK_CRANAPPLE_JUICE = ITEMS.register("drink_cranapple_juice", () -> new Juice(new EffectInstance(Effects.MOVEMENT_SPEED, 200)));
    public static final RegistryObject<Item> DRINK_CRANLEMON_JUICE = ITEMS.register("drink_cranlemon_juice", () -> new Juice(new EffectInstance(Effects.WATER_BREATHING, 600)));
    public static final RegistryObject<Item> DRINK_CRANLIME_JUICE = ITEMS.register("drink_cranlime_juice", () -> new Juice(new EffectInstance(Effects.MOVEMENT_SPEED, 200)));
    public static final RegistryObject<Item> DRINK_FIZZIUM_THICK = ITEMS.register("drink_fizzium_thick", () -> new ThickJuice(new EffectInstance(Effects.MOVEMENT_SPEED, 600,3)));
    public static final RegistryObject<Item> DRINK_FRUIT_PUNCH_JUICE = ITEMS.register("drink_fruit_punch_juice", () -> new Juice(new EffectInstance(Effects.MOVEMENT_SPEED, 200)));
    public static final RegistryObject<Item> DRINK_GRAPE_JUICE = ITEMS.register("drink_grape_juice", () -> new Juice(new EffectInstance(Effects.JUMP, 200,1)));
    public static final RegistryObject<Item> DRINK_JUICETANIUM_THICK = ITEMS.register("drink_juicetanium_thick", () -> new ThickJuice(new EffectInstance(Effects.MOVEMENT_SPEED, 1200,3),new EffectInstance(Effects.SATURATION, 1200),new EffectInstance(Effects.DAMAGE_BOOST, 1200,3)));
    public static final RegistryObject<Item> DRINK_LEMON_JUICE = ITEMS.register("drink_lemon_juice", () -> new Juice(new EffectInstance(Effects.DIG_SPEED, 200,1)));
    public static final RegistryObject<Item> DRINK_LIME_JUICE = ITEMS.register("drink_lime_juice", () -> new Juice(new EffectInstance(Effects.DIG_SPEED, 200,1)));
    public static final RegistryObject<Item> DRINK_LIQUADIUM_THICK = ITEMS.register("drink_liquadium_thick", () -> new ThickJuice(new EffectInstance(Effects.DAMAGE_BOOST, 600,2)));
    public static final RegistryObject<Item> DRINK_POTATO_JUICE = ITEMS.register("drink_potato_juice", () -> new Juice(new EffectInstance(Effects.DAMAGE_BOOST, 200,1)));
    public static final RegistryObject<Item> DRINK_SOUR_JUICE = ITEMS.register("drink_sour_juice", () -> new Juice(new EffectInstance(Effects.ABSORPTION, 400,2),new EffectInstance(Effects.MOVEMENT_SPEED, 200)));
    public static final RegistryObject<Item> DRINK_TROPICAL_PUNCH_JUICE= ITEMS.register("drink_tropical_punch_juice", () -> new Juice(new EffectInstance(Effects.MOVEMENT_SPEED, 200),new EffectInstance(Effects.NIGHT_VISION, 200)));





















}

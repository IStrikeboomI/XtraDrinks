package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.blocks.Dehydrator;
import Strikeboom.xtradrinks.blocks.GreenmanLure;
import Strikeboom.xtradrinks.blocks.LiquidDehydrator;
import Strikeboom.xtradrinks.items.Juicer;
import Strikeboom.xtradrinks.items.armor.FizziumArmor;
import Strikeboom.xtradrinks.items.armor.JuicetaniumArmor;
import Strikeboom.xtradrinks.items.armor.LiquadiumArmor;
import Strikeboom.xtradrinks.items.armor.armormaterials.FizziumArmorMaterial;
import Strikeboom.xtradrinks.items.armor.armormaterials.JuicetaniumArmorMaterial;
import Strikeboom.xtradrinks.items.armor.armormaterials.LiquadiumArmorMaterial;
import Strikeboom.xtradrinks.items.fruit.DehydratedFruit;
import Strikeboom.xtradrinks.items.fruit.HangingFruit;
import Strikeboom.xtradrinks.items.fruit.PlantableFruit;
import Strikeboom.xtradrinks.items.juice.Juice;
import Strikeboom.xtradrinks.items.juice.ThickJuice;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class XtraDrinksItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, XtraDrinks.MOD_ID);

    public static final RegistryObject<Item> FIZZIUM_SHARD = ITEMS.register("fizzium_shard",() -> new Item(new Item.Properties().tab(XtraDrinks.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> LIQUADIUM_SHARD = ITEMS.register("liquadium_shard",() -> new Item(new Item.Properties().tab(XtraDrinks.CREATIVE_MODE_TAB)));

    public static final RegistryObject<Item> FIZZIUM_INGOT = ITEMS.register("fizzium_ingot",() -> new Item(new Item.Properties().tab(XtraDrinks.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> LIQUADIUM_INGOT = ITEMS.register("liquadium_ingot",() -> new Item(new Item.Properties().tab(XtraDrinks.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> JUICETANIUM_INGOT = ITEMS.register("juicetanium_ingot",() -> new Item(new Item.Properties().tab(XtraDrinks.CREATIVE_MODE_TAB)));

    public static final ArmorMaterial FIZZIUM_ARMOR_MATERIAL = new FizziumArmorMaterial();
    public static final RegistryObject<Item> FIZZIUM_HELMET = ITEMS.register("fizzium_helmet",() -> new FizziumArmor(FIZZIUM_ARMOR_MATERIAL, EquipmentSlot.HEAD));
    public static final RegistryObject<Item> FIZZIUM_CHESTPLATE = ITEMS.register("fizzium_chestplate",() -> new FizziumArmor(FIZZIUM_ARMOR_MATERIAL,EquipmentSlot.CHEST));
    public static final RegistryObject<Item> FIZZIUM_LEGGINGS = ITEMS.register("fizzium_leggings",() -> new FizziumArmor(FIZZIUM_ARMOR_MATERIAL,EquipmentSlot.LEGS));
    public static final RegistryObject<Item> FIZZIUM_BOOTS = ITEMS.register("fizzium_boots",() -> new FizziumArmor(FIZZIUM_ARMOR_MATERIAL,EquipmentSlot.FEET));
    
    public static final ArmorMaterial LIQUADIUM_ARMOR_MATERIAL = new LiquadiumArmorMaterial();
    public static final RegistryObject<Item> LIQUADIUM_HELMET = ITEMS.register("liquadium_helmet",() -> new LiquadiumArmor(LIQUADIUM_ARMOR_MATERIAL, EquipmentSlot.HEAD));
    public static final RegistryObject<Item> LIQUADIUM_CHESTPLATE = ITEMS.register("liquadium_chestplate",() -> new LiquadiumArmor(LIQUADIUM_ARMOR_MATERIAL,EquipmentSlot.CHEST));
    public static final RegistryObject<Item> LIQUADIUM_LEGGINGS = ITEMS.register("liquadium_leggings",() -> new LiquadiumArmor(LIQUADIUM_ARMOR_MATERIAL,EquipmentSlot.LEGS));
    public static final RegistryObject<Item> LIQUADIUM_BOOTS = ITEMS.register("liquadium_boots",() -> new LiquadiumArmor(LIQUADIUM_ARMOR_MATERIAL,EquipmentSlot.FEET));
    
    public static final ArmorMaterial JUICETANIUM_ARMOR_MATERIAL = new JuicetaniumArmorMaterial();
    public static final RegistryObject<Item> JUICETANIUM_HELMET = ITEMS.register("juicetanium_helmet",() -> new JuicetaniumArmor(JUICETANIUM_ARMOR_MATERIAL, EquipmentSlot.HEAD));
    public static final RegistryObject<Item> JUICETANIUM_CHESTPLATE = ITEMS.register("juicetanium_chestplate",() -> new JuicetaniumArmor(JUICETANIUM_ARMOR_MATERIAL,EquipmentSlot.CHEST));
    public static final RegistryObject<Item> JUICETANIUM_LEGGINGS = ITEMS.register("juicetanium_leggings",() -> new JuicetaniumArmor(JUICETANIUM_ARMOR_MATERIAL,EquipmentSlot.LEGS));
    public static final RegistryObject<Item> JUICETANIUM_BOOTS = ITEMS.register("juicetanium_boots",() -> new JuicetaniumArmor(JUICETANIUM_ARMOR_MATERIAL,EquipmentSlot.FEET));
    
    public static final RegistryObject<Item> JUICER = ITEMS.register("juicer",() -> new Juicer(new Item.Properties().stacksTo(1).tab(XtraDrinks.CREATIVE_MODE_TAB)));

    public static final RegistryObject<Item> PINEAPPLE = ITEMS.register("pineapple",() -> new PlantableFruit(XtraDrinksBlocks.PINEAPPLE.get()));
    public static final RegistryObject<Item> LEMON = ITEMS.register("lemon",() -> new PlantableFruit(XtraDrinksBlocks.LEMON.get()));
    public static final RegistryObject<Item> LIME = ITEMS.register("lime",() -> new PlantableFruit(XtraDrinksBlocks.LIME.get()));
    public static final RegistryObject<Item> POMEGRANATE = ITEMS.register("pomegranate",() -> new PlantableFruit(XtraDrinksBlocks.POMEGRANATE.get()));
    public static final RegistryObject<Item> GRAPE = ITEMS.register("grape",() -> new PlantableFruit(XtraDrinksBlocks.GRAPE .get()));
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

    public static final RegistryObject<Item> CITRIC_ACID = ITEMS.register("citric_acid",() -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(1).saturationMod(.4f).fast().build()).tab(XtraDrinks.CREATIVE_MODE_TAB)));

    public static final RegistryObject<Item> DRINK_CUP = ITEMS.register("drink_cup",() -> new Item(new Item.Properties().tab(XtraDrinks.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> THICK_CUP = ITEMS.register("thick_cup",() -> new Item(new Item.Properties().tab(XtraDrinks.CREATIVE_MODE_TAB)));

    public static final RegistryObject<Item> DRINK_APPLE_JUICE = ITEMS.register("drink_apple_juice",() -> new Juice(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,200)));
    public static final RegistryObject<Item> DRINK_APPLE_THICK = ITEMS.register("drink_apple_thick", () -> new ThickJuice(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 240, 1)));
    public static final RegistryObject<Item> DRINK_BEET_JUICE = ITEMS.register("drink_beet_juice", () -> new Juice(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200)));
    public static final RegistryObject<Item> DRINK_CARROT_JUICE = ITEMS.register("drink_carrot_juice", () -> new Juice(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400)));
    public static final RegistryObject<Item> DRINK_CARROT_THICK = ITEMS.register("drink_carrot_thick", () -> new ThickJuice(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 1)));
    public static final RegistryObject<Item> DRINK_CHOCOLATE_MILK_THICK = ITEMS.register("drink_chocolate_milk_thick", () -> new ThickJuice(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 240, 3)));
    public static final RegistryObject<Item> DRINK_CINNAMON_SPICE_THICK = ITEMS.register("drink_cinnamon_spice_thick", () -> new ThickJuice(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 240, 3)));
    public static final RegistryObject<Item> DRINK_COCONUT_JUICE = ITEMS.register("drink_coconut_juice", () -> new Juice(new MobEffectInstance(MobEffects.REGENERATION, 500, 1)));
    public static final RegistryObject<Item> DRINK_CRANAPPLE_JUICE = ITEMS.register("drink_cranapple_juice", () -> new Juice(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200)));
    public static final RegistryObject<Item> DRINK_CRANLEMON_JUICE = ITEMS.register("drink_cranlemon_juice", () -> new Juice(new MobEffectInstance(MobEffects.WATER_BREATHING, 600)));
    public static final RegistryObject<Item> DRINK_CRANLIME_JUICE = ITEMS.register("drink_cranlime_juice", () -> new Juice(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200)));
    public static final RegistryObject<Item> DRINK_FIZZIUM_THICK = ITEMS.register("drink_fizzium_thick", () -> new ThickJuice(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 600,3)));
    public static final RegistryObject<Item> DRINK_FRUIT_PUNCH_JUICE = ITEMS.register("drink_fruit_punch_juice", () -> new Juice(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200)));
    public static final RegistryObject<Item> DRINK_GRAPE_JUICE = ITEMS.register("drink_grape_juice", () -> new Juice(new MobEffectInstance(MobEffects.JUMP, 200,1)));
    public static final RegistryObject<Item> DRINK_JUICETANIUM_THICK = ITEMS.register("drink_juicetanium_thick", () -> new ThickJuice(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200,3),new MobEffectInstance(MobEffects.SATURATION, 1200),new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200,3)));
    public static final RegistryObject<Item> DRINK_LEMON_JUICE = ITEMS.register("drink_lemon_juice", () -> new Juice(new MobEffectInstance(MobEffects.DIG_SPEED, 200,1)));
    public static final RegistryObject<Item> DRINK_LIME_JUICE = ITEMS.register("drink_lime_juice", () -> new Juice(new MobEffectInstance(MobEffects.DIG_SPEED, 200,1)));
    public static final RegistryObject<Item> DRINK_LIQUADIUM_THICK = ITEMS.register("drink_liquadium_thick", () -> new ThickJuice(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600,2)));
    public static final RegistryObject<Item> DRINK_POTATO_JUICE = ITEMS.register("drink_potato_juice", () -> new Juice(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200,1)));
    public static final RegistryObject<Item> DRINK_SOUR_JUICE = ITEMS.register("drink_sour_juice", () -> new Juice(new MobEffectInstance(MobEffects.ABSORPTION, 400,2),new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200)));
    public static final RegistryObject<Item> DRINK_TROPICAL_PUNCH_JUICE= ITEMS.register("drink_tropical_punch_juice", () -> new Juice(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200),new MobEffectInstance(MobEffects.NIGHT_VISION, 200)));

    public static final RegistryObject<Item> DEHYDRATOR_ITEM = fromBlock(XtraDrinksBlocks.DEHYDRATOR);
    public static final RegistryObject<Item> LIQUID_DEHYDRATOR_ITEM = fromBlock(XtraDrinksBlocks.LIQUID_DEHYDRATOR);
    public static final RegistryObject<Item> GREENMAN_LURE_ITEM = fromBlock(XtraDrinksBlocks.GREENMAN_LURE);
    public static final RegistryObject<Item> FIZZIUM_BLOCK_ITEM = fromBlock(XtraDrinksBlocks.FIZZIUM_BLOCK);
    public static final RegistryObject<Item> LIQUADIUM_BLOCK_ITEM = fromBlock(XtraDrinksBlocks.LIQUADIUM_BLOCK);
    public static final RegistryObject<Item> JUICETANIUM_BLOCK_ITEM = fromBlock(XtraDrinksBlocks.JUICETANIUM_BLOCK);

    public static final RegistryObject<Item> MOLTEN_FIZZIUM_BUCKET = ITEMS.register("molten_fizzium_bucket", () -> new BucketItem(XtraDrinksFluids.MOLTEN_FIZZIUM, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(XtraDrinks.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> MOLTEN_LIQUADIUM_BUCKET = ITEMS.register("molten_liquadium_bucket", () -> new BucketItem(XtraDrinksFluids.MOLTEN_LIQUADIUM, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(XtraDrinks.CREATIVE_MODE_TAB)));
    public static final RegistryObject<Item> MOLTEN_JUICETANIUM_BUCKET = ITEMS.register("molten_juicetanium_bucket", () -> new BucketItem(XtraDrinksFluids.MOLTEN_JUICETANIUM, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(XtraDrinks.CREATIVE_MODE_TAB)));


    private static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().durability(0).stacksTo(64).tab(XtraDrinks.CREATIVE_MODE_TAB)));
    }
}

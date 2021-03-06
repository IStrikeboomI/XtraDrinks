package Strikeboom.xtradrinks.init;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.items.Juicer;
import Strikeboom.xtradrinks.items.armor.ArmorMaterials;
import Strikeboom.xtradrinks.items.armor.FizziumArmor;
import Strikeboom.xtradrinks.items.armor.JucetaniumArmor;
import Strikeboom.xtradrinks.items.armor.LiquadiumArmor;
import Strikeboom.xtradrinks.items.fruit.*;
import Strikeboom.xtradrinks.items.juice.Juice;
import Strikeboom.xtradrinks.items.juice.ThickJuice;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.PotionEffect;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

public class ModItems {
    public static final List<Item> ITEMS = new LinkedList<>();

    public static Item juicer;
    public static Item pomegranate;
    public static Item grape;
    public static Item pineapple;
    public static Item pines;
    public static Item cinnamon;
    public static Item lemon;
    public static Item lime;
    public static Item coconut;
    public static Item orange;
    public static Item cranberry;
    public static Item blueberry;
    public static Item blackberry;
    public static Item dehydrated_grape;
    public static Item dehydrated_pineapple;
    public static Item dehydrated_lemon;
    public static Item dehydrated_lime;
    public static Item dehydrated_coconut;
    public static Item dehydrated_apple;
    public static Item dehydrated_orange;
    public static Item dehydrated_cranberry;
    public static Item dehydrated_blueberry;
    public static Item dehydrated_blackberry;
    public static Item dehydrated_carrot;
    public static Item dehydrated_potato;
    public static Item dehydrated_beetroot;
    public static Item dehydrated_pomegranate;
    public static Item fizzium_shard;
    public static Item liquadium_shard;
    public static Item fizzium_helmet;
    public static Item fizzium_chestplate;
    public static Item fizzium_leggings;
    public static Item fizzium_boots;
    public static Item liquadium_helmet;
    public static Item liquadium_chestplate;
    public static Item liquadium_leggings;
    public static Item liquadium_boots;
    public static Item jucetanium_helmet;
    public static Item jucetanium_chestplate;
    public static Item jucetanium_leggings;
    public static Item jucetanium_boots;
    public static Item fizzium_ingot;
    public static Item liquadium_ingot;
    public static Item jucetanium_ingot;
    public static Item citric_acid;
    public static Item drink_cup;
    public static Item drink_thick;
    public static Item drink_apple_juice;
    public static Item drink_apple_thick;
    public static Item drink_beet_juice;
    public static Item drink_carrot_juice;
    public static Item drink_carrot_thick;
    public static Item drink_chocolate_milk_thick;
    public static Item drink_cinnamon_spice_thick;
    public static Item drink_coconut_juice;
    public static Item drink_cranapple_juice;
    public static Item drink_cranlemon_juice;
    public static Item drink_cranlime_juice;
    public static Item drink_fizzium_thick;
    public static Item drink_fruit_punch_juice;
    public static Item drink_grape_juice;
    public static Item drink_jucetanium_thick;
    public static Item drink_lemon_juice;
    public static Item drink_lime_juice;
    public static Item drink_liquadium_thick;
    public static Item drink_potato_juice;
    public static Item drink_sour_juice;
    public static Item drink_tropical_punch_juice;

    public static void preInit() {

        juicer = new Juicer().setRegistryName("juicer").setUnlocalizedName("juicer").setMaxStackSize(1).setContainerItem(ModItems.juicer);
        fizzium_shard = new Item().setRegistryName("fizzium_shard").setUnlocalizedName("fizzium_shard");
        liquadium_shard = new Item().setRegistryName("liquadium_shard").setUnlocalizedName("liquadium_shard");
        fizzium_ingot = new Item().setRegistryName("fizzium_ingot").setUnlocalizedName("fizzium_ingot");
        liquadium_ingot = new Item().setRegistryName("liquadium_ingot").setUnlocalizedName("liquadium_ingot");
        jucetanium_ingot = new Item().setRegistryName("jucetanium_ingot").setUnlocalizedName("jucetanium_ingot");
        citric_acid = new ItemFood(1,0.1f,false).setRegistryName("citric_acid").setUnlocalizedName("citric_acid");
        drink_cup = new Item().setRegistryName("drink_cup").setUnlocalizedName("drink_cup");
        drink_thick = new Item().setRegistryName("drink_thick").setUnlocalizedName("drink_thick");

        drink_apple_juice = new Juice(new PotionEffect(MobEffects.SPEED,10)).setRegistryName("drink_apple_juice").setUnlocalizedName("drink_apple_juice");
        drink_apple_thick = new ThickJuice(new PotionEffect(MobEffects.SPEED, 10, 1)).setRegistryName("drink_apple_thick").setUnlocalizedName("drink_apple_thick");
        drink_beet_juice = new Juice(new PotionEffect(MobEffects.RESISTANCE,10)).setRegistryName("drink_beet_juice").setUnlocalizedName("drink_beet_juice");
        drink_carrot_juice  = new Juice(new PotionEffect(MobEffects.RESISTANCE,5, 1)).setRegistryName("drink_carrot_juice").setUnlocalizedName("drink_carrot_juice");
        drink_carrot_thick = new ThickJuice(new PotionEffect(MobEffects.RESISTANCE,10, 1)).setRegistryName("drink_carrot_thick").setUnlocalizedName("drink_carrot_thick");
        drink_chocolate_milk_thick = new    ThickJuice(new PotionEffect(MobEffects.SPEED,10),new PotionEffect(MobEffects.RESISTANCE,10)).setRegistryName("drink_chocolate_milk_thick").setUnlocalizedName("drink_chocolate_milk_thick");
        drink_cinnamon_spice_thick = new ThickJuice(new PotionEffect(MobEffects.SPEED,10,2)).setRegistryName("drink_cinnamon_spice_thick").setUnlocalizedName("drink_cinnamon_spice_thick");
        drink_coconut_juice = new Juice(new PotionEffect(MobEffects.REGENERATION,15)).setRegistryName("drink_coconut_juice").setUnlocalizedName("drink_coconut_juice");
        drink_cranapple_juice = new Juice(new PotionEffect(MobEffects.SPEED,10)).setRegistryName("drink_cranapple_juice").setUnlocalizedName("drink_cranapple_juice");
        drink_cranlemon_juice = new Juice(new PotionEffect(MobEffects.SPEED,10)).setRegistryName("drink_cranlemon_juice").setUnlocalizedName("drink_cranlemon_juice");
        drink_cranlime_juice = new Juice(new PotionEffect(MobEffects.SPEED,10)).setRegistryName("drink_cranlime_juice").setUnlocalizedName("drink_cranlime_juice");
        drink_fizzium_thick = new ThickJuice(new PotionEffect(MobEffects.SPEED,10,3)).setRegistryName("drink_fizzium_thick").setUnlocalizedName("drink_fizzium_thick");
        drink_fruit_punch_juice = new Juice(new PotionEffect(MobEffects.SPEED,15)).setRegistryName("drink_fruit_punch_juice").setUnlocalizedName("drink_fruit_punch_juice");
        drink_grape_juice = new Juice(new PotionEffect(MobEffects.SPEED,10)).setRegistryName("drink_grape_juice").setUnlocalizedName("drink_grape_juice");
        drink_jucetanium_thick = new ThickJuice(new PotionEffect(MobEffects.SPEED,20,3),new PotionEffect(MobEffects.STRENGTH,20,2),new PotionEffect(MobEffects.SATURATION,10)).setRegistryName("drink_jucetanium_thick").setUnlocalizedName("drink_jucetanium_thick");
        drink_lemon_juice = new Juice(new PotionEffect(MobEffects.HASTE,20,3)).setRegistryName("drink_lemon_juice").setUnlocalizedName("drink_lemon_juice");
        drink_lime_juice = new Juice(new PotionEffect(MobEffects.HASTE,20,3)).setRegistryName("drink_lime_juice").setUnlocalizedName("drink_lime_juice");
        drink_liquadium_thick = new ThickJuice(new PotionEffect(MobEffects.STRENGTH,15,2)).setRegistryName("drink_liquadium_thick").setUnlocalizedName("drink_liquadium_thick");
        drink_potato_juice = new Juice(new PotionEffect(MobEffects.STRENGTH,10,1)).setRegistryName("drink_potato_juice").setUnlocalizedName("drink_potato_juice");
        drink_sour_juice = new Juice(new PotionEffect(MobEffects.ABSORPTION,20,2)).setRegistryName("drink_sour_juice").setUnlocalizedName("drink_sour_juice");
        drink_tropical_punch_juice = new Juice(new PotionEffect(MobEffects.NIGHT_VISION,30,2)).setRegistryName("drink_tropical_punch_juice").setUnlocalizedName("drink_tropical_punch_juice");

        fizzium_helmet = new FizziumArmor(ArmorMaterials.FIZZIUM,1, EntityEquipmentSlot.HEAD).setRegistryName("fizzium_helmet").setUnlocalizedName("fizzium_helmet");
        fizzium_chestplate = new FizziumArmor(ArmorMaterials.FIZZIUM,1,EntityEquipmentSlot.CHEST).setRegistryName("fizzium_chestplate").setUnlocalizedName("fizzium_chestplate");
        fizzium_leggings = new FizziumArmor(ArmorMaterials.FIZZIUM,2,EntityEquipmentSlot.LEGS).setRegistryName("fizzium_leggings").setUnlocalizedName("fizzium_leggings");
        fizzium_boots = new FizziumArmor(ArmorMaterials.FIZZIUM,1,EntityEquipmentSlot.FEET).setRegistryName("fizzium_boots").setUnlocalizedName("fizzium_boots");
        liquadium_helmet = new LiquadiumArmor(ArmorMaterials.LIQUIDADIUM,1,EntityEquipmentSlot.HEAD).setRegistryName("liquadium_helmet").setUnlocalizedName("liquadium_helmet");
        liquadium_chestplate = new LiquadiumArmor(ArmorMaterials.LIQUIDADIUM,1,EntityEquipmentSlot.CHEST).setRegistryName("liquadium_chestplate").setUnlocalizedName("liquadium_chestplate");
        liquadium_leggings = new LiquadiumArmor(ArmorMaterials.LIQUIDADIUM,2,EntityEquipmentSlot.LEGS).setRegistryName("liquadium_leggings").setUnlocalizedName("liquadium_leggings");
        liquadium_boots = new LiquadiumArmor(ArmorMaterials.LIQUIDADIUM,1,EntityEquipmentSlot.FEET).setRegistryName("liquadium_boots").setUnlocalizedName("liquadium_boots");
        jucetanium_helmet = new JucetaniumArmor(ArmorMaterials.JUICEITANIUM,1,EntityEquipmentSlot.HEAD).setRegistryName("jucetanium_helmet").setUnlocalizedName("jucetanium_helmet");
        jucetanium_chestplate = new JucetaniumArmor(ArmorMaterials.JUICEITANIUM,1,EntityEquipmentSlot.CHEST).setRegistryName("jucetanium_chestplate").setUnlocalizedName("jucetanium_chestplate");
        jucetanium_leggings = new JucetaniumArmor(ArmorMaterials.JUICEITANIUM,2,EntityEquipmentSlot.LEGS).setRegistryName("jucetanium_leggings").setUnlocalizedName("jucetanium_leggings");
        jucetanium_boots = new JucetaniumArmor(ArmorMaterials.JUICEITANIUM,1,EntityEquipmentSlot.FEET).setRegistryName("jucetanium_boots").setUnlocalizedName("jucetanium_boots");

        pineapple = new PlantableFruit(ModBlocks.pineapple).setRegistryName("pineapple").setUnlocalizedName("pineapple");
        lemon = new PlantableFruit(ModBlocks.lemon).setRegistryName("lemon").setUnlocalizedName("lemon");
        lime = new PlantableFruit(ModBlocks.lime).setRegistryName("lime").setUnlocalizedName("lime");
        pomegranate = new PlantableFruit(ModBlocks.pomegranate).setRegistryName("pomegranate").setUnlocalizedName("pomegranate");
        grape = new PlantableFruit(ModBlocks.grape).setRegistryName("grape").setUnlocalizedName("grape");
        cranberry = new PlantableFruit(ModBlocks.cranberry).setRegistryName("cranberry").setUnlocalizedName("cranberry");
        blueberry = new PlantableFruit(ModBlocks.blueberry).setRegistryName("blueberry").setUnlocalizedName("blueberry");
        blackberry = new PlantableFruit(ModBlocks.blackberry).setRegistryName("blackberry").setUnlocalizedName("blackberry");
        orange = new ItemFood(1,0.3f,false).setRegistryName("orange").setUnlocalizedName("orange");
        coconut = new ItemFood(1,0.3f,false).setRegistryName("coconut").setUnlocalizedName("coconut");
        pines = new Item().setRegistryName("pines").setUnlocalizedName("pines");
        cinnamon = new Item().setRegistryName("cinnamon").setUnlocalizedName("cinnamon");

        dehydrated_grape = new DehydratedFruit().setRegistryName("dehydrated_grape").setUnlocalizedName("dehydrated_grape");
        dehydrated_pineapple = new DehydratedFruit().setRegistryName("dehydrated_pineapple").setUnlocalizedName("dehydrated_pineapple");
        dehydrated_lemon = new DehydratedFruit().setRegistryName("dehydrated_lemon").setUnlocalizedName("dehydrated_lemon");
        dehydrated_lime = new DehydratedFruit().setRegistryName("dehydrated_lime").setUnlocalizedName("dehydrated_lime");
        dehydrated_coconut = new DehydratedFruit().setRegistryName("dehydrated_coconut").setUnlocalizedName("dehydrated_coconut");
        dehydrated_apple = new DehydratedFruit().setRegistryName("dehydrated_apple").setUnlocalizedName("dehydrated_apple");
        dehydrated_orange = new DehydratedFruit().setRegistryName("dehydrated_orange").setUnlocalizedName("dehydrated_orange");
        dehydrated_cranberry = new DehydratedFruit().setRegistryName("dehydrated_cranberry").setUnlocalizedName("dehydrated_cranberry");
        dehydrated_blueberry= new DehydratedFruit().setRegistryName("dehydrated_blueberry").setUnlocalizedName("dehydrated_blueberry");
        dehydrated_blackberry= new DehydratedFruit().setRegistryName("dehydrated_blackberry").setUnlocalizedName("dehydrated_blackberry");
        dehydrated_beetroot = new DehydratedFruit().setRegistryName("dehydrated_beetroot").setUnlocalizedName("dehydrated_beetroot");
        dehydrated_potato = new DehydratedFruit().setRegistryName("dehydrated_potato").setUnlocalizedName("dehydrated_potato");
        dehydrated_carrot = new DehydratedFruit().setRegistryName("dehydrated_carrot").setUnlocalizedName("dehydrated_carrot");
        dehydrated_pomegranate = new DehydratedFruit().setRegistryName("dehydrated_pomegranate").setUnlocalizedName("dehydrated_pomegranate");


        //spacing for easier copy ability
        add(juicer                      );
        add(citric_acid                 );
        add(fizzium_ingot               );
        add(liquadium_ingot             );
        add(jucetanium_ingot            );
        add(fizzium_helmet              );
        add(fizzium_chestplate          );
        add(fizzium_leggings            );
        add(fizzium_boots               );
        add(liquadium_helmet            );
        add(liquadium_chestplate        );
        add(liquadium_leggings          );
        add(liquadium_boots             );
        add(jucetanium_helmet           );
        add(jucetanium_chestplate       );
        add(jucetanium_leggings         );
        add(jucetanium_boots            );
        add(fizzium_shard               );
        add(liquadium_shard             );
        add(drink_cup                   );
        add(drink_thick                 );
        add(pineapple                   );
        add(lemon                       );
        add(lime                        );
        add(pomegranate                 );
        add(grape                       );
        add(cranberry                   );
        add(blackberry                  );
        add(blueberry                   );
        add(pines                       );
        add(cinnamon                    );
        add(coconut                     );
        add(orange                      );
        add(dehydrated_grape            );
        add(dehydrated_pineapple        );
        add(dehydrated_lemon            );
        add(dehydrated_lime             );
        add(dehydrated_coconut          );
        add(dehydrated_apple            );
        add(dehydrated_orange           );
        add(dehydrated_cranberry        );
        add(dehydrated_blackberry       );
        add(dehydrated_blueberry        );
        add(dehydrated_beetroot         );
        add(dehydrated_potato           );
        add(dehydrated_carrot           );
        add(dehydrated_pomegranate      );
        add(drink_apple_juice           );
        add(drink_apple_thick           );
        add(drink_beet_juice            );
        add(drink_carrot_juice          );
        add(drink_carrot_thick          );
        add(drink_chocolate_milk_thick  );
        add(drink_cinnamon_spice_thick  );
        add(drink_coconut_juice         );
        add(drink_cranapple_juice       );
        add(drink_cranlemon_juice       );
        add(drink_cranlime_juice        );
        add(drink_fizzium_thick         );
        add(drink_fruit_punch_juice     );
        add(drink_grape_juice           );
        add(drink_jucetanium_thick      );
        add(drink_lemon_juice           );
        add(drink_lime_juice            );
        add(drink_liquadium_thick       );
        add(drink_potato_juice          );
        add(drink_sour_juice            );
        add(drink_tropical_punch_juice  );
    }
    private static void add(@Nonnull Item item) {
        item.setCreativeTab(XtraDrinks.XTRADRINKS_CREATIVE_TAB);
        ITEMS.add(item);
    }
}

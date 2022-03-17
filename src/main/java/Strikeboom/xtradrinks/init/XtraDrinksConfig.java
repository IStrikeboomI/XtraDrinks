package Strikeboom.xtradrinks.init;

import net.minecraftforge.common.ForgeConfigSpec;

public class XtraDrinksConfig {
    public static final ForgeConfigSpec COMMON;

    public static final ForgeConfigSpec.IntValue DEHYDRATOR_DELAY;
    public static final ForgeConfigSpec.IntValue LIQUID_DEHYDRATOR_DELAY;
    public static final ForgeConfigSpec.BooleanValue GREENMAN_LURE_ENABLED;

    public static final ForgeConfigSpec.BooleanValue GREENMAN_ITEMS_ENABLED;
    public static final ForgeConfigSpec.IntValue GREENMAN_ITEMS_MAX;

    public static final ForgeConfigSpec.BooleanValue CROP_GENERATION_ENABLED;
    public static final ForgeConfigSpec.BooleanValue HANGING_CROP_GENERATION_ENABLED;

    public static final ForgeConfigSpec.BooleanValue STRUCTURES_ENABLED;
    public static final ForgeConfigSpec.BooleanValue COCONUT_FARM_ENABLED;
    public static final ForgeConfigSpec.BooleanValue ORANGE_FARM_ENABLED;
    public static final ForgeConfigSpec.BooleanValue PINES_FARM_ENABLED;
    public static final ForgeConfigSpec.BooleanValue TROPICAL_FARM_ENABLED;
    public static final ForgeConfigSpec.BooleanValue BERRY_FARM_ENABLED;
    public static final ForgeConfigSpec.BooleanValue POMEGRANATE_FARM_ENABLED;
    public static final ForgeConfigSpec.BooleanValue FIZZIUM_WELL_ENABLED;
    public static final ForgeConfigSpec.BooleanValue LARGE_FIZZIUM_WELL_ENABLED;
    public static final ForgeConfigSpec.BooleanValue LIQUADIUM_WELL_ENABLED;
    public static final ForgeConfigSpec.BooleanValue LARGE_LIQUADIUM_WELL_ENABLED;

    public static final ForgeConfigSpec.IntValue COCONUT_FARM_HEIGHT;
    public static final ForgeConfigSpec.IntValue ORANGE_FARM_HEIGHT;
    public static final ForgeConfigSpec.IntValue PINES_FARM_HEIGHT;
    public static final ForgeConfigSpec.IntValue TROPICAL_FARM_HEIGHT;
    public static final ForgeConfigSpec.IntValue BERRY_FARM_HEIGHT;
    public static final ForgeConfigSpec.IntValue POMEGRANATE_FARM_HEIGHT;
    static {
        final ForgeConfigSpec.Builder common = new ForgeConfigSpec.Builder();
        common.push("Dehydrator");
        DEHYDRATOR_DELAY = common.comment("Dehydrator Delay").defineInRange("dehydrator_delay",200,2,10000);
        common.pop();

        common.push("Liquid Dehydrator");
        LIQUID_DEHYDRATOR_DELAY = common.comment("Liquid Dehydrator Delay").defineInRange("liquid_dehydrator_delay",200,2,10000);
        common.pop();

        common.push("Greenman");
        GREENMAN_LURE_ENABLED = common.comment("Greenman Lure Enabled").define("greenman_lure_enabled",true);
        GREENMAN_ITEMS_ENABLED = common.comment("Greenman Items Enabled").define("greenman_items_enabled",true);
        GREENMAN_ITEMS_MAX = common.comment("Greenman Items Max").defineInRange("greenman_items_max",16,3,64);
        common.pop();

        common.push("Crop Generation");
        CROP_GENERATION_ENABLED = common.comment("Crop Generation Enabled").define("crop_generation_enabled",true);
        common.pop();

        common.push("Hanging Crop Generation");
        HANGING_CROP_GENERATION_ENABLED = common.comment("Hanging Crop Generation Enabled").define("hanging_crop_generation_enabled",true);
        common.pop();

        common.push("Structures");
        STRUCTURES_ENABLED = common.comment("Structures Enabled").define("structures_enabled",true);
        common.pop();

        common.push("Coconut Farm");
        COCONUT_FARM_ENABLED = common.comment("Coconut Farm Enabled").define("coconut_farm_enabled",true);
        COCONUT_FARM_HEIGHT = common.comment("Coconut Farm Enabled").defineInRange("coconut_farm_height",100,70,200);
        common.pop();

        common.push("Orange Farm");
        ORANGE_FARM_ENABLED = common.comment("Orange Farm Enabled").define("orange_farm_enabled",true);
        ORANGE_FARM_HEIGHT = common.comment("Orange Farm Enabled").defineInRange("orange_farm_height",100,70,200);
        common.pop();

        common.push("Pines Farm");
        PINES_FARM_ENABLED = common.comment("Pines Farm Enabled").define("pines_farm_enabled",true);
        PINES_FARM_HEIGHT = common.comment("Pines Farm Enabled").defineInRange("pines_farm_height",100,70,200);
        common.pop();

        common.push("Tropical Farm");
        TROPICAL_FARM_ENABLED = common.comment("Tropical Farm Enabled").define("tropical_farm_enabled",true);
        TROPICAL_FARM_HEIGHT = common.comment("Tropical Farm Enabled").defineInRange("tropical_farm_height",100,70,200);
        common.pop();

        common.push("Berry Farm");
        BERRY_FARM_ENABLED = common.comment("Berry Farm Enabled").define("berry_farm_enabled",true);
        BERRY_FARM_HEIGHT = common.comment("Berry Farm Enabled").defineInRange("berry_farm_height",100,70,200);
        common.pop();

        common.push("Pomegranate Farm");
        POMEGRANATE_FARM_ENABLED = common.comment("Pomegranate Farm Enabled").define("pomegranate_farm_enabled",true);
        POMEGRANATE_FARM_HEIGHT = common.comment("Pomegranate Farm Enabled").defineInRange("pomegranate_farm_height",100,70,200);
        common.pop();

        common.push("Fizzium Well");
        FIZZIUM_WELL_ENABLED = common.comment("Fizzium Well Enabled").define("fizzium_well_enabled",true);
        common.pop();

        common.push("Large Fizzium Well");
        LARGE_FIZZIUM_WELL_ENABLED = common.comment("Large Fizzium Well Enabled").define("large_fizzium_well_enabled",true);
        common.pop();

        common.push("Liquadium Well");
        LIQUADIUM_WELL_ENABLED = common.comment("Liquadium Well Enabled").define("liquadium_well_enabled",true);
        common.pop();

        common.push("Large Liquadium Well");
        LARGE_LIQUADIUM_WELL_ENABLED = common.comment("Large Liquadium Well Enabled").define("large_liquadium_well_enabled",true);
        common.pop();

        COMMON = common.build();
    }
}

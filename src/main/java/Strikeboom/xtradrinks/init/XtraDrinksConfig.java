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
    public static final ForgeConfigSpec.BooleanValue WELLS_ENABLED;
    public static final ForgeConfigSpec.BooleanValue FARMS_ENABLED;

    public static final ForgeConfigSpec.IntValue FARM_MIN_HEIGHT;
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

        common.push("Wells");
        WELLS_ENABLED = common.comment("Wells Enabled").define("wells_enabled",true);
        common.pop();

        common.push("Farms");
        FARMS_ENABLED = common.comment("Farms Enabled").define("farms_enabled",true);
        common.pop();

        common.push("Farms");
        FARM_MIN_HEIGHT = common.comment("Farm Minimum Height").defineInRange("farm_height",100,70,300);
        common.pop();

        COMMON = common.build();
    }
}

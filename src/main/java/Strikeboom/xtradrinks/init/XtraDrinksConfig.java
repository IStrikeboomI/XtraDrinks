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
        CROP_GENERATION_ENABLED = common.comment("Crop Generation Enabled").comment("Includes both ground and hanging crops").define("crop_generation_enabled",true);
        common.pop();

        COMMON = common.build();
    }
}

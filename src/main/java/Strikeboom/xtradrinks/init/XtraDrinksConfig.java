package Strikeboom.xtradrinks.init;


import net.neoforged.neoforge.common.ModConfigSpec;

public class XtraDrinksConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue DEHYDRATOR_DELAY = BUILDER.push("dehydrator").translation("xtradrinks.configuration.dehydrator.delay").defineInRange("dehydrator_delay",200,2,10000);
    public static final ModConfigSpec.IntValue LIQUID_DEHYDRATOR_DELAY = BUILDER.pop().push("liquid_dehydrator").translation("xtradrinks.configuration.liquid_dehydrator.delay").defineInRange("liquid_dehydrator_delay",200,2,10000);

    public static final ModConfigSpec.BooleanValue GREENMAN_LURE_ENABLED = BUILDER.pop().push("greenman").translation("xtradrinks.configuration.greenman.lure_enabled").define("greenman_lure_enabled",true);
    public static final ModConfigSpec.BooleanValue GREENMAN_ITEMS_ENABLED = BUILDER.translation("xtradrinks.configuration.greenman.items_enabled").define("greenman_items_enabled",true);
    public static final ModConfigSpec.IntValue GREENMAN_ITEMS_MAX = BUILDER.translation("xtradrinks.configuration.greenman.items_max").defineInRange("greenman_items_max",16,1,64);

    public static final ModConfigSpec.BooleanValue CROP_GENERATION_ENABLED = BUILDER.pop().push("crops").translation("xtradrinks.configuration.crops.enabled").define("crop_generation_enabled",true);
}

package Strikeboom.xtradrinks.handlers;

import Strikeboom.xtradrinks.XtraDrinks;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = XtraDrinks.MOD_ID)
@Config.LangKey(XtraDrinks.MOD_ID+".config.name")
public class ConfigHandler {

    @Config.LangKey(XtraDrinks.MOD_ID+".config.blocks")
    public static final Blocks BLOCKS = new Blocks();

    public static class Blocks {
        @Config.LangKey(XtraDrinks.MOD_ID+".config.blocks.dehydrator_delay")
        @Config.RangeInt(min = 2,max = 1000)
        public int dehydrator_delay_time = 50;

        @Config.LangKey(XtraDrinks.MOD_ID+".config.blocks.liquid_dehydrator_delay")
        @Config.RangeInt(min = 2,max = 1000)
        public int liquid_dehydrator_delay_time = 50;
    }
    @Mod.EventBusSubscriber(modid = XtraDrinks.MOD_ID)
    public static class ConfigEvent {
        @SubscribeEvent()
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(XtraDrinks.MOD_ID)) {
                ConfigManager.sync(XtraDrinks.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }

}

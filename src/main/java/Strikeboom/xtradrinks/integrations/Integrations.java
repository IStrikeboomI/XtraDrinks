package Strikeboom.xtradrinks.integrations;

import Strikeboom.xtradrinks.integrations.tconstruct.Materials;
import Strikeboom.xtradrinks.integrations.tconstruct.SmelteryRecipes;
import net.minecraftforge.fml.common.Loader;

public class Integrations {
    public static void preInit() {
        if (Loader.isModLoaded("tconstruct")) {
            Materials.preInit();
        }
    }
    public static void init() {
        if (Loader.isModLoaded("tconstruct")) {
            SmelteryRecipes.init();
        }
    }
}

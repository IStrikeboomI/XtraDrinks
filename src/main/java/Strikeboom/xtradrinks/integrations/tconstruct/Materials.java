package Strikeboom.xtradrinks.integrations.tconstruct;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.init.ModFluids;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.utils.HarvestLevels;

public class Materials {
    public static Material fizzium;
    public static Material liquadium;
    public static void preInit() {
        fizzium = new Material(XtraDrinks.MOD_ID + ".fizzium", 0xb67900);
        fizzium.addItem("ingotFizzium");
        fizzium.addItemIngot("ingotFizzium");
        TinkerRegistry.addMaterialStats(fizzium, new HeadMaterialStats(1380, 11.08F, 8.2F, HarvestLevels.OBSIDIAN), new HandleMaterialStats(0.97F, 100), new ExtraMaterialStats(150), new BowMaterialStats(1.07F, 1.63F, 4.02F));
        fizzium.setFluid(ModFluids.fizzium_fluid);
        fizzium.setCastable(true);
        fizzium.setCraftable(false);
        TinkerRegistry.integrate(fizzium).preInit();

        liquadium = new Material(XtraDrinks.MOD_ID + ".liquadium", 0x6fa0ea);
        liquadium.addItem("ingotLiquadium");
        liquadium.addItemIngot("ingotLiquadium");
        TinkerRegistry.addMaterialStats(liquadium, new HeadMaterialStats(1380, 11.08F, 8.2F, HarvestLevels.OBSIDIAN), new HandleMaterialStats(0.97F, 100), new ExtraMaterialStats(150), new BowMaterialStats(1.07F, 1.63F, 4.02F));
        liquadium.setFluid(ModFluids.fizzium_fluid);
        liquadium.setCastable(true);
        liquadium.setCraftable(false);
        TinkerRegistry.integrate(liquadium).preInit();
    }
}

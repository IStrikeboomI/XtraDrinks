package Strikeboom.xtradrinks.guis.tileentity.recipes.liquid_dehydrator;

import Strikeboom.xtradrinks.init.ModFluids;
import Strikeboom.xtradrinks.init.ModItems;

public class LiquidDehydratorRecipeRegister {
    public static void init() {
        LiquidDehydratorHandler.addRecipe(ModItems.fizzium_shard, ModFluids.fizzium_fluid,100);
        LiquidDehydratorHandler.addRecipe(ModItems.liquadium_shard, ModFluids.liquadium_fluid,100);

    }
}

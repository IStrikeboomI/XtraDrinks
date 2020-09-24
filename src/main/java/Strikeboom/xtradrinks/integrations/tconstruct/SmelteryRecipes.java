package Strikeboom.xtradrinks.integrations.tconstruct;

import Strikeboom.xtradrinks.init.ModBlocks;
import Strikeboom.xtradrinks.init.ModFluids;
import Strikeboom.xtradrinks.init.ModItems;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;

public class SmelteryRecipes {
    public static void init() {
        TinkerRegistry.registerMelting(ModItems.liquadium_ingot, ModFluids.liquadium_fluid,  288);
        TinkerRegistry.registerMelting(ModItems.liquadium_shard, ModFluids.liquadium_fluid,  288);
        TinkerRegistry.registerMelting(ModItems.liquadium_helmet, ModFluids.liquadium_fluid,  1440);
        TinkerRegistry.registerMelting(ModItems.liquadium_chestplate, ModFluids.liquadium_fluid,  2304);
        TinkerRegistry.registerMelting(ModItems.liquadium_leggings, ModFluids.liquadium_fluid,  2016);
        TinkerRegistry.registerMelting(ModItems.liquadium_boots, ModFluids.liquadium_fluid,  1152);
        TinkerRegistry.registerMelting(ModBlocks.liquadium_block,ModFluids.liquadium_fluid,2592);

        TinkerRegistry.registerMelting(ModItems.fizzium_ingot, ModFluids.fizzium_fluid,  288);
        TinkerRegistry.registerMelting(ModItems.fizzium_shard, ModFluids.fizzium_fluid,  288);
        TinkerRegistry.registerMelting(ModItems.fizzium_helmet, ModFluids.fizzium_fluid,  1440);
        TinkerRegistry.registerMelting(ModItems.fizzium_chestplate, ModFluids.fizzium_fluid,  2304);
        TinkerRegistry.registerMelting(ModItems.fizzium_leggings, ModFluids.fizzium_fluid,  2016);
        TinkerRegistry.registerMelting(ModItems.fizzium_boots, ModFluids.fizzium_fluid,  1152);
        TinkerRegistry.registerMelting(ModBlocks.fizzium_block,ModFluids.fizzium_fluid,2592);

        TinkerRegistry.registerTableCasting(new ItemStack(ModItems.liquadium_ingot),TinkerSmeltery.castIngot,ModFluids.liquadium_fluid,288);
        TinkerRegistry.registerTableCasting(new ItemStack(ModItems.fizzium_ingot),TinkerSmeltery.castIngot,ModFluids.fizzium_fluid,288);

        TinkerRegistry.registerBasinCasting(new ItemStack(ModBlocks.liquadium_block),ItemStack.EMPTY,ModFluids.liquadium_fluid,2592);
        TinkerRegistry.registerBasinCasting(new ItemStack(ModBlocks.fizzium_block),ItemStack.EMPTY,ModFluids.fizzium_fluid,2592);

    }
}

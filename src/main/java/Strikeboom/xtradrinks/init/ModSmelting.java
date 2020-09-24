package Strikeboom.xtradrinks.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModSmelting {
    public static void init() {
        GameRegistry.addSmelting(ModItems.fizzium_shard,new ItemStack(ModItems.fizzium_ingot),4.0f);
        GameRegistry.addSmelting(ModItems.liquadium_shard,new ItemStack(ModItems.liquadium_ingot),4.0f);
    }
}

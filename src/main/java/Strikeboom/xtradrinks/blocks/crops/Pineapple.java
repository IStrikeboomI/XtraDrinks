package Strikeboom.xtradrinks.blocks.crops;

import Strikeboom.xtradrinks.init.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class Pineapple extends BlockCrops {
    @Override
    protected Item getSeed() {
        return ModItems.pineapple;
    }

    @Override
    protected Item getCrop() {
        return ModItems.pineapple;
    }
}

package Strikeboom.xtradrinks.blocks.crops;

import Strikeboom.xtradrinks.init.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class Lime extends BlockCrops {
    @Override
    protected Item getSeed() {
        return ModItems.lime;
    }
    @Override
    protected Item getCrop() {
        return ModItems.lime;
    }
}

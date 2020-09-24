package Strikeboom.xtradrinks.blocks.crops;

import Strikeboom.xtradrinks.init.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class Blackberry extends BlockCrops {
    @Override
    protected Item getSeed() {
        return ModItems.blackberry;
    }

    @Override
    protected Item getCrop() {
        return ModItems.blackberry;
    }
}

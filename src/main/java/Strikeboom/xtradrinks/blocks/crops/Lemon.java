package Strikeboom.xtradrinks.blocks.crops;

import Strikeboom.xtradrinks.init.ModItems;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

public class Lemon extends BlockCrops {
    @Override
    protected Item getSeed() {
        return ModItems.lemon;
    }

    @Override
    protected Item getCrop() {
        return ModItems.lemon;
    }
}

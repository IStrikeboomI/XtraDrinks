package Strikeboom.xtradrinks.items.fruit;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeedFood;

public class PlantableFruit extends ItemSeedFood {
    public PlantableFruit(Block block) {
        super(1,0.3f,block, Blocks.FARMLAND);
    }
}

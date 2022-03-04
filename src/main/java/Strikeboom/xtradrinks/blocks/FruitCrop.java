package Strikeboom.xtradrinks.blocks;

import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class FruitCrop extends CropBlock {
    public FruitCrop() {
        super(BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.CROP));
    }
}

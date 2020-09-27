package Strikeboom.xtradrinks.blocks;

import Strikeboom.xtradrinks.entity.EntityGreenman;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class GreenmanLure extends Block {
    public GreenmanLure() {
        super(Material.GRASS);
        setSoundType(SoundType.GLASS);
        setHardness(2.0f);
        setResistance(2.0f);
        setHarvestLevel("pickaxe",1);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World player, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add(I18n.format("tile.greenman_lure.tooltip.name"));
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (!worldIn.isRemote) {
            worldIn.destroyBlock(pos,false);
            EntityGreenman greenman = new EntityGreenman(worldIn);
            greenman.setPositionAndUpdate(pos.getX(),pos.getY(),pos.getZ());
            greenman.setRotationYawHead(-placer.rotationYawHead);
            greenman.randomizeHandler();
            worldIn.spawnEntity(greenman);
        }
    }
}

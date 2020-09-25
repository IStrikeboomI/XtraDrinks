package Strikeboom.xtradrinks.blocks;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.guis.tileentity.TileEntityLiquidDehydrator;
import Strikeboom.xtradrinks.handlers.GuiHandler;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class LiquidDehydrator extends Block {
    public LiquidDehydrator() {
        super(Material.IRON);
        setSoundType(SoundType.METAL);
        setHardness(8.0F);
        setResistance(100.0F);
        setHarvestLevel("pickaxe", 2);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityLiquidDehydrator();
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        IItemHandler handler = worldIn.getTileEntity(pos).getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        for (int slot = 0; slot < handler.getSlots(); slot++) {
            ItemStack stack = handler.getStackInSlot(slot);
            if (!stack.isEmpty()) {
                InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
            }
        }
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            if (FluidUtil.interactWithFluidHandler(playerIn, hand, worldIn, pos, null)) {
                return true;
            }
            playerIn.openGui(XtraDrinks.instance, GuiHandler.LIQUID_DEHYDRATOR,worldIn,pos.getX(),pos.getY(),pos.getZ());
        }
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }


}

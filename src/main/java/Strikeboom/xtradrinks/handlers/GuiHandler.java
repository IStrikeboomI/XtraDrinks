package Strikeboom.xtradrinks.handlers;

import Strikeboom.xtradrinks.entity.EntityGreenman;
import Strikeboom.xtradrinks.guis.container.ContainerDehydrator;
import Strikeboom.xtradrinks.guis.container.ContainerGreenman;
import Strikeboom.xtradrinks.guis.container.ContainerLiquidDehydrator;
import Strikeboom.xtradrinks.guis.gui.GuiDehydrator;
import Strikeboom.xtradrinks.guis.gui.GuiGreenman;
import Strikeboom.xtradrinks.guis.gui.GuiLiquidDehydrator;
import Strikeboom.xtradrinks.guis.tileentity.TileEntityDehydrator;
import Strikeboom.xtradrinks.guis.tileentity.TileEntityLiquidDehydrator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    public static final int DEHYDRATOR = 1;
    public static final int GREENMAN = 2;
    public static final int LIQUID_DEHYDRATOR = 3;
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == DEHYDRATOR) {
            return new ContainerDehydrator(player.inventory, (TileEntityDehydrator) world.getTileEntity(new BlockPos(x,y,z)));
        }
        if (ID == GREENMAN) {
            return new ContainerGreenman(player.inventory, world.getEntitiesWithinAABB(EntityGreenman.class,new AxisAlignedBB(new BlockPos(x,y,z))).get(0));
        }
        if (ID == LIQUID_DEHYDRATOR) {
            return new ContainerLiquidDehydrator(player.inventory,(TileEntityLiquidDehydrator)world.getTileEntity(new BlockPos(x,y,z)));
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == DEHYDRATOR) {
            return new GuiDehydrator(player.inventory, (TileEntityDehydrator) world.getTileEntity(new BlockPos(x,y,z)));
        }
        if (ID == GREENMAN) {
            return new GuiGreenman(player.inventory, world.getEntitiesWithinAABB(EntityGreenman.class,new AxisAlignedBB(new BlockPos(x,y,z))).get(0));
        }
        if (ID == LIQUID_DEHYDRATOR) {
            return new GuiLiquidDehydrator(player.inventory,(TileEntityLiquidDehydrator)world.getTileEntity(new BlockPos(x,y,z)));
        }
        return null;
    }
}

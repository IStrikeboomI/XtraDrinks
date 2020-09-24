package Strikeboom.xtradrinks.handlers.proxy;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.entity.EntityGreenman;
import Strikeboom.xtradrinks.entity.render.EntityGreenmanRender;
import Strikeboom.xtradrinks.guis.tileentity.TileEntityDehydrator;
import Strikeboom.xtradrinks.guis.tileentity.TileEntityLiquidDehydrator;
import Strikeboom.xtradrinks.guis.tileentity.tesr.TESRDehydrator;
import Strikeboom.xtradrinks.guis.tileentity.tesr.TESRLiquidDehydrator;
import Strikeboom.xtradrinks.handlers.ConfigHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy implements IProxy{
    @Override
    public void registerItemModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0,new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    @Override
    public void registerFluidTexture(Fluid fluid) {
        ModelResourceLocation loc = new ModelResourceLocation(XtraDrinks.MOD_ID + ":" + fluid.getName(), "fluid");
        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(fluid.getBlock()), stack -> loc);
        ModelLoader.setCustomStateMapper(fluid.getBlock(), new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return loc;
            }
        });

    }

    @Override
    public void preInit() {
        RenderingRegistry.registerEntityRenderingHandler(EntityGreenman.class, EntityGreenmanRender::new);
        MinecraftForge.EVENT_BUS.register(ConfigHandler.ConfigEvent.class);
    }

    @Override
    public void init() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDehydrator.class,new TESRDehydrator());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLiquidDehydrator.class,new TESRLiquidDehydrator());
    }

}

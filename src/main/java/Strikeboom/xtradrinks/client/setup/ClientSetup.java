package Strikeboom.XtraDrinks.client.setup;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {
    public static void init(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            //RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.PINEAPPLE.get(), RenderType.cutout());
            //RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.LEMON.get(), RenderType.cutout());
            //RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.LIME.get(), RenderType.cutout());
            //RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.POMEGRANATE.get(), RenderType.cutout());
            //RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.GRAPE.get(), RenderType.cutout());
            //RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.CRANBERRY.get(), RenderType.cutout());
            //RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.BLUEBERRY.get(), RenderType.cutout());
            //RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.BLACKBERRY.get(), RenderType.cutout());
            //RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.ORANGE.get(), RenderType.cutout());
            //RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.COCONUT.get(), RenderType.cutout());
            //RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.PINES.get(), RenderType.cutout());
            //RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.DEHYDRATOR.get(), RenderType.cutout());
            //RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.LIQUID_DEHYDRATOR.get(), RenderType.cutout());
            //ScreenManager.register(XtraDrinksMenus.DEHYDRATOR_MENU.get(), DehydratorScreen::new);
            //ScreenManager.register(XtraDrinksMenus.LIQUID_DEHYDRATOR_MENU.get(), LiquidDehydratorScreen::new);
            //ScreenManager.register(XtraDrinksMenus.GREENMAN_MENU.get(), GreenmanScreen::new);
            //ClientRegistry.bindTileEntityRenderer(XtraDrinksBlockEntities.DEHYDRATOR_BLOCK_ENTITY.get(), DehydratorBlockEntityRenderer::new);
            //ClientRegistry.bindTileEntityRenderer(XtraDrinksBlockEntities.LIQUID_DEHYDRATOR_BLOCK_ENTITY.get(), LiquidDehydratorBlockEntityRenderer::new);
            //RenderingRegistry.registerEntityRenderingHandler(XtraDrinksEntities.GREENMAN.get(), GreenmanRenderer::new);
        });
    }
}

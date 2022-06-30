package Strikeboom.XtraDrinks.client.setup;

import Strikeboom.XtraDrinks.client.render.entity.GreenmanRenderer;
import Strikeboom.XtraDrinks.client.render.screens.DehydratorScreen;
import Strikeboom.XtraDrinks.client.render.screens.GreenmanScreen;
import Strikeboom.XtraDrinks.client.render.screens.LiquidDehydratorScreen;
import Strikeboom.XtraDrinks.client.render.ter.DehydratorBlockEntityRenderer;
import Strikeboom.XtraDrinks.client.render.ter.LiquidDehydratorBlockEntityRenderer;
import Strikeboom.XtraDrinks.init.XtraDrinksBlocks;
import Strikeboom.XtraDrinks.init.XtraDrinksContainers;
import Strikeboom.XtraDrinks.init.XtraDrinksEntities;
import Strikeboom.XtraDrinks.init.XtraDrinksTileEntities;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {
    public static void init(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.PINEAPPLE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.LEMON.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.LIME.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.POMEGRANATE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.GRAPE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.CRANBERRY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.BLUEBERRY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.BLACKBERRY.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.ORANGE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.COCONUT.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.PINES.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.DEHYDRATOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(XtraDrinksBlocks.LIQUID_DEHYDRATOR.get(), RenderType.cutout());
        ScreenManager.register(XtraDrinksContainers.DEHYDRATOR_CONTAINER.get(), DehydratorScreen::new);
        ScreenManager.register(XtraDrinksContainers.LIQUID_DEHYDRATOR_CONTAINER.get(), LiquidDehydratorScreen::new);
        ScreenManager.register(XtraDrinksContainers.GREENMAN_CONTAINER.get(), GreenmanScreen::new);
        ClientRegistry.bindTileEntityRenderer(XtraDrinksTileEntities.DEHYDRATOR_TILE_ENTITY.get(), DehydratorBlockEntityRenderer::new);
        ClientRegistry.bindTileEntityRenderer(XtraDrinksTileEntities.LIQUID_DEHYDRATOR_TILE_ENTITY.get(), LiquidDehydratorBlockEntityRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(XtraDrinksEntities.GREENMAN.get(), GreenmanRenderer::new);
    }
}

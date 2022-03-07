package Strikeboom.xtradrinks.client.render.setup;

import Strikeboom.xtradrinks.client.render.ber.DehydratorBlockEntityRenderer;
import Strikeboom.xtradrinks.client.render.ber.LiquidDehydratorBlockEntityRenderer;
import Strikeboom.xtradrinks.client.render.entity.GreenmanRenderer;
import Strikeboom.xtradrinks.client.render.screens.DehydratorScreen;
import Strikeboom.xtradrinks.client.render.screens.GreenmanScreen;
import Strikeboom.xtradrinks.client.render.screens.LiquidDehydratorScreen;
import Strikeboom.xtradrinks.init.XtraDrinksBlockEntities;
import Strikeboom.xtradrinks.init.XtraDrinksBlocks;
import Strikeboom.xtradrinks.init.XtraDrinksEntities;
import Strikeboom.xtradrinks.init.XtraDrinksMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {
    public static void init(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(XtraDrinksBlocks.PINEAPPLE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(XtraDrinksBlocks.LEMON.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(XtraDrinksBlocks.LIME.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(XtraDrinksBlocks.POMEGRANATE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(XtraDrinksBlocks.GRAPE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(XtraDrinksBlocks.CRANBERRY.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(XtraDrinksBlocks.BLUEBERRY.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(XtraDrinksBlocks.BLACKBERRY.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(XtraDrinksBlocks.ORANGE.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(XtraDrinksBlocks.COCONUT.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(XtraDrinksBlocks.PINES.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(XtraDrinksBlocks.DEHYDRATOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(XtraDrinksBlocks.LIQUID_DEHYDRATOR.get(), RenderType.cutout());
            MenuScreens.register(XtraDrinksMenus.DEHYDRATOR_MENU.get(), DehydratorScreen::new);
            MenuScreens.register(XtraDrinksMenus.LIQUID_DEHYDRATOR_MENU.get(), LiquidDehydratorScreen::new);
            MenuScreens.register(XtraDrinksMenus.GREENMAN_MENU.get(), GreenmanScreen::new);
            BlockEntityRenderers.register(XtraDrinksBlockEntities.DEHYDRATOR_BLOCK_ENTITY.get(), DehydratorBlockEntityRenderer::new);
            BlockEntityRenderers.register(XtraDrinksBlockEntities.LIQUID_DEHYDRATOR_BLOCK_ENTITY.get(), LiquidDehydratorBlockEntityRenderer::new);
            EntityRenderers.register(XtraDrinksEntities.GREENMAN.get(), GreenmanRenderer::new);
        });
    }
}

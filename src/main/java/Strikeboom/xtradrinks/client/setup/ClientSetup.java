package Strikeboom.xtradrinks.client.setup;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.client.render.ber.DehydratorBlockEntityRenderer;
import Strikeboom.xtradrinks.client.render.ber.LiquidDehydratorBlockEntityRenderer;
import Strikeboom.xtradrinks.client.render.entity.GreenmanRenderer;
import Strikeboom.xtradrinks.client.render.screens.DehydratorScreen;
import Strikeboom.xtradrinks.client.render.screens.GreenmanScreen;
import Strikeboom.xtradrinks.client.render.screens.LiquidDehydratorScreen;
import Strikeboom.xtradrinks.init.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;

@Mod(value = XtraDrinks.MOD_ID, dist = Dist.CLIENT)
public class ClientSetup {
    public ClientSetup(IEventBus modBus, ModContainer modContainer) {
        modBus.register(this);
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
    @SubscribeEvent
    private void registerScreens(RegisterMenuScreensEvent event) {
        event.register(XtraDrinksMenus.DEHYDRATOR_MENU.get(), DehydratorScreen::new);
        event.register(XtraDrinksMenus.LIQUID_DEHYDRATOR_MENU.get(), LiquidDehydratorScreen::new);
        event.register(XtraDrinksMenus.GREENMAN_MENU.get(), GreenmanScreen::new);
    }
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(XtraDrinksEntities.GREENMAN.get(), GreenmanRenderer::new);
        event.registerBlockEntityRenderer(XtraDrinksBlockEntities.DEHYDRATOR_BLOCK_ENTITY.get(), DehydratorBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(XtraDrinksBlockEntities.LIQUID_DEHYDRATOR_BLOCK_ENTITY.get(), LiquidDehydratorBlockEntityRenderer::new);
    }
    @SubscribeEvent
    private void registerFluidTextures(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {

            @Override
            public int getTintColor() {
                return 0xFFfaaa39;
            }

            @Override
            public ResourceLocation getStillTexture() {
                return ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID,"block/molten_fizzium_still");
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID,"block/molten_fizzium_flowing");
            }
        }, XtraDrinksFluids.MOLTEN_FIZZIUM_TYPE);
        event.registerFluidType(new IClientFluidTypeExtensions() {

            @Override
            public int getTintColor() {
                return 0xFF4fb8e8;
            }

            @Override
            public ResourceLocation getStillTexture() {
                return ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID,"block/molten_liquadium_still");
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID,"block/molten_liquadium_flowing");
            }
        }, XtraDrinksFluids.MOLTEN_LIQUADIUM_TYPE);
        event.registerFluidType(new IClientFluidTypeExtensions() {

            @Override
            public int getTintColor() {
                return 0xFF2ccf17;
            }

            @Override
            public ResourceLocation getStillTexture() {
                return ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID,"block/molten_juicetanium_still");
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID,"block/molten_juicetanium_flowing");
            }
        }, XtraDrinksFluids.MOLTEN_JUICETANIUM_TYPE);
    }
}

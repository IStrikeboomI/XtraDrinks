package Strikeboom.xtradrinks.client.render.screens;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.client.util.ClientUtil;
import Strikeboom.xtradrinks.menus.LiquidDehydratorMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;


public class LiquidDehydratorScreen extends AbstractContainerScreen<LiquidDehydratorMenu> {
    FluidTank tank;
    public LiquidDehydratorScreen(LiquidDehydratorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);

        tank = menu.blockEntity.fluidTank;

        titleLabelX -= getGuiLeft() - 50;
        inventoryLabelX -= getGuiLeft() - 55;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int pMouseX, int pMouseY, float pPartialTick)  {
        this.renderBackground(guiGraphics,pMouseX, pMouseY, pPartialTick);
        super.render(guiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(guiGraphics, pMouseX, pMouseY);
        ClientUtil.drawFluidCapacityTooltip(pMouseX,pMouseY,getGuiLeft() + 8,getGuiTop() + 8, 24,66,this,font,guiGraphics,tank.getFluid());
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(RenderType::guiTextured,ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID, "textures/gui/container/liquid_dehydrator.png"), relX, relY, 0, 0, this.imageWidth, this.imageHeight,256,256);
        guiGraphics.blit(RenderType::guiTextured,ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID, "textures/gui/container/liquid_dehydrator.png"),getGuiLeft()+48,getGuiTop()+32,176,0,this.menu.blockEntity.getCooldown() * 24 / this.menu.blockEntity.getDelay(),17,256,256);
        ClientUtil.renderFluidBar(guiGraphics,getGuiLeft() + 8,getGuiTop() + 8,24,66,tank.getFluid(),tank.getCapacity());
    }

}

package Strikeboom.xtradrinks.guis.screens;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.guis.menus.DehydratorMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class DehydratorScreen extends AbstractContainerScreen<DehydratorMenu> {
    public DehydratorScreen(DehydratorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);

        titleLabelX -= getGuiLeft() - 50;
        inventoryLabelX -= getGuiLeft() - 55;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, new ResourceLocation(XtraDrinks.MOD_ID, "textures/gui/container/dehydrator.png"));
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(pPoseStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
        this.blit(pPoseStack,getGuiLeft()+80,getGuiTop()+35,176,0,this.menu.blockEntity.getCooldown() * 24 / this.menu.blockEntity.getDelay(),17);
    }

}

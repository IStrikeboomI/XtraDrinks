package Strikeboom.XtraDrinks.client.render.screens;

import Strikeboom.XtraDrinks.guis.containers.GreenmanContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GreenmanScreen extends ContainerScreen<GreenmanContainer> {
    public GreenmanScreen(GreenmanContainer pMenu, PlayerInventory pPlayerInventory, ITextComponent pTitle) {
        super(pMenu, pPlayerInventory, pTitle);

        titleLabelX -= getGuiLeft() - 55;
        inventoryLabelX -= getGuiLeft() - 55;
    }

    @Override
    public void render(MatrixStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pPoseStack, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(MatrixStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        minecraft.getTextureManager().bind(new ResourceLocation("minecraft", "textures/gui/container/dispenser.png"));
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(pPoseStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }
}

package Strikeboom.XtraDrinks.client.render.screens;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.guis.containers.DehydratorContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class DehydratorScreen extends ContainerScreen<DehydratorContainer> {
    public DehydratorScreen(DehydratorContainer pMenu, PlayerInventory pPlayerInventory, ITextComponent pTitle) {
        super(pMenu, pPlayerInventory, pTitle);

        titleLabelX -= getGuiLeft() - 50;
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
        minecraft.getTextureManager().bind(new ResourceLocation(XtraDrinks.MOD_ID, "textures/gui/container/dehydrator.png"));
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(pPoseStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
        this.blit(pPoseStack,getGuiLeft()+80,getGuiTop()+35,176,0,this.menu.blockEntity.getCooldown() * 24 / this.menu.blockEntity.getDelay(),17);
    }

}

package Strikeboom.XtraDrinks.client.render.screens;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.client.util.ClientUtil;
import Strikeboom.XtraDrinks.guis.containers.LiquidDehydratorContainer;
import Strikeboom.XtraDrinks.init.XtraDrinksPackets;
import Strikeboom.XtraDrinks.network.ServerboundDrainPacket;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class LiquidDehydratorScreen extends ContainerScreen<LiquidDehydratorContainer> {
    FluidTank tank;
    public LiquidDehydratorScreen(LiquidDehydratorContainer pMenu, PlayerInventory pPlayerInventory, ITextComponent pTitle) {
        super(pMenu, pPlayerInventory, pTitle);

        tank = (FluidTank) menu.blockEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null);

        titleLabelX -= getGuiLeft() - 50;
        inventoryLabelX -= getGuiLeft() - 55;
    }

    @Override
    protected void init() {
        super.init();
        addButton(new Button(getGuiLeft()+38,getGuiTop()+50,39,20, new TranslationTextComponent("gui."+ XtraDrinks.MOD_ID +".drain"), pButton -> {
            XtraDrinksPackets.INSTANCE.sendToServer(new ServerboundDrainPacket());
        }));
    }

    @Override
    public void render(MatrixStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pPoseStack, pMouseX, pMouseY);
        ClientUtil.drawFluidCapacityTooltip(pMouseX,pMouseY,getGuiLeft() + 8,getGuiTop() + 8, 24,66,this,pPoseStack,tank.getFluid());
    }

    @Override
    protected void renderBg(MatrixStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        minecraft.getTextureManager().bind(new ResourceLocation(XtraDrinks.MOD_ID, "textures/gui/container/liquid_dehydrator.png"));
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(pPoseStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
        this.blit(pPoseStack,getGuiLeft()+49,getGuiTop()+33,176,0,this.menu.blockEntity.getCooldown() * 24 / this.menu.blockEntity.getDelay(),17);
        ClientUtil.renderFluidBar(pPoseStack,getGuiLeft() + 8,getGuiTop() + 8,24,66,tank.getFluid(),10000);
    }

}

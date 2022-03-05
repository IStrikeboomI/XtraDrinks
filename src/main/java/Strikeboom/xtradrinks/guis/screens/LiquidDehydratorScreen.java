package Strikeboom.xtradrinks.guis.screens;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.guis.menus.LiquidDehydratorMenu;
import Strikeboom.xtradrinks.init.XtraDrinksPackets;
import Strikeboom.xtradrinks.network.ServerboundDrainPacket;
import Strikeboom.xtradrinks.client.util.ClientUtil;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class LiquidDehydratorScreen extends AbstractContainerScreen<LiquidDehydratorMenu> {
    FluidTank tank;
    public LiquidDehydratorScreen(LiquidDehydratorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);

        tank = (FluidTank) menu.blockEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null);

        titleLabelX -= getGuiLeft() - 50;
        inventoryLabelX -= getGuiLeft() - 55;
    }

    @Override
    protected void init() {
        super.init();
        addRenderableWidget(new Button(getGuiLeft()+38,getGuiTop()+50,39,20, new TranslatableComponent("gui."+ XtraDrinks.MOD_ID +".drain"),pButton -> {
            XtraDrinksPackets.INSTANCE.sendToServer(new ServerboundDrainPacket());
        }));
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pPoseStack, pMouseX, pMouseY);
        ClientUtil.drawFluidCapacityTooltip(pMouseX,pMouseY,getGuiLeft() + 8,getGuiTop() + 8, 24,66,this,pPoseStack,tank.getFluid());
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderTexture(0, new ResourceLocation(XtraDrinks.MOD_ID, "textures/gui/container/liquid_dehydrator.png"));
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(pPoseStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
        this.blit(pPoseStack,getGuiLeft()+49,getGuiTop()+33,176,0,this.menu.blockEntity.getCooldown() * 24 / this.menu.blockEntity.getDelay(),17);
        ClientUtil.renderFluidBar(pPoseStack,getGuiLeft() + 8,getGuiTop() + 8,24,66,tank.getFluid(),10000);
    }

}

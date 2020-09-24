package Strikeboom.xtradrinks.guis.gui;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.entity.EntityGreenman;
import Strikeboom.xtradrinks.guis.container.ContainerDehydrator;
import Strikeboom.xtradrinks.guis.container.ContainerGreenman;
import Strikeboom.xtradrinks.guis.tileentity.TileEntityDehydrator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiGreenman extends GuiContainer {
    private final EntityGreenman greenman;
    private final IInventory playerInv;

    public GuiGreenman(IInventory playerInv, EntityGreenman greenman) {
        super(new ContainerGreenman(playerInv, greenman));

        this.xSize = 176;
        this.ySize = 165;

        this.playerInv = playerInv;
        this.greenman = greenman;

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX,mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(new ResourceLocation("minecraft", "textures/gui/container/dispenser.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = this.greenman.getDisplayName().getFormattedText();
        this.fontRenderer.drawString(s, (this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2) - 55, 6, 4210752);
        this.fontRenderer.drawString(this.playerInv.getDisplayName().getFormattedText(), (this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2) - 55, 73, 4210752);
    }
}

package Strikeboom.xtradrinks.guis.gui;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.guis.container.ContainerDehydrator;
import Strikeboom.xtradrinks.guis.tileentity.TileEntityDehydrator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GuiDehydrator extends GuiContainer {
    private final IInventory playerInv;
    private final TileEntityDehydrator te;

    public GuiDehydrator(IInventory playerInv, TileEntityDehydrator te) {
        super(new ContainerDehydrator(playerInv, te));

        this.xSize = 176;
        this.ySize = 165;

        this.playerInv = playerInv;
        this.te = te;

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX,mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(new ResourceLocation(XtraDrinks.MOD_ID, "textures/gui/container/dehydrator.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        this.drawTexturedModalRect(this.guiLeft + 80,this.guiTop + 35,176,0,this.te.getCoolDown() != 0 ? this.te.getCoolDown() * 24 / this.te.getDelay() : 0 ,17);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = this.te.getDisplayName().getFormattedText();
        this.fontRenderer.drawString(s, (this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2) - 50, 6, 4210752);
        this.fontRenderer.drawString(this.playerInv.getDisplayName().getFormattedText(), (this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2) - 50, 73, 4210752);
    }

}

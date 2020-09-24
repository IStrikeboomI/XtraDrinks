package Strikeboom.xtradrinks.guis.gui;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.guis.container.ContainerLiquidDehydrator;
import Strikeboom.xtradrinks.guis.tileentity.TileEntityLiquidDehydrator;
import Strikeboom.xtradrinks.guis.tileentity.messages.MessageDrain;
import Strikeboom.xtradrinks.handlers.PacketHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import java.util.Collections;

public class GuiLiquidDehydrator extends GuiContainer {
    private final IInventory playerInv;
    private final TileEntityLiquidDehydrator te;
    private final FluidTank fluidHandler;

    public GuiLiquidDehydrator(IInventory playerInv, TileEntityLiquidDehydrator te) {
        super(new ContainerLiquidDehydrator(playerInv, te));

        this.xSize = 176;
        this.ySize = 165;

        this.playerInv = playerInv;
        this.te = te;
        this.fluidHandler = (FluidTank) te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,null);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(1,this.guiLeft + 38,this.guiTop + 50,39,20, I18n.format("gui."+XtraDrinks.MOD_ID+".drain.name")));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX,mouseY);
        //Fluid bar tooltip
        if (fluidHandler.getFluid() != null) {
            if (mouseX > this.guiLeft + 9 && mouseX < this.guiLeft + 31
                    && mouseY > this.guiTop +8 && mouseY < this.guiTop + 73 ) {
                FluidStack fluidStack = fluidHandler.getFluid();
                drawHoveringText(Collections.singletonList(fluidStack.getLocalizedName() + " " + fluidStack.amount + "mB"), mouseX, mouseY);
            }
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        this.drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(new ResourceLocation(XtraDrinks.MOD_ID, "textures/gui/container/liquid_dehydrator.png"));
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
        this.drawTexturedModalRect(this.guiLeft + 49,this.guiTop + 33,176,0,this.te.getCoolDown() != 0 ? this.te.getCoolDown() * 24 / this.te.getDelay() : 0 ,17);
        //renders fluid bar
        if (this.fluidHandler.getFluid() != null) {
            FluidStack fluidStack = fluidHandler.getFluid();
            TextureAtlasSprite sprite = mc.getTextureMapBlocks().getAtlasSprite(fluidStack.getFluid().getStill().toString());
            //I need to color the fluid because some fluids like from tconstruct have no colors in the image
            colorFluid(fluidStack.getFluid().getColor());
            mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            this.drawTexturedModalRect(this.guiLeft + 8,this.guiTop + 8 + (66 - fluidHeight(66)),sprite,24,fluidHeight(66));
        }
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = this.te.getDisplayName().getFormattedText();
        this.fontRenderer.drawString(s, (this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2) , 6, 4210752);
        this.fontRenderer.drawString(this.playerInv.getDisplayName().getFormattedText(), (this.xSize / 2 - this.fontRenderer.getStringWidth(this.playerInv.getDisplayName().getFormattedText()) / 2) , 73, 4210752);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 1) {
            PacketHandler.NETWORK.sendToServer(new MessageDrain());
        }
    }
    private int fluidHeight(int height) {
        return (int) Math.ceil(( (float) fluidHandler.getFluidAmount() / (float) fluidHandler.getCapacity()) * height);
    }
    private void colorFluid(int color) {
        GlStateManager.color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f);
    }
}

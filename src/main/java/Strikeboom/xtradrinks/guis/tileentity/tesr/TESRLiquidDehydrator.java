package Strikeboom.xtradrinks.guis.tileentity.tesr;

import Strikeboom.xtradrinks.guis.tileentity.TileEntityLiquidDehydrator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import org.lwjgl.opengl.GL11;

public class TESRLiquidDehydrator extends TileEntitySpecialRenderer<TileEntityLiquidDehydrator> {

    @Override
    public void render(TileEntityLiquidDehydrator te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        final float TANK_THICKNESS = .0626f;
        final float FLUID_HEIGHT = .6f;

        GlStateManager.pushMatrix();
        GlStateManager.pushAttrib();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        GlStateManager.translate((float) x, (float) y, (float) z);

        bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        FluidStack fluidStack = ((FluidTank) te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)).getFluid();
        if (fluidStack != null && fluidStack.getFluid() != null) {
            float topScale = (1.0f - TANK_THICKNESS / 2f - (TANK_THICKNESS + FLUID_HEIGHT)) * fluidStack.amount / (((FluidTank) te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,null)).getCapacity());

            if (topScale > 0.0f) {
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder renderer = tessellator.getBuffer();
                TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluidStack.getFluid().getStill().toString());

                RenderHelper.disableStandardItemLighting();

                int color = fluidStack.getFluid().getColor();

                renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);

                float u1 = sprite.getMinU();
                float v1 = sprite.getMinV();
                float u2 = sprite.getMaxU();
                float v2 = sprite.getMaxV();

                // Top
                renderer.pos(TANK_THICKNESS, topScale + TANK_THICKNESS + FLUID_HEIGHT, TANK_THICKNESS).tex(u1, v1).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();
                renderer.pos(TANK_THICKNESS, topScale + TANK_THICKNESS + FLUID_HEIGHT, 1 - TANK_THICKNESS).tex(u1, v2).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();
                renderer.pos(1 - TANK_THICKNESS, topScale + TANK_THICKNESS + FLUID_HEIGHT, 1 - TANK_THICKNESS).tex(u2, v2).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();
                renderer.pos(1 - TANK_THICKNESS, topScale + TANK_THICKNESS + FLUID_HEIGHT, TANK_THICKNESS).tex(u2, v1).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();

                // Sides
                renderer.pos(TANK_THICKNESS, topScale + TANK_THICKNESS + FLUID_HEIGHT, 1 - TANK_THICKNESS).tex(u1, v1).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();
                renderer.pos(TANK_THICKNESS,    FLUID_HEIGHT   , 1 - TANK_THICKNESS).tex(u1, v2).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();
                renderer.pos(1 - TANK_THICKNESS,  FLUID_HEIGHT   , 1 - TANK_THICKNESS).tex(u2, v2).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();
                renderer.pos(1 - TANK_THICKNESS, topScale + TANK_THICKNESS + FLUID_HEIGHT , 1 - TANK_THICKNESS).tex(u2, v1).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();

                renderer.pos(1 - TANK_THICKNESS, topScale + TANK_THICKNESS + FLUID_HEIGHT, TANK_THICKNESS).tex(u2, v1).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();
                renderer.pos(1 - TANK_THICKNESS, FLUID_HEIGHT , TANK_THICKNESS).tex(u2, v2).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();
                renderer.pos(TANK_THICKNESS, FLUID_HEIGHT , TANK_THICKNESS).tex(u1, v2).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();
                renderer.pos(TANK_THICKNESS, topScale + TANK_THICKNESS + FLUID_HEIGHT, TANK_THICKNESS).tex(u1, v1).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();

                renderer.pos(1 - TANK_THICKNESS, topScale + TANK_THICKNESS + FLUID_HEIGHT, 1 - TANK_THICKNESS).tex(u2, v1).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();
                renderer.pos(1 - TANK_THICKNESS, FLUID_HEIGHT, 1 - TANK_THICKNESS).tex(u2, v2).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();
                renderer.pos(1 - TANK_THICKNESS, FLUID_HEIGHT, TANK_THICKNESS).tex(u1, v2).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();
                renderer.pos(1 - TANK_THICKNESS, topScale + TANK_THICKNESS + FLUID_HEIGHT, TANK_THICKNESS).tex(u1, v1).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();

                renderer.pos(TANK_THICKNESS, topScale + TANK_THICKNESS + FLUID_HEIGHT, TANK_THICKNESS).tex(u1, v1).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();
                renderer.pos(TANK_THICKNESS, FLUID_HEIGHT, TANK_THICKNESS).tex(u1, v2).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();
                renderer.pos(TANK_THICKNESS, FLUID_HEIGHT, 1 - TANK_THICKNESS).tex(u2, v2).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();
                renderer.pos(TANK_THICKNESS, topScale + TANK_THICKNESS + FLUID_HEIGHT, 1 - TANK_THICKNESS).tex(u2, v1).color(((color >> 16) & 0xFF) / 255.0f,((color >> 8) & 0xFF) / 255.0f,(color & 0xFF) / 255.0f,((color >> 24) & 0xFF) / 255.0f).endVertex();

                tessellator.draw();

                RenderHelper.enableStandardItemLighting();
            }
        }
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();

    }

}

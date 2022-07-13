package Strikeboom.xtradrinks.client.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;

public class ClientUtil {
    public static void renderFluidBar(PoseStack poseStack, final int xPosition, final int yPosition, final int width, final int height, FluidStack fluidStack,int size) {
        RenderSystem.enableBlend();

        if (fluidStack != null) {
            Fluid fluid = fluidStack.getFluid();
            if (fluid != null && !fluidStack.isEmpty() && !fluid.isSame(Fluids.EMPTY)) {
                Minecraft minecraft = Minecraft.getInstance();
                IClientFluidTypeExtensions attributes = IClientFluidTypeExtensions.of(fluid);
                ResourceLocation fluidStill = attributes.getStillTexture();
                TextureAtlasSprite fluidStillSprite = minecraft.getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(fluidStill);

                int fluidColor = attributes.getTintColor(fluidStack);

                int amount = fluidStack.getAmount();
                int scaledAmount = (amount * height) / size;
                if (amount > 0 && scaledAmount < 1) {
                    scaledAmount = 1;
                }
                if (scaledAmount > height) {
                    scaledAmount = height;
                }

                RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
                Matrix4f matrix = poseStack.last().pose();
                RenderSystem.setShaderColor(((fluidColor >> 16) & 0xFF) / 255f, ((fluidColor >> 8) & 0xFF) / 255f, (fluidColor & 0xFF) / 255f, ((fluidColor >> 24) & 0xFF) / 255f);

                final int xTileCount = width / 16;
                final int xRemainder = width - (xTileCount * 16);
                final int yTileCount = scaledAmount / 16;
                final int yRemainder = scaledAmount - (yTileCount * 16);

                final int yStart = yPosition + height;

                for (int xTile = 0; xTile <= xTileCount; xTile++) {
                    for (int yTile = 0; yTile <= yTileCount; yTile++) {
                        int w = (xTile == xTileCount) ? xRemainder : 16;
                        int h = (yTile == yTileCount) ? yRemainder : 16;
                        int x = xPosition + (xTile * 16);
                        int y = yStart - ((yTile + 1) * 16);
                        if (w > 0 && h > 0) {
                            int maskTop = 16 - h;
                            int maskRight = 16 - w;

                            float uMin = fluidStillSprite.getU0();
                            float uMax = fluidStillSprite.getU1();
                            float vMin = fluidStillSprite.getV0();
                            float vMax = fluidStillSprite.getV1();
                            uMax -= (maskRight / 16F * (uMax - uMin));
                            vMax -= (maskTop / 16F * (vMax - vMin));

                            RenderSystem.setShader(GameRenderer::getPositionTexShader);

                            Tesselator tessellator = Tesselator.getInstance();
                            BufferBuilder bufferBuilder = tessellator.getBuilder();
                            bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                            bufferBuilder.vertex(matrix, x, y + 16, 100).uv(uMin, vMax).endVertex();
                            bufferBuilder.vertex(matrix, x + 16 - maskRight, y + 16, 100).uv(uMax, vMax).endVertex();
                            bufferBuilder.vertex(matrix, x + 16 - maskRight, y + maskTop, 100).uv(uMax, vMin).endVertex();
                            bufferBuilder.vertex(matrix, x, y + maskTop, 100).uv(uMin, vMin).endVertex();
                            tessellator.end();
                        }
                    }
                }
            }
        }
        RenderSystem.setShaderColor(1, 1, 1, 1);

        RenderSystem.disableBlend();
    }
    public static void drawFluidCapacityTooltip(int mouseX, int mouseY, int xPos, int yPos, int width, int height, AbstractContainerScreen<?> gui, PoseStack pPoseStack, FluidStack fluidStack) {
        if (fluidStack != null && fluidStack.getFluid() != null && !fluidStack.getFluid().isSame( Fluids.EMPTY)) {
            if (mouseX > xPos && mouseX < xPos + width
                    && mouseY > yPos && mouseY < yPos + height ) {
                gui.renderTooltip(pPoseStack, Component.literal(fluidStack.getDisplayName().getString() + " " + fluidStack.getAmount() + " mB"), mouseX, mouseY);
            }
        }
    }
}

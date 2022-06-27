package Strikeboom.XtraDrinks.client.render.ter;

import Strikeboom.XtraDrinks.guis.tileentities.LiquidDehydratorTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class LiquidDehydratorBlockEntityRenderer extends TileEntityRenderer<LiquidDehydratorTileEntity> {
    final float TANK_THICKNESS = .0626f;
    final float FLUID_HEIGHT = .6f;
    public LiquidDehydratorBlockEntityRenderer(TileEntityRendererDispatcher context) {
        super(context);
    }
    @Override
    public void render(LiquidDehydratorTileEntity pBlockEntity, float pPartialTick, MatrixStack pPoseStack, IRenderTypeBuffer pBufferSource, int pPackedLight, int pPackedOverlay) {
        pBlockEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).ifPresent(iFluidHandler -> {
            FluidStack stack = iFluidHandler.getFluidInTank(0);
            if (!stack.isEmpty()) {
                pPoseStack.pushPose();

                float topScale = (1.0f - TANK_THICKNESS / 2f - (TANK_THICKNESS + FLUID_HEIGHT)) * stack.getAmount() / iFluidHandler.getTankCapacity(0) + TANK_THICKNESS + FLUID_HEIGHT;

                if (topScale > 0.0f) {
                    FluidAttributes attributes = stack.getFluid().getAttributes();
                    ResourceLocation fluidStill = attributes.getStillTexture(stack);
                    TextureAtlasSprite fluidStillSprite = Minecraft.getInstance().getTextureAtlas(PlayerContainer.BLOCK_ATLAS).apply(fluidStill);
                    if (fluidStillSprite != null) {

                        IVertexBuilder renderer = pBufferSource.getBuffer(RenderType.text(fluidStillSprite.atlas().location()));
                        Matrix4f matrix = pPoseStack.last().pose();
                        float u1 = fluidStillSprite.getU0();
                        float v1 = fluidStillSprite.getV0();
                        float u2 = fluidStillSprite.getU1();
                        float v2 = fluidStillSprite.getV1();

                        int color = attributes.getColor(stack);
                        float r = ((color >> 16) & 0xFF) / 255.0f;
                        float g = ((color >> 8) & 0xFF) / 255.0f;
                        float b = (color & 0xFF) / 255.0f;
                        float a = ((color >> 24) & 0xFF) / 255.0f;

                        renderer.vertex(matrix, TANK_THICKNESS, topScale, TANK_THICKNESS).color(r, g, b, a).uv(u1, v1).uv2(15728880).endVertex();
                        renderer.vertex(matrix, TANK_THICKNESS, topScale, 1 - TANK_THICKNESS).color(r, g, b, a).uv(u1, v2).uv2(15728880).endVertex();
                        renderer.vertex(matrix, 1 - TANK_THICKNESS, topScale, 1 - TANK_THICKNESS).color(r, g, b, a).uv(u2, v2).uv2(15728880).endVertex();
                        renderer.vertex(matrix, 1 - TANK_THICKNESS, topScale, TANK_THICKNESS).color(r, g, b, a).uv(u2, v1).uv2(15728880).endVertex();

                        renderer.vertex(matrix, TANK_THICKNESS, topScale, 1 - TANK_THICKNESS).color(r, g, b, a).uv(u1, v1).uv2(15728880).endVertex();
                        renderer.vertex(matrix, TANK_THICKNESS, FLUID_HEIGHT, 1 - TANK_THICKNESS).color(r, g, b, a).uv(u1, v2).uv2(15728880).endVertex();
                        renderer.vertex(matrix, 1 - TANK_THICKNESS, FLUID_HEIGHT, 1 - TANK_THICKNESS).color(r, g, b, a).uv(u2, v2).uv2(15728880).endVertex();
                        renderer.vertex(matrix, 1 - TANK_THICKNESS, topScale, 1 - TANK_THICKNESS).color(r, g, b, a).uv(u2, v1).uv2(15728880).endVertex();

                        renderer.vertex(matrix, 1 - TANK_THICKNESS, topScale, TANK_THICKNESS).color(r, g, b, a).uv(u1, v1).uv2(15728880).endVertex();
                        renderer.vertex(matrix, 1 - TANK_THICKNESS, FLUID_HEIGHT, TANK_THICKNESS).color(r, g, b, a).uv(u1, v2).uv2(15728880).endVertex();
                        renderer.vertex(matrix, TANK_THICKNESS, FLUID_HEIGHT, TANK_THICKNESS).color(r, g, b, a).uv(u2, v2).uv2(15728880).endVertex();
                        renderer.vertex(matrix, TANK_THICKNESS, topScale, TANK_THICKNESS).color(r, g, b, a).uv(u2, v1).uv2(15728880).endVertex();

                        renderer.vertex(matrix, 1 - TANK_THICKNESS, topScale, 1 - TANK_THICKNESS).color(r, g, b, a).uv(u1, v1).uv2(15728880).endVertex();
                        renderer.vertex(matrix, 1 - TANK_THICKNESS, FLUID_HEIGHT, 1 - TANK_THICKNESS).color(r, g, b, a).uv(u1, v2).uv2(15728880).endVertex();
                        renderer.vertex(matrix, 1 - TANK_THICKNESS, FLUID_HEIGHT, TANK_THICKNESS).color(r, g, b, a).uv(u2, v2).uv2(15728880).endVertex();
                        renderer.vertex(matrix, 1 - TANK_THICKNESS, topScale, TANK_THICKNESS).color(r, g, b, a).uv(u2, v1).uv2(15728880).endVertex();

                        renderer.vertex(matrix, TANK_THICKNESS, topScale, TANK_THICKNESS).color(r, g, b, a).uv(u1, v1).uv2(15728880).endVertex();
                        renderer.vertex(matrix, TANK_THICKNESS, FLUID_HEIGHT, TANK_THICKNESS).color(r, g, b, a).uv(u1, v2).uv2(15728880).endVertex();
                        renderer.vertex(matrix, TANK_THICKNESS, FLUID_HEIGHT, 1 - TANK_THICKNESS).color(r, g, b, a).uv(u2, v2).uv2(15728880).endVertex();
                        renderer.vertex(matrix, TANK_THICKNESS, topScale, 1 - TANK_THICKNESS).color(r, g, b, a).uv(u2, v1).uv2(15728880).endVertex();

                        pPoseStack.popPose();
                    }
                }
            }
        });
    }
}

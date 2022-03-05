package Strikeboom.xtradrinks.guis.blockentities.render;

import Strikeboom.xtradrinks.guis.blockentities.LiquidDehydratorBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

public class LiquidDehydratorBlockEntityRenderer implements BlockEntityRenderer<LiquidDehydratorBlockEntity> {
    final float TANK_THICKNESS = .0626f;
    final float FLUID_HEIGHT = .6f;
    public LiquidDehydratorBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }
    @Override
    public void render(LiquidDehydratorBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pBlockEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).ifPresent(iFluidHandler -> {
            FluidStack stack = iFluidHandler.getFluidInTank(0);
            if (!stack.isEmpty()) {
                //RenderSystem.enableBlend();
                pPoseStack.pushPose();

                float topScale = (1.0f - TANK_THICKNESS / 2f - (TANK_THICKNESS + FLUID_HEIGHT)) * stack.getAmount() / iFluidHandler.getTankCapacity(0) + TANK_THICKNESS + FLUID_HEIGHT;

                if (topScale > 0.0f) {
                    FluidAttributes attributes = stack.getFluid().getAttributes();
                    ResourceLocation fluidStill = attributes.getStillTexture(stack);
                    TextureAtlasSprite fluidStillSprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(fluidStill);
                    if (fluidStillSprite != null) {

                        VertexConsumer renderer = pBufferSource.getBuffer(RenderType.text(fluidStillSprite.atlas().location()));
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

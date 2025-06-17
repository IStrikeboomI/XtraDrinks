package Strikeboom.xtradrinks.client.render.ber;

import Strikeboom.xtradrinks.blockentities.LiquidDehydratorBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.joml.Matrix4f;

public class LiquidDehydratorBlockEntityRenderer implements BlockEntityRenderer<LiquidDehydratorBlockEntity> {
    final float TANK_THICKNESS = .0626f;
    final float FLUID_HEIGHT = .6f;
    public LiquidDehydratorBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        
    }
    @Override
    public void render(LiquidDehydratorBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, Vec3 cameraPos) {
        IFluidHandler iFluidHandler = blockEntity.fluidTank;
        FluidStack stack = iFluidHandler.getFluidInTank(0);
        if (!stack.isEmpty()) {
            //RenderSystem.enableBlend();
            poseStack.pushPose();

            float topScale = (1.0f - TANK_THICKNESS / 2f - (TANK_THICKNESS + FLUID_HEIGHT)) * stack.getAmount() / iFluidHandler.getTankCapacity(0) + TANK_THICKNESS + FLUID_HEIGHT;

            if (topScale > 0.0f) {
                IClientFluidTypeExtensions attributes = IClientFluidTypeExtensions.of(stack.getFluid());
                ResourceLocation fluidStill = attributes.getStillTexture();

                TextureAtlasSprite fluidStillSprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(fluidStill);
                if (fluidStillSprite != null) {

                    VertexConsumer renderer = bufferSource.getBuffer(RenderType.text(fluidStillSprite.atlasLocation()));
                    Matrix4f matrix = poseStack.last().pose();
                    float u1 = fluidStillSprite.getU0();
                    float v1 = fluidStillSprite.getV0();
                    float u2 = fluidStillSprite.getU1();
                    float v2 = fluidStillSprite.getV1();

                    int color = attributes.getTintColor(stack);
                    float r = ((color >> 16) & 0xFF) / 255.0f;
                    float g = ((color >> 8) & 0xFF) / 255.0f;
                    float b = (color & 0xFF) / 255.0f;
                    float a = ((color >> 24) & 0xFF) / 255.0f;
                    renderer.addVertex(matrix, TANK_THICKNESS, topScale, TANK_THICKNESS)               .setColor(r, g, b, a).setUv(u1, v1).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, TANK_THICKNESS, topScale, 1 - TANK_THICKNESS)           .setColor(r, g, b, a).setUv(u1, v2).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, 1 - TANK_THICKNESS, topScale, 1 - TANK_THICKNESS)       .setColor(r, g, b, a).setUv(u2, v2).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, 1 - TANK_THICKNESS, topScale, TANK_THICKNESS)           .setColor(r, g, b, a).setUv(u2, v1).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, TANK_THICKNESS, topScale, 1 - TANK_THICKNESS)           .setColor(r, g, b, a).setUv(u1, v1).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, TANK_THICKNESS, FLUID_HEIGHT, 1 - TANK_THICKNESS)       .setColor(r, g, b, a).setUv(u1, v2).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, 1 - TANK_THICKNESS, FLUID_HEIGHT, 1 - TANK_THICKNESS)   .setColor(r, g, b, a).setUv(u2, v2).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, 1 - TANK_THICKNESS, topScale, 1 - TANK_THICKNESS)       .setColor(r, g, b, a).setUv(u2, v1).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, 1 - TANK_THICKNESS, topScale, TANK_THICKNESS)           .setColor(r, g, b, a).setUv(u1, v1).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, 1 - TANK_THICKNESS, FLUID_HEIGHT, TANK_THICKNESS)       .setColor(r, g, b, a).setUv(u1, v2).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, TANK_THICKNESS, FLUID_HEIGHT, TANK_THICKNESS)           .setColor(r, g, b, a).setUv(u2, v2).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, TANK_THICKNESS, topScale, TANK_THICKNESS)               .setColor(r, g, b, a).setUv(u2, v1).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, 1 - TANK_THICKNESS, topScale, 1 - TANK_THICKNESS)       .setColor(r, g, b, a).setUv(u1, v1).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, 1 - TANK_THICKNESS, FLUID_HEIGHT, 1 - TANK_THICKNESS)   .setColor(r, g, b, a).setUv(u1, v2).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, 1 - TANK_THICKNESS, FLUID_HEIGHT, TANK_THICKNESS)       .setColor(r, g, b, a).setUv(u2, v2).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, 1 - TANK_THICKNESS, topScale, TANK_THICKNESS)           .setColor(r, g, b, a).setUv(u2, v1).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, TANK_THICKNESS, topScale, TANK_THICKNESS)               .setColor(r, g, b, a).setUv(u1, v1).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, TANK_THICKNESS, FLUID_HEIGHT, TANK_THICKNESS)           .setColor(r, g, b, a).setUv(u1, v2).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, TANK_THICKNESS, FLUID_HEIGHT, 1 - TANK_THICKNESS)       .setColor(r, g, b, a).setUv(u2, v2).setLight(0xFF).setOverlay(packedOverlay);
                    renderer.addVertex(matrix, TANK_THICKNESS, topScale, 1 - TANK_THICKNESS)           .setColor(r, g, b, a).setUv(u2, v1).setLight(0xFF).setOverlay(packedOverlay);

                    poseStack.popPose();
                }
            }
        }
    }
}

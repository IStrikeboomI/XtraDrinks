package Strikeboom.xtradrinks.guis.blockentities.render;

import Strikeboom.xtradrinks.guis.blockentities.DehydratorBlockEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.Random;

public class DehydratorBlockEntityRenderer implements BlockEntityRenderer<DehydratorBlockEntity> {

    public DehydratorBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(DehydratorBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {

        pBlockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(iItemHandler -> {
            ItemStack stack = iItemHandler.getStackInSlot(0);
            if (!stack.isEmpty()) {
                pPoseStack.pushPose();
                pPoseStack.translate(0.5f, 0.75f, 0.5f);
                //basically the radian to deg formula but with cooldown and delay
                pPoseStack.mulPose(new Quaternion(90, 0, pBlockEntity.getCooldown() * (360f / pBlockEntity.getDelay()), true));
                pPoseStack.scale(1f, 1f, 1f);

                ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

                var model = itemRenderer.getItemModelShaper().getItemModel(stack);
                var quads = model.getQuads(null, null, new Random());

                if (!quads.isEmpty()) {
                    pPoseStack.scale(0.5f, 0.5f, 0.5f);
                }
                RenderSystem.applyModelViewMatrix();

                itemRenderer.renderStatic(stack, ItemTransforms.TransformType.FIXED, 15728880, pPackedOverlay, pPoseStack,
                        pBufferSource, 0);
                pPoseStack.popPose();
            }
        });
    }
}

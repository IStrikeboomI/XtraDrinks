package Strikeboom.xtradrinks.client.render.ber;

import Strikeboom.xtradrinks.blockentities.DehydratorBlockEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.items.IItemHandler;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;

public class DehydratorBlockEntityRenderer implements BlockEntityRenderer<DehydratorBlockEntity> {
    private final ItemRenderer itemRenderer;

    public DehydratorBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(DehydratorBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay, Vec3 cameraPos) {
        IItemHandler iItemHandler = blockEntity.itemHandler;
            ItemStack stack = iItemHandler.getStackInSlot(0);
            if (!stack.isEmpty()) {
                poseStack.pushPose();
                poseStack.translate(0.5f, 0.75f, 0.5f);
                //basically the radian to deg formula but with cooldown and delay
                poseStack.mulPose(Axis.YP.rotationDegrees(blockEntity.getCooldown() * (360f / blockEntity.getDelay())));
                poseStack.mulPose(Axis.XP.rotationDegrees(90));
                poseStack.scale(.5f, .5f, .5f);

                //var model = itemRenderer.getItemModelShaper().getItemModel(stack);
                //var quads = model.getQuads(null, null, new Random(),model,null);

                //if (!quads.isEmpty()) {
                //    pPoseStack.scale(0.5f, 0.5f, 0.5f);
                //}

                this.itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, 15728880, packedOverlay, poseStack,
                        bufferSource, blockEntity.getLevel(),0);
                poseStack.popPose();
            }
    }
}

package Strikeboom.XtraDrinks.client.render.ter;

import Strikeboom.XtraDrinks.guis.tileentities.DehydratorTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraftforge.items.CapabilityItemHandler;

import java.util.Random;

public class DehydratorBlockEntityRenderer extends TileEntityRenderer<DehydratorTileEntity> {

    public DehydratorBlockEntityRenderer(TileEntityRendererDispatcher context) {
        super(context);
    }

    @Override
    public void render(DehydratorTileEntity pBlockEntity, float pPartialTick, MatrixStack pPoseStack, IRenderTypeBuffer pBufferSource, int pPackedLight, int pPackedOverlay) {
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

                //RenderSystem.applyModelViewMatrix();

                itemRenderer.renderStatic(stack, ItemCameraTransforms.TransformType.FIXED, 15728880, pPackedOverlay, pPoseStack,pBufferSource);
                pPoseStack.popPose();
            }
        });
    }
}

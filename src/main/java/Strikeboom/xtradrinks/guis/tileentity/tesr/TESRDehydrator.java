package Strikeboom.xtradrinks.guis.tileentity.tesr;

import Strikeboom.xtradrinks.guis.tileentity.TileEntityDehydrator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;

public class TESRDehydrator extends TileEntitySpecialRenderer<TileEntityDehydrator> {
    @Override
    public void render(TileEntityDehydrator te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushAttrib();
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);

        ItemStack stack = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY,null).getStackInSlot(0);
        if (!stack.isEmpty()) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(.5, .75, .5);
            GlStateManager.scale(.7f, .7f, .7f);
            GlStateManager.rotate(90,.5f, 0, 0);
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.NONE);
            GlStateManager.popMatrix();
        }

        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }

}

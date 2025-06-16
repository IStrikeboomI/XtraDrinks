package Strikeboom.xtradrinks.client.render.entity;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.entity.GreenmanEntity;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.VillagerRenderState;
import net.minecraft.resources.ResourceLocation;

public class GreenmanRenderer extends MobRenderer<GreenmanEntity, VillagerRenderState,VillagerModel> {

    public GreenmanRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new VillagerModel(p_174304_.bakeLayer(ModelLayers.VILLAGER)), 1f);
    }

    @Override
    public VillagerRenderState createRenderState() {
        return new VillagerRenderState();
    }


    @Override
    public ResourceLocation getTextureLocation(VillagerRenderState renderState) {
        return ResourceLocation.fromNamespaceAndPath(XtraDrinks.MOD_ID,"textures/entity/greenman.png");
    }
}

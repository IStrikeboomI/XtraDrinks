package Strikeboom.XtraDrinks.client.render.entity;

import Strikeboom.XtraDrinks.XtraDrinks;
import Strikeboom.XtraDrinks.entity.GreenmanEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.util.ResourceLocation;

public class GreenmanRenderer extends MobRenderer<GreenmanEntity, VillagerModel<GreenmanEntity>> {

    public GreenmanRenderer(EntityRendererManager p_174304_) {
        super(p_174304_, new VillagerModel<>(0), .5f);
        this.addLayer(new HeadLayer<>(this));
        this.addLayer(new CrossedArmsItemLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(GreenmanEntity pEntity) {
        return new ResourceLocation(XtraDrinks.MOD_ID,"textures/entity/greenman.png");
    }
}

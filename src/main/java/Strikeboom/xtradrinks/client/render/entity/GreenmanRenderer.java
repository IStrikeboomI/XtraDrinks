package Strikeboom.xtradrinks.client.render.entity;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.entity.GreenmanEntity;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.npc.Villager;

public class GreenmanRenderer extends MobRenderer<GreenmanEntity, VillagerModel<GreenmanEntity>> {

    public GreenmanRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new VillagerModel<>(p_174304_.bakeLayer(ModelLayers.VILLAGER)), 1f);
    }

    @Override
    public ResourceLocation getTextureLocation(GreenmanEntity pEntity) {
        return new ResourceLocation(XtraDrinks.MOD_ID,"textures/entity/greenman.png");
    }
}

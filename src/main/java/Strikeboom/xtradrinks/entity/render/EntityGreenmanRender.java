package Strikeboom.xtradrinks.entity.render;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.entity.EntityGreenman;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class EntityGreenmanRender extends RenderLiving<EntityGreenman> {
    public EntityGreenmanRender(RenderManager RenderManagerIn) {
        super(RenderManagerIn, new ModelVillager(0f), .5f);
    }
    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityGreenman entity) {
        return new ResourceLocation(XtraDrinks.MOD_ID,"textures/entity/greenman.png");
    }

}

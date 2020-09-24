package Strikeboom.xtradrinks.integrations.jei.dehydrator;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.init.ModBlocks;
import Strikeboom.xtradrinks.integrations.jei.XtraDrinksJeiIntegration;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class DehydratorRecipeCategory implements IRecipeCategory<DehydratorRecipeWrapper> {


    public final IDrawableAnimated PROGRESS_BAR;
    public final IDrawable BACKGROUND;


    public DehydratorRecipeCategory(IGuiHelper gui) {
        final IDrawableStatic STATIC_PROGRESS_BAR = gui.createDrawable(new ResourceLocation(XtraDrinks.MOD_ID, "textures/gui/container/dehydrator.png"),176,0,24,17);
        PROGRESS_BAR = gui.createAnimatedDrawable(STATIC_PROGRESS_BAR, 50, IDrawableAnimated.StartDirection.LEFT,false);
        BACKGROUND = gui.createDrawable(new ResourceLocation(XtraDrinks.MOD_ID, "textures/gui/container/dehydrator.png"),4,15,150,60);
    }

    @Override
    public String getUid() {
        return XtraDrinksJeiIntegration.DEHYDRATOR_UID;
    }

    @Override
    public String getTitle() {
        return ModBlocks.dehydrator.getLocalizedName();
    }

    @Override
    public String getModName() {
        return "Xtra Drinks";
    }

    @Override
    public IDrawable getBackground() {
        return BACKGROUND;
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return null;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        PROGRESS_BAR.draw(minecraft,75,20);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, DehydratorRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
        stacks.init(0,true,50,19);
        stacks.init(1,false,111,19);
        stacks.set(ingredients);
    }

}

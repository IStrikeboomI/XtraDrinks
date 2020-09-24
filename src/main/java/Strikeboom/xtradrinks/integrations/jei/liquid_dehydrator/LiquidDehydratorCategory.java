package Strikeboom.xtradrinks.integrations.jei.liquid_dehydrator;

import Strikeboom.xtradrinks.XtraDrinks;
import Strikeboom.xtradrinks.blocks.LiquidDehydrator;
import Strikeboom.xtradrinks.init.ModBlocks;
import Strikeboom.xtradrinks.integrations.jei.XtraDrinksJeiIntegration;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.*;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class LiquidDehydratorCategory implements IRecipeCategory<LiquidDehydratorRecipeWrapper> {
    public final IDrawableAnimated PROGRESS_BAR;
    public final IDrawable BACKGROUND;
    public LiquidDehydratorCategory(IGuiHelper guiHelper) {
        BACKGROUND = guiHelper.createDrawable(new ResourceLocation(XtraDrinks.MOD_ID, "textures/gui/container/liquid_dehydrator.png"),4,15,150,60);
        final IDrawableStatic STATIC_PROGRESS_BAR = guiHelper.createDrawable(new ResourceLocation(XtraDrinks.MOD_ID, "textures/gui/container/liquid_dehydrator.png"),176,0,24,17);
        PROGRESS_BAR = guiHelper.createAnimatedDrawable(STATIC_PROGRESS_BAR,50, IDrawableAnimated.StartDirection.LEFT,false);
    }
    @Override
    public String getUid() {
        return XtraDrinksJeiIntegration.LIQUID_DEHYDRATOR_UID;
    }

    @Override
    public String getTitle() {
        return ModBlocks.liquid_dehydrator.getLocalizedName();
    }

    @Override
    public String getModName() {
        return "Xtra Drinks";
    }

    @Override
    public IDrawable getBackground() {
        return BACKGROUND;
    }

    @Override
    public void drawExtras(Minecraft minecraft) {
        PROGRESS_BAR.draw(minecraft,45,17);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, LiquidDehydratorRecipeWrapper recipeWrapper, IIngredients ingredients) {
        IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
        stacks.init(0,false,81,17);
        stacks.set(ingredients);

        IGuiFluidStackGroup fluidStackGroup = recipeLayout.getFluidStacks();
        fluidStackGroup.init(0,true,4,0,24,60,10000,true,null);
        fluidStackGroup.set(ingredients);
    }
}
